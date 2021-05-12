package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCredits;
import domain.enums.CreditType;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addCreditsController {

    public Button deleteCredit;
    public Button showCredits;
    public Button activeCreditsButton;

    TvCredits tvCredits;

    public Button addCreditButton;
    public TextField productionIdText;
    public ChoiceBox roleChoiceBox;
    public TextField nameTextField;
    public Button addRoleButton;
    public ListView listViewRoles;



    public void initialize(){
        tvCredits = TvCredits.getInstance();

        addCreditButton.setDisable(true);
        activeCreditsButton.setDisable(true);

        //sets roles from enum. We need to rethink what we call role etc.
        roleChoiceBox.getItems().setAll(CreditType.getEnum());

    }


    public void switchToAddCredits(ActionEvent actionEvent) {
    }

    public void switchToAddProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("addProduction");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }


    //Mixing addRole and credit. Confusing to read

    public void addRole(ActionEvent actionEvent) {
        //Create credit obj
        Credit credit = new Credit();

        //set parameters
        credit.setCreditType((String) roleChoiceBox.getValue());
        credit.setRole(nameTextField.getText());

        //save through singleton
        tvCredits.addCredit(productionIdText.getText(), credit);

        //show it to user
        listViewRoles.getItems().add(credit);
    }

    public void deleteCredit(ActionEvent actionEvent) {
        //Find selected obj
        Object selectedItem = listViewRoles.getSelectionModel().getSelectedItem();

        //delete through singleton
        tvCredits.deleteCredit((Credit) selectedItem);

        //delete from gui
        listViewRoles.getItems().remove(selectedItem);
    }

    public void showCredit(ActionEvent actionEvent) {
        //Finds the production with productionID through singleton obj.
        Production production = tvCredits.getProduction(productionIdText.getText());

        //if production has credits show them in GUI
        if(production.getCredits() != null){
            listViewRoles.getItems().add("ID: " + production.getProductionReference());
            listViewRoles.getItems().addAll(production.getCredits());
        }

    }
}
