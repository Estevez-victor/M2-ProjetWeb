package spring.devrep.sante.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.Dispo;

@Configurable
@Repository
public interface DispoRepository extends JpaRepository<Dispo, Long> {
    List<Dispo> findByHeureContains(String heure);

    Dispo findById(long id);

    List<Dispo> findByPro_id(Long id);

    Dispo findByJour(String name);
}
