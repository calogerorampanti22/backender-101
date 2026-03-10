package calogero.rampanti.backender_101.api;

import calogero.rampanti.backender_101.model.Profession;
import calogero.rampanti.backender_101.service.ProfessionService;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/profession")
@RestController
public class ProfessionController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @PostMapping
    public void addProfession(@Valid @NonNull @RequestBody Profession profession) {
        professionService.addProfession(profession);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProfession(@PathVariable("id") Long id) {
        professionService.deleteProfessionById(id);
    }

    @PutMapping(path = "{id}")
    public void updateProfessionName(@PathVariable("id") Long id, @Valid @NonNull @RequestBody Profession professionToUpdate) {
        professionService.updateProfessionNameById(id, professionToUpdate);
    }

}
