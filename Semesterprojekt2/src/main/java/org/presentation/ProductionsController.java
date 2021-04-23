package org.presentation;

import domain.Production;
import domain.TvCredits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;

public class ProductionsController {

    public TextArea productionsTextArea;

    public void initialize(){
        productionsTextArea.setText("Productions \n");
        TvCredits tvCredits = TvCredits.getInstance();
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
}
