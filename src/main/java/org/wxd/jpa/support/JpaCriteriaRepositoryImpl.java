package org.wxd.jpa.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by wxd on 16-9-14.
 */
public class JpaCriteriaRepositoryImpl <T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements JpaCriteriaRepository<T, ID> {

    private final EntityManager entityManager;

    public JpaCriteriaRepositoryImpl(Class<T> domainClass, EntityManager entityManager){
        super(domainClass,entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable){
        return findAll(CustomerSpecs.byAuto(entityManager, example), pageable);
    }
}
