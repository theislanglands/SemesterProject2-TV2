package domain;

import Interfaces.ProducerInterface;

import java.util.Date;

public class Producer extends Viewer implements ProducerInterface {


    public Producer(){
        //Facade
    }

    public Producer(int ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
    }


    @Override
    public void addProduction(int ID, String Name, Date date) {

    }

    @Override
    public void addCredit(Production production) {

    }

    @Override
    public void search(String searchString) {

    }

    @Override
    public void searchProduction(String searchString) {

    }

    @Override
    public void searchCredit(String searchString) {

    }
}
