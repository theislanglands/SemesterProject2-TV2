package org.presentation;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminProdController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }
}