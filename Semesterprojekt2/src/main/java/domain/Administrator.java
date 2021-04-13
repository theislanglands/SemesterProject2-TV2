package domain;

import Interfaces.AdminInterface;

import java.util.Date;

public class Administrator extends Producer implements AdminInterface{

    public Administrator(){

    }


    public Administrator(int ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
    }


    @Override
    public void verifyProduction(Production production) {

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
