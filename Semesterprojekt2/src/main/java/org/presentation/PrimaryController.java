package org.presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    TvCredits facade = TvCredits.getInstance();


    @FXML
    private void switchToProducer() throws IOException {
        App.setRoot("producer");
    }

    /*Bliver brugt senere med roller
    @FXML
    private void switchToAdministration() throws IOException {
        App.setRoot("administration");
    }

    Bilver brugt senere med roller
    @FXML
    private void switchToViewer() throws IOException {
        App.setRoot("viewer");
    }*/


}