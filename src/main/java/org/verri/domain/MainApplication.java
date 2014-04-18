package org.verri.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApplication {

    public static void main(String[] args) {
        // Test the Eclipselink implementation.
        testJPAImplementation("eclipselink-pu", "Eclipselink test");
        // Test the Hibernate implementation.
        testJPAImplementation("hibernate-pu", "Hibernate test");
    }

    /**
     * Test the persistence unit. This method will save a new entity {@link org.verri.domain.Description} to the database.
     *
     * @param persistenceUnit The persistence unit name.
     * @param description     The description of the test.
     */
    public static void testJPAImplementation(final String persistenceUnit, final String description) {

        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory(persistenceUnit);
            em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();

            // Create a new Description entity.
            Description desc = new Description(description);

            tx.begin();
            em.persist(desc);
            tx.commit();

            System.out.println(description + " passed.");

        } catch (Exception e) {
            System.err.println(description + " failed: " + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

}
