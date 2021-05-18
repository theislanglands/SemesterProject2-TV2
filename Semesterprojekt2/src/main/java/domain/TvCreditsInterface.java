package Interfaces;

import domain.Production;

import java.util.Date;

public interface ProducerInterface extends ViewerInterface{

    void addProduction(int ID, String Name, Date date);
    void addCredit(Production production);

}
