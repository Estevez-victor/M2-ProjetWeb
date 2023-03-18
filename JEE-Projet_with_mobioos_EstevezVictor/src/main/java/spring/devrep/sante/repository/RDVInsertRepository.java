package spring.devrep.sante.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.RDV;

@Repository
public class RDVInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(RDV rdv) {
        this.entityManager.persist(rdv);
    }

}
