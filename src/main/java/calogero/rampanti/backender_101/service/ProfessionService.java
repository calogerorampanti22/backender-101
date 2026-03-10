package calogero.rampanti.backender_101.service;

import calogero.rampanti.backender_101.dao.PersonDao;
import calogero.rampanti.backender_101.dao.ProfessionDao;
import calogero.rampanti.backender_101.model.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProfessionService {

    private final ProfessionDao professionDao;

    @Autowired
    public ProfessionService(@Qualifier("mysq") ProfessionDao professionDao) {
        this.professionDao = professionDao;
    }

    public void addProfession(Profession profession) {
        professionDao.insertProfession(profession);
    }

    public void deleteProfessionById(Long id) {
        professionDao.deleteProfessionById(id);
    }

    public void updateProfessionNameById(Long id, Profession profession) {
        professionDao.updateProfessionNameById(id, profession);
    }
}
