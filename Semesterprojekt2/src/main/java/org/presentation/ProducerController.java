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


    public TextArea productionsTextArea;


    TvCredits tvCredits = TvCredits.getInstance();


    List<Production> productions = tvCredits.getProductions();




    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToProductions() throws IOException {
        App.setRoot("productions");

        //System.out.println(productionsTextArea.getText());

        productionsTextArea.setText("Productions");

        /*for (Production prod :
                productions) {
            productionsTextArea.appendText(prod.toString() + "\n");
        }*/

    }

    @FXML
    private void switchToAddProduction() throws IOException {
        App.setRoot("AddProduction");
    }

    @FXML
    private void switchToAddCredits() throws IOException {
        App.setRoot("AddCredits");
    }
}