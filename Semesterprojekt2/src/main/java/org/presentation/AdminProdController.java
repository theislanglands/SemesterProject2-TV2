package org.presentation;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminProdController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}