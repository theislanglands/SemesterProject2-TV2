package org.presentation;

import domain.Production;
import domain.TvCredits;
import domain.enums.Genre;
import domain.enums.Language;
import domain.enums.ProductionType;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class addProductionController {


    public ChoiceBox genreDropdown2;
    public ChoiceBox genreDropdown3;
    public Button activeCreditsButton;
    TvCredits tvCredits;

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
    public ChoiceBox genreDropdown1;

    public void initialize(){

        tvCredits = TvCredits.getInstance();

        addProductionButton.setDisable(true);
        typeDropdown.getItems().add("Film");
        typeDropdown.getItems().add("Serie");

        //mangler
        genreDropdown1.getItems().add("Krimi");
        genreDropdown1.getItems().add("Drama");
        genreDropdown1.getItems().add("Komedie");

        genreDropdown2.getItems().add("Krimi");
        genreDropdown2.getItems().add("Drama");
        genreDropdown2.getItems().add("Komedie");

        genreDropdown3.getItems().add("Krimi");
        genreDropdown3.getItems().add("Drama");
        genreDropdown3.getItems().add("Komedie");

        //mangler
        languageDropdown.getItems().add("Dansk");
        activeCreditsButton.setDisable(true);

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


        Production production = new Production(
                productionID.getText(),
                title.getText(),
                Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        production.setLength(length.getLength());

        if(typeDropdown.getValue() != null){
            if(typeDropdown.getValue().equals("Serie"))
                production.setType(ProductionType.SERIES);
            else if(typeDropdown.getValue().equals("Film")){
                production.setType(ProductionType.FILM);
            }
        }
        if(languageDropdown.getValue() != null){
            switch((String)languageDropdown.getValue()){
                case "Dansk":
                    production.setLanguage(Language.DANISH);
                    break;
                case "Engelsk":
                    production.setLanguage(Language.ENGLISH);
                    break;
                case "Spansk":
                    production.setLanguage(Language.SPANISH);
                    break;
                //mangler resten
            }
        }

        ArrayList<Genre> genres = new ArrayList<>();
        if(genreDropdown1.getValue() != null){
            genres.add(genreSwitch((String) genreDropdown1.getValue()));
        }
        if(genreDropdown2.getValue() != null){
            genres.add(genreSwitch((String)genreDropdown2.getValue()));
        }
        if(genreDropdown3.getValue() != null){
            genres.add(genreSwitch((String)genreDropdown3.getValue()));
        }
        production.setGenre(genres);

        if(subtitles.isSelected()){
            production.setHasSubtitle(true);
        }else{
            production.setHasSubtitle(false);
        }
        //maybe change this from subtitle?
        if(CC.isSelected()){
            production.setHasSignLanguage(true);
        }else{
            production.setHasSignLanguage(false);
        }

        production.setActive(false);
        production.setValidated(false);

        tvCredits.saveProduction(production);
    }

    private Genre genreSwitch(String in){
        switch(in){
            case "Drama":
                return Genre.DRAMA;
            case "Krimi":
                return Genre.THRILLER;
            case "Komedie":
                return Genre.COMEDY;
            //mangler resten
        }
        return null;
    }
}
