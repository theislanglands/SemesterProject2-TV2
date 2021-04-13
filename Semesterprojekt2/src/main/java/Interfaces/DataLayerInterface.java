package Interfaces;

import domain.Production;

public interface DataLayerInterface {

    void saveProduction(Production prod);
    List<Production> getProductions();
}
