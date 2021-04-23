package Interfaces;

import domain.Production;

import java.util.List;

public interface DataLayerInterface {

    void saveProduction(Production prod);

    List<Production> getProductions();

    // returnerer true hvis lykkedes
    boolean updateProduction(String productionID, Production replaceProduction);

    void createTestProductions();
}
