package org.presentation;

import domain.Credit;
import domain.Person;
import domain.Production;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addCreditsController {

    public Button addCreditButton;
    public TextField productionIdText;
    public ChoiceBox roleChoiceBox;
    public TextField nameTextField;
    public Button addRoleButton;

    public void saveButtonHandler(ActionEvent actionEvent) {
        Person myPerson = new Person(nameTextField.getText());
        Credit myCredit = new Credit(myPerson, roleChoiceBox.getValue());
        
    }


    public void initialize(){
        addCreditButton.setDisable(true);
    }


    public void switchToAddCredits(ActionEvent actionEvent) {
    }

    public void switchToAddProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("addProduction");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }
}
