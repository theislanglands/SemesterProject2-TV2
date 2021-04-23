package org.presentation;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class addProductionController {


    public Button addProductionButton;

    public void initialize(){
        addProductionButton.setDisable(true);
    }

    public void switchToAddCredits(ActionEvent actionEvent) throws IOException {
        App.setRoot("addCredits");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }

    public void switchToAddProduction(ActionEvent actionEvent) {
    }
}
