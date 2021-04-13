package Interfaces;

import domain.Production;

public interface DataLayerInterface {

    void saveProduction(Production prod);
    Production[] getProduction();
}
