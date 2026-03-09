package calogero.rampanti.backender_101.repository;

import calogero.rampanti.backender_101.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
