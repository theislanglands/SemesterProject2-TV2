package org.presentation;

import java.io.IOException;
import javafx.fxml.FXML;

public class ProducerController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private void switchToCredits() throws IOException {
        App.setRoot("credits");
    }
}