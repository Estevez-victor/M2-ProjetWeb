package spring.devrep.sante.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.devrep.sante.entities.RDV;

@Configurable
@Repository
public interface RDVRepository extends JpaRepository<RDV, Long>{
    List<RDV> findByHeureContains(String heure);
    List<RDV> findByPro_id(Long id);
    List<RDV> findByPatient_id(Long id);
    List<RDV> findByPro_idAndPatient_id(Long pro_id, Long patient_id);
}
