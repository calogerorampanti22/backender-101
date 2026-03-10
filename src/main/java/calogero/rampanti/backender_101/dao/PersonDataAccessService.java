package calogero.rampanti.backender_101.dao;

import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.model.Profession;
import calogero.rampanti.backender_101.repository.PersonRepository;
import calogero.rampanti.backender_101.repository.ProfessionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Character.toUpperCase;

@Repository("mysql")
public class PersonDataAccessService implements PersonDao {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDataAccessService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        personRepository.save(new Person(id, person.getName(), person.getProfession()));
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

    @Override
    public Profession getProfessionByPersonName(String PersonName) {
        Person person = personRepository.findByName(PersonName);
        return person.getProfession();
    }

    @Override
    public String getNamesByChar(char c) {
        String s = "";
        List<Person> people = personRepository.findAll();

        for (Person person : people) {
            if(toUpperCase(person.getName().charAt(0)) == toUpperCase(c)) {
                s = s.concat(person.getName()).concat(", ");
            }
        }
        if(!s.isEmpty())
            s = s.substring(0, s.length() - 2);
        else s = "Resource not found";

        return s;
    }


}
