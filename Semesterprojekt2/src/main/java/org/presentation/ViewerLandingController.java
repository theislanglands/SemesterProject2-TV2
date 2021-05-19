package org.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ViewerLandingController {

    public void switchToSearch(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerSearch");
    }

    @FXML
    private void switchToViewerLanding() throws IOException {
        App.setRoot("viewerLanding");
    }

    @FXML
    private void switchToViewerSearch() throws IOException {
        App.setRoot("viewerSearch");
    }
}
