package domain;

import Interfaces.ViewerInterface;

public class Viewer extends User implements ViewerInterface {


    public Viewer(){
        //facade
    }

    public Viewer(int ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
    }

    @Override
    public void search(String searchString) {
        System.out.println("Searching: " + searchString);
    }

    @Override
    public void searchProduction(String searchString) {

    }

    @Override
    public void searchCredit(String searchString) {

    }
}
