package com.coursemanagement.src.configuration;

import jakarta.inject.Singleton;
import jakarta.persistence.*;

@Singleton
public class EntityManagerProviderImpl implements EntityManagerProvider {

    @PersistenceUnit
    private final EntityManagerFactory emfactory;
    @PersistenceContext
    private final EntityManager entitymanager;

    public EntityManagerProviderImpl() {
        emfactory = Persistence.createEntityManagerFactory("course_management");
        entitymanager = emfactory.createEntityManager();
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entitymanager;
    }
}
