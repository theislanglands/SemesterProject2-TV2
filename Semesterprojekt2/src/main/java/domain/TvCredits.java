package domain;

import Interfaces.*;
import data.DataFacade;
import data.DataMain;
import domain.enums.CreditType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Controllerklasse

public final class TvCredits {

    private static final TvCredits INSTANCE = new TvCredits();

    private static DataLayerInterface dataconnect;

    private TvCredits(){
        dataconnect = new DataMain();

    }

    public static TvCredits getInstance(){
        return INSTANCE;
    }

    public List<Production> getProductions() {
        return dataconnect.getProductions();
    }

    public Production createProduction(int id, String name, Date releaseDate) {
        return new Production(id, name, releaseDate);
    }

    public Credit createCredit(Person person, String role, CreditType creditType) {
        return new Credit(person, role, creditType);
    }

    public void addCredit (Production production, Credit credit) {
        production.addCredit(credit);
    }


    public static void main(String[] args) {

        TvCredits tvCredits = TvCredits.getInstance();

        List<Production> testProductions = tvCredits.getProductions();
        System.out.println(testProductions);
    }


/*
    private Administrator facade;

    public TvCredits(){
        facade = new Administrator();
    }


    //Calling implementation in associated classes.
    @Override
    public void verifyProduction(Production production) {
        facade.verifyProduction(production);
    }

    @Override
    public void addProduction(int ID, String Name, Date date) {
        facade.addProduction(ID, Name, date);
    }

    @Override
    public void addCredit(Production production) {
        facade.addCredit(production);
    }

    @Override
    public void search(String searchString) {
        facade.search(searchString);
    }

    @Override
    public void searchProduction(String searchString) {
        facade.searchProduction(searchString);
    }

    @Override
    public void searchCredit(String searchString) {
        facade.searchCredit(searchString);
    }
*/

    // private Producer producerObj = new Producer(1,"Test", "Testsen");
   // private Administrator administrator = new Administrator(2,"Teste", "Testesen");


}
