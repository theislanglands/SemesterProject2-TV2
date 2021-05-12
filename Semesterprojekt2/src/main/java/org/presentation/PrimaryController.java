package org.presentation;

import java.io.IOException;
import java.util.List;

import domain.Production;
import domain.TvCredits;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    TvCredits tvCredits = TvCredits.getInstance();


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