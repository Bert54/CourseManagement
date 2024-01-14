package com.coursemanagement.src.configuration;

import jakarta.persistence.EntityManager;

public interface EntityManagerProvider {

    public EntityManager getEntityManager();

}
