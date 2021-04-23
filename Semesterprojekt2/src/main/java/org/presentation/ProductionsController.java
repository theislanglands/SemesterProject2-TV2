package org.presentation;

import domain.Production;
import domain.TvCredits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class ProductionsController {

    private TvCredits tvCredits;

    public TextArea productionsTextArea;
    public TextField searchBar;
    public Button searchButton;



    public void initialize(){
        productionsTextArea.setText("Productions \n");
        tvCredits = TvCredits.getInstance();
        List<Production> productions = tvCredits.getProductions();
        for (Production prod :
                productions) {
            productionsTextArea.appendText(prod.toString() + "\n");
        }

    }

    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
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
