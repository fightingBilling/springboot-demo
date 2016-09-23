package org.wxd.jpa.support;

import com.google.common.collect.Iterables;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wxd on 16-9-14.
 */
public class CustomerSpecs {
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example){
        final Class<T> type = (Class<T>) example.getClass();

        return new Specification<T> (){
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb){

                // 存储查询条件
                List<Predicate> predicates = new ArrayList<Predicate>();

                // 获取实体类的EntityType, 可以获取实体类的属性
                EntityType<T> entity = entityManager.getMetamodel().entity(type);

                // 遍历entity的所有属性
                for(Attribute<T, ?> attr:entity.getDeclaredAttributes()){
                    // 获取对应属性的属性值
                    Object attrValue = getValue(example, attr);
                    if(attrValue != null){

                        // 如果属性为String，则用like模糊查询
                        if(attr.getJavaType() == String.class){
                            if(!StringUtils.isEmpty(attrValue)){

                                // 构造like模糊查询条件
                                predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)),pattern((String) attrValue)));
                            }
                        }else{

                            // 构造equal等值查询条件
                            predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
                        }
                    }
                }

                return predicates.isEmpty()?cb.conjunction():cb.and(Iterables.toArray(predicates, Predicate.class));
            }

            /**
             * 通过反射获得实体类对象对应属性的属性值
             * @param example
             * @param attr
             * @param <T>
             * @return
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr){
                return ReflectionUtils.getField((Field) attr.getJavaMember(),example);
            }

            /**
             * 获得实体类的当前属性的SingularAttribute, SingularAttribute包含的是实体类的某个单独属性
             * @param entity
             * @param fieldName
             * @param fieldClass
             * @param <E>
             * @param <T>
             * @return
             */
            private <E,T>SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass){
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }

    /**
     * 构造like模糊查询
     * @param str
     * @return
     */
    private static String pattern(String str){
        return "%" + str + "%";
    }
}
