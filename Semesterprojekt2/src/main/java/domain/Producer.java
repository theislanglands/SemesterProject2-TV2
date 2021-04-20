package domain;

import Interfaces.ProducerInterface;

import java.util.ArrayList;
import java.util.Date;

public class Producer extends Viewer implements ProducerInterface {

    ArrayList<Production> productions;

    public Producer(){
        //Facade
    }

    public Producer(int ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
    }


    @Override
    public void addProduction(int ID, String Name, Date date) {
        //implementering

        //Production prod = new Production(Name, ID, date);

    }

    @Override
    public void addCredit(Production production) {

    }

    private void getProductions(){
       // productions = data.getProductions;
    }

}
