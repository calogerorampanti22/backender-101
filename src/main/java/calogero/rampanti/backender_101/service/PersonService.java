package calogero.rampanti.backender_101.service;

import calogero.rampanti.backender_101.dao.PersonDao;
import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.model.Profession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("mysql") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public Profession getProfessionByPersonName(String personName) {
        return personDao.getProfessionByPersonName(personName);
    }

    public String getNamesByChar(char c) {
        if(!inputIsValid(c)) {
            throw new IllegalArgumentException("Invalid input");
        }

        if(Character.toUpperCase(c) == 'X') {
            throw new RuntimeException("Eccezione forzata");
        }

        String result = personDao.getNamesByChar(c);

        if(result.isEmpty()) {
            throw new NoSuchElementException("Resource not found");
        }

        return result;
    }

    private boolean inputIsValid(char c) {
        return Character.isLetter(c);
    }

}
