package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
	private EntityManagerFactory entityManageFactory;
	private EntityManager entityManager;

	public EntityManagerFactoryUtil() {
		entityManageFactory = Persistence.createEntityManagerFactory("jpa-orm-sql-demo");
		entityManager = entityManageFactory.createEntityManager();
	}

	public EntityManager getEnManager() {
		return entityManager;
	}

	public void closeEnManager() {
		entityManager.close();
	}
	
	public void closeEnManagerFactory() {
		entityManageFactory.close();
	}
}
