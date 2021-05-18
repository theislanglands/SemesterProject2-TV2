package org.presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ProducerController {



    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToProductions() throws IOException {
        App.setRoot("productions");

    }

    @FXML
    private void switchToAddProduction() throws IOException {
        App.setRoot("addProduction");
    }

    @FXML
    private void switchToAddCredits() throws IOException {
        App.setRoot("addCredits");
    }

    public void switchToSearch(ActionEvent actionEvent) throws IOException {
        App.setRoot("search");
    }
}