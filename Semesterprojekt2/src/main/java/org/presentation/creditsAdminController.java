package org.presentation;

import domain.Credit;
import domain.CreditName;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class creditsAdminController {

    public Button addCreditButton;
    public Button deleteCredit;
    public Button logoutButton;
    public Button saveCreditButton;
    public Label message;

    public TextField lastnameTextField;
    public TextField firstnameTextField;
    public TextField productionRefText;
    public TextField roleTextField;
    //public ListView listViewCredits;
    public ChoiceBox typeChoiceBox;
    public TableView tableViewCredits;


    private TvCreditsFacade tvCreditsFacade;
    private Production productionChosen;
    private ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();
        productionChosen = producerLandingController.getProductionChosen();

        productionRefText.setText((productionChosen.getProductionReference()));
        //addCreditButton.setDisable(true);

        setTableViewCredits();

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

        if (checkFormFields()) {
            //Create credit obj
            Credit credit = new Credit();
            CreditName associatedName = new CreditName();
            credit.setCreditName(associatedName);

            //set parameters
            credit.setCreditType((String) typeChoiceBox.getValue());
            credit.setFirstName(firstnameTextField.getText());
            credit.setLastName(lastnameTextField.getText());
            credit.setProductionId(productionChosen.getId());
            credit.setRole(roleTextField.getText());
            //save through singleton
            tvCreditsFacade.addCredit(credit);

            System.out.println("cr: " + credit);
            //show it to user
            tableViewCredits.getItems().add(credit);
            clearFormFields();
        }
    }

    public void deleteCredit(ActionEvent actionEvent) {
        //Find selected obj
        Object selectedItem = tableViewCredits.getSelectionModel().getSelectedItem();

        //delete through singleton
        tvCreditsFacade.deleteCredit((Credit) selectedItem);

        //delete from gui
        tableViewCredits.getItems().remove(selectedItem);
    }

    public void saveCredits(ActionEvent actionEvent) {

    }

    private void setTableViewCredits() {

        tableViewCredits.getColumns().clear();
        tableViewCredits.getItems().clear();

        // setting column header
        TableColumn<Credit, String> col1 = new TableColumn<>("Fornavn");
        // assigning getter from Credit Class to column
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Credit, String> col2 = new TableColumn<>("Efternavn");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Credit, String> col3 = new TableColumn<>("Rolle");
        col3.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Credit, String> col4 = new TableColumn<>("Krediteringstype");
        col4.setCellValueFactory(new PropertyValueFactory<>("creditType"));

        //adding columns to the tableview
        tableViewCredits.getColumns().clear();
        tableViewCredits.getColumns().add(col1);
        tableViewCredits.getColumns().add(col2);
        tableViewCredits.getColumns().add(col3);
        tableViewCredits.getColumns().add(col4);

        //adding data to the table view
        List<Credit> creditList = productionChosen.getCredits();
        tableViewCredits.getItems().addAll(creditList);
    }

    private boolean checkFormFields() {

        System.out.println("checks fields");
        boolean result = true;
        String setMessage = "";
        // Checks if all fields are complete and sets message if not
        if (lastnameTextField.getText().equals("")) {
            setMessage += "indtast efternavn\n";
            result = false;
        }
        if (firstnameTextField.getText().equals("")) {
            setMessage += "indtast fornavn\n";
            result = false;
        }
        if (roleTextField.getText().equals("")) {
            setMessage += "udfyld rolle\n";
            result = false;
        }
        if (typeChoiceBox.getValue().equals("")) {
            setMessage += "vælg krediteringstype\n";
            result = false;
        }

        message.setText(setMessage);
        return result;
    }

    private void clearFormFields(){
        message.setText("");
        lastnameTextField.setText("");
        firstnameTextField.setText("");
        roleTextField.setText("");
        typeChoiceBox.setValue("");

    }

}
