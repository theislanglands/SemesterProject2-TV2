package Interfaces;

import domain.Production;

public interface AdminInterface extends ProducerInterface{

    void verifyProduction(Production production);
}
