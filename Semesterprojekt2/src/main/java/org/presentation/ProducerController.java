package org.presentation;

import java.io.IOException;
import java.util.*;

import domain.Production;
import domain.TvCredits;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

public class ProducerController {



    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToProductions() throws IOException {
        App.setRoot("productions");

    }

    @FXML
    private void switchToAddProduction() throws IOException {
        App.setRoot("addProduction");
    }

    @FXML
    private void switchToAddCredits() throws IOException {
        App.setRoot("addCredits");
    }
}