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

        //singleton obj
        tvCredits = TvCredits.getInstance();

        addProductionButton.setDisable(true);

        //might not need this button
        activeCreditsButton.setDisable(true);

        //adding values to dropdowns
        //mangler data
        //should be taken from database
        setCreditTypeDropdown();
        setGenreDropdown();
        setLanguageDropdown();

    }

    private void setLanguageDropdown() {
        // tvCredits.getLanguageList()
        languageDropdown.getItems().add("Dansk");
    }

    private void setGenreDropdown() {
        // tvCredits.getGenreList()

        genreDropdown1.getItems().add("Krimi");
        genreDropdown1.getItems().add("Drama");
        genreDropdown1.getItems().add("Komedie");

        genreDropdown2.getItems().add("Krimi");
        genreDropdown2.getItems().add("Drama");
        genreDropdown2.getItems().add("Komedie");

        genreDropdown3.getItems().add("Krimi");
        genreDropdown3.getItems().add("Drama");
        genreDropdown3.getItems().add("Komedie");
    }

    private void setCreditTypeDropdown() {
        // tvCredits.getTypeList()

        typeDropdown.getItems().add("Film");
        typeDropdown.getItems().add("Serie");
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

        //When button is pressed initialize a prodcution object with 3 arguments
        Production production = new Production(
                productionID.getText(),
                title.getText(),
                //this maybe works for setting Date from calendar
                Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        production.setLength(Integer.parseInt(length.getText()));

        //sets type if any is selected
        if(typeDropdown.getValue() != null){
            if(typeDropdown.getValue().equals("Serie"))
                production.setType("Serie");
            else if(typeDropdown.getValue().equals("Film")){
                production.setType("Film");
            }
        }

        //sets language if any is selected
        if(languageDropdown.getValue() != null){
            switch((String)languageDropdown.getValue()){
                case "Dansk":
                    production.setLanguage("Dansk");
                    break;
                case "Engelsk":
                    production.setLanguage("Engelsk");
                    break;
                case "Spansk":
                    production.setLanguage("Spansk");
                    break;
                //mangler resten
            }
        }

        //Sets genres from a dropdown through an array. This could be refactored in a method
        ArrayList<String> genres = new ArrayList<>();
        if(genreDropdown1.getValue() != null){
            genres.add((String) genreDropdown1.getValue());
        }
        if(genreDropdown2.getValue() != null){
            genres.add((String)genreDropdown2.getValue());
        }
        if(genreDropdown3.getValue() != null){
            genres.add((String)genreDropdown3.getValue());
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

        //saves production through singleton obj
        tvCredits.saveProduction(production);

        //clears the fields where user entered info
        clearFields();
    }

    private void clearFields() {
        length.setText("");
        productionID.setText("");
        season.setText("");
        episode.setText("");
        title.setText("");
        producent.setText("");
        subtitles.setSelected(false);
    }

    //Tror ikke den bruges
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
