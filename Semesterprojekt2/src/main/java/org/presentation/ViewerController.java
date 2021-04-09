package org.presentation;

import java.io.IOException;
import javafx.fxml.FXML;

public class ViewerController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}