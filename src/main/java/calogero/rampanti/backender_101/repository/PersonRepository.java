package calogero.rampanti.backender_101.repository;

import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    public Person findByName(String name);
    public List<Person> findAllByProfession(Profession profession);
}
