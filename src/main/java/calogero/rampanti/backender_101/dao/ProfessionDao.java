package calogero.rampanti.backender_101.dao;

import calogero.rampanti.backender_101.model.Profession;

public interface ProfessionDao {

    void insertProfession(Profession profession);

    void deleteProfessionById(Long id);

    void updateProfessionNameById(Long id, Profession newProfession);

}
