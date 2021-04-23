package org.presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class addCreditsController {

    public Button addCreditButton;

    public void initialize(){
        addCreditButton.setDisable(true);

    }


    public void switchToAddCredits(ActionEvent actionEvent) {
    }

    public void switchToAddProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("addProductions");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }
}
