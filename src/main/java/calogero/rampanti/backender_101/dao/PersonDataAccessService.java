package calogero.rampanti.backender_101.dao;

import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("mysql")
public class PersonDataAccessService implements PersonDao {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDataAccessService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        personRepository.save(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public int deletePersonById(UUID id) {
        personRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        Person oldPerson = personRepository.getReferenceById(id);
        oldPerson.setName(person.getName());
        personRepository.save(oldPerson);
        return 1;
    }
}
