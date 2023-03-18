package spring.devrep.sante.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.Dispo;

@Repository
public class DispoInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(Dispo dispo) {
        this.entityManager.persist(dispo);
    }

}
