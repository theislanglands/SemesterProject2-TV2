package org.presentation;

import domain.Production;
import domain.TvCreditsFacade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProducerAddProductionController {

    public ChoiceBox genreDropdown1, genreDropdown2, genreDropdown3;
    public ChoiceBox typeDropdown;
    public ChoiceBox languageDropdown;

    public Button saveAndSubmitButton;
    public Button logoutButton;

    public TextArea title;
    public TextArea producent;
    public TextArea director;
    public TextArea length;
    public TextArea productionID;
    public TextArea season;
    public TextArea episode;
    public TextArea age;
    
    public CheckBox subtitles;
    public CheckBox signLanguage;
    public DatePicker date;

    TvCreditsFacade tvCreditsFacade;

    public void initialize(){
        //singleton obj
        tvCreditsFacade = TvCreditsFacade.getInstance();

        //adding values to dropdowns
        setProductionTypeDropdown();
        setGenreDropdown();
        setLanguageDropdown();

    }

    private void setLanguageDropdown() {
        List<String> languages = tvCreditsFacade.getLanguages();

        for (String s :
                languages) {
            languageDropdown.getItems().add(s);
        }
    }

    private void setGenreDropdown() {
        List<String> genres = tvCreditsFacade.getGenres();

        for (String s :
                genres) {
            genreDropdown1.getItems().add(s);
            genreDropdown2.getItems().add(s);
            genreDropdown3.getItems().add(s);
        }
    }

    private void setProductionTypeDropdown() {
        List<String> productionTypes = tvCreditsFacade.getProductionTypes();

        for (String s : productionTypes) {
            typeDropdown.getItems().add(s);
        }
    }

    @FXML
    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerLanding");
    }

    @FXML
    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }

    private boolean requiredFields(){
        return date.getValue() != null &&
                producent.getText() != null &&
                length.getText() != null &&
                genreDropdown1.getValue() != null &&
                typeDropdown.getValue() != null &&
                languageDropdown.getValue() != null &&
                title.getText() != null &&
                productionID.getText() != null &&
                date.getValue() != null;
    }

    public void saveProduction(ActionEvent actionEvent) throws IOException {

        if(requiredFields()) {
            //When button is pressed initialize a prodcution object with 3 arguments
            Production production = new Production(
                    productionID.getText(),
                    title.getText(),
                    //this maybe works for setting Date from calendar
                    Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            production.setLength(Integer.parseInt(length.getText()));

            //sets type
            production.setProductionType((String) typeDropdown.getValue());

            //sets language
            production.setLanguage((String) languageDropdown.getValue());

            //Sets genres from a dropdown through an array. This could be refactored in a method
            ArrayList<String> genres = new ArrayList<>();
            if (genreDropdown1.getValue() != null) {
                genres.add((String) genreDropdown1.getValue());
            }
            if (genreDropdown2.getValue() != null) {
                genres.add((String) genreDropdown2.getValue());
            }
            if (genreDropdown3.getValue() != null) {
                genres.add((String) genreDropdown3.getValue());
            }
            production.setGenres(genres);

            if (subtitles.isSelected()) {
                production.setSubtitle(true);
            } else {
                production.setSubtitle(false);
            }
            //maybe change this from subtitle?
            if (signLanguage.isSelected()) {
                production.setSignLanguage(true);
            } else {
                production.setSignLanguage(false);
            }

            production.setActive(false);
            production.setValidated(false);

            //saves production
            tvCreditsFacade.saveProduction(production);

            //sends user back to landing
            App.setRoot("producerLanding");
        }
    }

    public void switchToPrimary(ActionEvent actionEvent) throws IOException{
        App.setRoot("primary");
    }
}
