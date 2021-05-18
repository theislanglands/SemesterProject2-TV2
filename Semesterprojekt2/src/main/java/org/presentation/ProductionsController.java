package org.presentation;

import domain.Production;
import domain.TvCreditsFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class ProductionsController {

    private TvCreditsFacade tvCreditsFacade;

    public TextArea productionsTextArea;
    public TextField searchBar;
    public Button searchButton;



    public void initialize(){
        //Singleton object initialized
        tvCreditsFacade = TvCreditsFacade.getInstance();

        //Displays all current productions
        productionsTextArea.setText("Productions \n");
        List<Production> productions = tvCreditsFacade.getAllProductions();
        for (Production prod :
                productions) {
            productionsTextArea.appendText(prod.toString() + "\n");
        }

    }

    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("search");
    }

    @FXML
    private void switchToAddProduction() throws IOException {
        App.setRoot("AddProduction");
    }

    @FXML
    private void switchToAddCredits() throws IOException {
        App.setRoot("AddCredits");
    }

    public void search(ActionEvent actionEvent) {

        //tvCredits.search(searchBar.getText());


    }

    private void displaySearch(){

    }
}
