package org.presentation;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdministrationController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToAdminValidate() throws IOException {
        App.setRoot("adminValidate");
    }

    @FXML
    private void switchToAdminLanding() throws IOException {
        App.setRoot("administration");
    }
}