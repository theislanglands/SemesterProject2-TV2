package Interfaces;

import domain.Production;

import java.util.List;

public interface DataLayerInterface {

    // flg metoder defineres for hver klasse!
    void saveProduction(Production prod);
    List<Production> getProductions();
    public Production getProduction(int id);


    // returnerer true hvis lykkedes
    boolean updateProduction(String productionID, Production replaceProduction);

    // void createTestProductions();
}
