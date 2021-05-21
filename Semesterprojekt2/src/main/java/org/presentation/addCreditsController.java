package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class addCreditsController {

    public Button addCreditButton;
    public Button deleteCredit;
    public Button logoutButton;
    public Button saveCreditButton;

    public TextField productionRefText;
    public ChoiceBox typeChoiceBox;
    public TextField roleTextField;
    public ListView listViewCredits;
    public TextField lastnameTextField;
    public TextField firstnameTextField;

    private TvCreditsFacade tvCreditsFacade;
    private Production productionChosen;
    private ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    public void initialize(){
        tvCreditsFacade = TvCreditsFacade.getInstance();
        productionChosen = producerLandingController.getProductionChosen();

        productionRefText.setText((productionChosen.getProductionReference()));
        addCreditButton.setDisable(true);


        creditObservableList.addAll(productionChosen.getCredits());
        listViewCredits.setItems(creditObservableList);

        //sets types from database
        typeChoiceBox.getItems().setAll(tvCreditsFacade.getCreditTypes());
    }

    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerLanding");
    }

    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }


    public void addCredit(ActionEvent actionEvent) {
        //Create credit obj
        Credit credit = new Credit();

        //set parameters
        credit.setCreditType((String) typeChoiceBox.getValue());
        credit.setFirstName(firstnameTextField.getText());
        credit.setLastName(lastnameTextField.getText());
        credit.setProductionId(productionChosen.getId());
        credit.setRole(roleTextField.getText());
        //save through singleton
        tvCreditsFacade.addCredit(credit);

        //show it to user
        listViewCredits.getItems().add(credit);
    }

    public void deleteCredit(ActionEvent actionEvent) {
        //Find selected obj
        Object selectedItem = listViewCredits.getSelectionModel().getSelectedItem();

        //delete through singleton
        tvCreditsFacade.deleteCredit((Credit) selectedItem);

        //delete from gui
        listViewCredits.getItems().remove(selectedItem);
    }


    public void saveCredits(ActionEvent actionEvent) {

    }
}
