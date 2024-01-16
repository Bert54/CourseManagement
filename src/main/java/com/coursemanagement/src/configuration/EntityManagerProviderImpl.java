package com.coursemanagement.src.configuration;

import jakarta.inject.Singleton;
import jakarta.persistence.*;

@Singleton
public class EntityManagerProviderImpl implements EntityManagerProvider {

    @PersistenceUnit
    private final EntityManagerFactory emFactory;

    @PersistenceContext
    private final EntityManager entityManager;

    public EntityManagerProviderImpl() {
        this.emFactory = Persistence.createEntityManagerFactory("course_management");
        this.entityManager = this.emFactory.createEntityManager();
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
