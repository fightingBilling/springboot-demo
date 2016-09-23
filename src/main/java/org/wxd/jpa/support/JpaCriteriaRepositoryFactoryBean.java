package org.wxd.jpa.support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by wxd on 16-9-14.
 */
public class JpaCriteriaRepositoryFactoryBean<T extends JpaRepository<S,ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID>{
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager){
        return new JpaCriteriaRepositoryFactory(entityManager);
    }

    private static class JpaCriteriaRepositoryFactory extends JpaRepositoryFactory{

        public JpaCriteriaRepositoryFactory(EntityManager entityManager){
            super(entityManager);
        }

        @Override
        @SuppressWarnings({"unchecked"})
        protected <T, ID extends Serializable> SimpleJpaRepository<?,?> getTargetRepository(RepositoryInformation information, EntityManager entityManager){
            return new JpaCriteriaRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata){
            return JpaCriteriaRepositoryImpl.class;
        }
    }
}
