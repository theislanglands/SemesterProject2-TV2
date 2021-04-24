package org.presentation;

import domain.Credit;
import domain.Person;
import domain.Production;
import domain.TvCredits;
import domain.enums.CreditType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addCreditsController {

    public Button deleteCredit;
    public Button showCredits;
    TvCredits tvCredits;

    public Button addCreditButton;
    public TextField productionIdText;
    public ChoiceBox roleChoiceBox;
    public TextField nameTextField;
    public Button addRoleButton;
    public ListView listViewRoles;

    public void saveButtonHandler(ActionEvent actionEvent) {
        Person myPerson = new Person(nameTextField.getText());
        Credit myCredit = new Credit(myPerson, roleChoiceBox.getValue().toString());
        
    }


    public void initialize(){
        addCreditButton.setDisable(true);
        roleChoiceBox.getItems().setAll(CreditType.getEnum());
        tvCredits = TvCredits.getInstance();
    }


    public void switchToAddCredits(ActionEvent actionEvent) {
    }

    public void switchToAddProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("addProduction");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }


    public void addRole(ActionEvent actionEvent) {
        Credit credit = new Credit();
        credit.setCreditType((CreditType) roleChoiceBox.getValue());
        credit.setRole(nameTextField.getText());
        tvCredits.addCredit(productionIdText.getText(), credit);
        listViewRoles.getItems().add(credit);

    }

    public void deleteCredit(ActionEvent actionEvent) {
        Object selectedItem = listViewRoles.getSelectionModel().getSelectedItem();
        tvCredits.deleteCredit((Credit) selectedItem);
        listViewRoles.getItems().remove(selectedItem);
    }

    public void showCredit(ActionEvent actionEvent) {
        Production production = tvCredits.getProduction(productionIdText.getText());
        
        listViewRoles.getItems().addAll(production.getCredits());
    }
}
