package org.presentation;

import java.io.IOException;

import domain.TvCreditsFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {


    @FXML
    private void switchToProducer() throws IOException {
        App.setRoot("producer");

    }

    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }

    public void switchToAdmin(ActionEvent actionEvent) {
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