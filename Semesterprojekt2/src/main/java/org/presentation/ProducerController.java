package org.presentation;

import java.io.IOException;
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
        App.setRoot("AddProduction");
    }

    @FXML
    private void switchToAddCredits() throws IOException {
        App.setRoot("AddCredits");
    }
}