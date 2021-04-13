package org.presentation;

import java.io.IOException;

import domain.TvCredits;
import javafx.fxml.FXML;

public class PrimaryController {

    TvCredits facade = new TvCredits();


    @FXML
    private void switchToSecondary() throws IOException {

        facade.search("Test");

        App.setRoot("secondary");
    }
}
