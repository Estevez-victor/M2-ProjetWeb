package spring.devrep.sante.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.Pro;

@Configurable
@Repository
public interface ProRepository extends JpaRepository<Pro, Long> {

    List<Pro> findByNomContains(String nom);

    Pro findByNom(String nom);

    Pro findById(long id);

    Pro findByMail(String mail);

    Pro findByPrenom(String prenom);

    long deleteById(long id);

    List<Pro> findByNomContainsAndVisitor(String nom, boolean visitor);

    List<Pro> findByMetierContainsAndVisitor(String nom, boolean visitor);

}
