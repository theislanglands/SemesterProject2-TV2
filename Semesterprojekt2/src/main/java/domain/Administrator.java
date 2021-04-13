package domain;

import Interfaces.AdminInterface;



public class Administrator extends Producer implements AdminInterface{

    public Administrator(){

    }

    public Administrator(int ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
    }

    @Override
    public void verifyProduction(Production production) {

    }

}
