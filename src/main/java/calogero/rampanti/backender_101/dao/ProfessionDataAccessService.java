package calogero.rampanti.backender_101.dao;

import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.model.Profession;
import calogero.rampanti.backender_101.repository.PersonRepository;
import calogero.rampanti.backender_101.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("mysq")
public class ProfessionDataAccessService implements ProfessionDao {

    private final ProfessionRepository professionRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ProfessionDataAccessService(ProfessionRepository professionRepository, PersonRepository personRepository) {
        this.professionRepository = professionRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void insertProfession(Profession profession) {
        if(!professionRepository.existsByName(profession.getName()))
            professionRepository.save(profession);
    }

    @Override
    @Transactional
    public void deleteProfessionById(Long id) {
        Profession profession = professionRepository.findById(id).orElse(null);

        List<Person> people = personRepository.findAllByProfession(profession);
        people.forEach(person -> person.setProfession(null));
        personRepository.saveAll(people);

        professionRepository.deleteById(id);
    }

    @Override
    public void updateProfessionNameById(Long id, Profession newProfession) {
        Profession oldProfession = professionRepository.getReferenceById(id);
        oldProfession.setName(newProfession.getName());

        professionRepository.save(oldProfession);
    }
}
