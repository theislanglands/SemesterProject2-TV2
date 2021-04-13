package domain;

import Interfaces.*;

import java.util.Date;

//Controllerklasse

public class TvCredits implements AdminInterface{

    private Viewer viewer;
    private Producer producer;
    private Administrator admin;

    public TvCredits(){
        viewer = new Viewer();
        producer = new Producer();
        admin = new Administrator();
    }


    //Calling implementation in associated classes.
    @Override
    public void verifyProduction(Production production) {
        admin.verifyProduction(production);
    }

    @Override
    public void addProduction(int ID, String Name, Date date) {
        producer.addProduction(ID, Name, date);
    }

    @Override
    public void addCredit(Production production) {
        producer.addCredit(production);
    }

    @Override
    public void search(String searchString) {
        viewer.search(searchString);
    }

    @Override
    public void searchProduction(String searchString) {
        viewer.searchProduction(searchString);
    }

    @Override
    public void searchCredit(String searchString) {
        viewer.searchCredit(searchString);
    }


    // private Producer producerObj = new Producer(1,"Test", "Testsen");
   // private Administrator administrator = new Administrator(2,"Teste", "Testesen");


}
