package spring.devrep.sante.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.Person;

@Repository
public class PersonInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithEntityManager(Person person) {
        this.entityManager.persist(person);
    }

}
