package domain;

//Controllerklasse

public class TvCredits {

    private Producer producer = new Producer("Test");
    private Administrator administrator = new Administrator("TestAdmin");

    public void changeProduction(){
        if(producer instanceof Producer || administrator instanceof Administrator){

        }
    }
}
