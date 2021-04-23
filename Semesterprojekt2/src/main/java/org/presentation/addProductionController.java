package org.presentation;

import domain.Production;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class addProductionController {


    public Button addProductionButton;
    public TextArea title;
    public TextArea producent;
    public TextArea director;
    public Button saveButton;
    public CheckBox subtitles;
    public CheckBox CC;
    public DatePicker date;
    public TextArea length;
    public ChoiceBox typeDropdown;
    public ChoiceBox languageDropdown;
    public TextArea productionID;
    public TextArea season;
    public TextArea episode;
    public TextArea age;
    public ChoiceBox genreDropdown;

    public void initialize(){
        addProductionButton.setDisable(true);
        typeDropdown.getItems().add("Film");
        typeDropdown.getItems().add("Serie");

        //mangler
        genreDropdown.getItems().add("Krimi");
        genreDropdown.getItems().add("Drama");
        genreDropdown.getItems().add("Komedie");

        //mangler
        languageDropdown.getItems().add("Dansk");
    }

    public void switchToAddCredits(ActionEvent actionEvent) throws IOException {
        App.setRoot("addCredits");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }

    public void switchToAddProduction(ActionEvent actionEvent) {
    }

    public void saveProduction(ActionEvent actionEvent) {
        Production production = new Production(productionID.getText(), title.getText(), Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
}
