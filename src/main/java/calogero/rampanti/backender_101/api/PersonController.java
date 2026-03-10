package calogero.rampanti.backender_101.api;

import calogero.rampanti.backender_101.model.Person;
import calogero.rampanti.backender_101.model.Profession;
import calogero.rampanti.backender_101.service.PersonService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "id/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "id/{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "id/{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }

    @GetMapping(path = "profession")
    public Profession getProfessionByPerson(@RequestParam(name = "personName") String personName) {
        return personService.getProfessionByPersonName(personName);
    }

    @GetMapping(path = "getNamesByChar/{c}")
    public ResponseEntity<String> getNamesByChar(@PathVariable("c") char c) {

        try {
            if(Character.toUpperCase(c) == 'X') {
                throw new RuntimeException("Eccezione forzata");
            }

            String result = personService.getNamesByChar(c);

            if(result.equals("Invalid input")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input");
            }
            if(result.equals("Resource not found")) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Resource not found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(result);

        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
        catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eccezione forzata: " + e.getMessage());
        }


    }
}
