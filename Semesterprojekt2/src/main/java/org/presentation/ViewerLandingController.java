package org.presentation;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ViewerLandingController {

    public void switchToSearch(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerSearch");
    }
}
