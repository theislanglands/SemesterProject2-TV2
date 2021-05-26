package org.presentation;

import domain.Credit;
import domain.CreditName;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.*;

public class ProducerAddCreditController {

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
    public Button addCreditName;
    public TableView tableViewCreditName;
    public TextField searchFieldCreditName;


    private TvCreditsFacade tvCreditsFacade;
    private Production productionChosen;
    private ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();
    private ObservableList<CreditName> creditNameObservableList = FXCollections.observableArrayList();

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();
        productionChosen = ProducerLandingController.getProductionChosen();

        creditObservableList.setAll(productionChosen.getCredits());
        //hurtig implementering, bør optimeres
        creditNameObservableList.setAll(tvCreditsFacade.getAllCreditNames());

        removeExisting();

        productionRefText.setText((productionChosen.getProductionReference()));
        //sets types from database
        typeChoiceBox.getItems().setAll(tvCreditsFacade.getCreditTypes());

        message.setVisible(false);



        setTableViewCredits();
        setTableViewCreditNames();
        activateSearchbar();


    }

    private void removeExisting() {
        List<CreditName> existingCreditNames = new ArrayList<>();
        for (Credit cred :
                creditObservableList) {
            existingCreditNames.add(cred.getCreditName());
        }
        creditNameObservableList.removeAll(existingCreditNames);
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
    public void addPerson(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerAddCreditName");
    }


    public void addCredit(ActionEvent actionEvent) {

        if (checkFormFields()) {
            //Create credit obj
            Credit credit = new Credit();
            CreditName associatedName = (CreditName) tableViewCreditName.getSelectionModel().getSelectedItem();
            credit.setCreditName(associatedName);

            //set parameters
            credit.setCreditType((String) typeChoiceBox.getValue());

            credit.setProductionId(productionChosen.getId());
            credit.setRole(roleTextField.getText());

            //save through singleton
            tvCreditsFacade.addCreditToProduction(credit);

            //show it to user

            productionChosen.addCredit(credit);
            creditObservableList.add(credit);

            tableViewCredits.getItems().setAll(creditObservableList);
            clearFormFields();
        }
    }

    public void deleteCredit(ActionEvent actionEvent) {
        //Find selected obj
        Object selectedItem = tableViewCredits.getSelectionModel().getSelectedItem();

        //delete through singleton
        tvCreditsFacade.deleteCredit((Credit) selectedItem);

        //delete from gui
        creditObservableList.remove(selectedItem);
        tableViewCredits.getItems().setAll(creditObservableList);
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

        tableViewCredits.getItems().addAll(creditObservableList);
    }

    private void setTableViewCreditNames() {

        tableViewCreditName.getColumns().clear();
        tableViewCreditName.getItems().clear();

        // setting column header
        TableColumn<CreditName, String> col1 = new TableColumn<>("Fornavn");
        // assigning getter from Credit Class to column
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<CreditName, String> col2 = new TableColumn<>("Efternavn");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<CreditName, String> col3 = new TableColumn<>("Fødselsdato");
        col3.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        TableColumn<CreditName, String> col4 = new TableColumn<>("Email");
        col4.setCellValueFactory(new PropertyValueFactory<>("email"));

        //adding columns to the tableview

        tableViewCreditName.getColumns().add(col1);
        tableViewCreditName.getColumns().add(col2);
        tableViewCreditName.getColumns().add(col3);
        tableViewCreditName.getColumns().add(col4);

        //adding data to the table view

        tableViewCreditName.getItems().addAll(creditNameObservableList);
    }

    private boolean checkFormFields() {


        boolean result = true;
        String setMessage = "";
        // Checks if all fields are complete and sets message if not
        if (tableViewCreditName.getSelectionModel().getSelectedItem() == null) {
            setMessage += "Vælg person på listen\n";
            result = false;
            System.out.println("creditname");
        }
        if (roleTextField.getText().equals("")) {
            setMessage += "udfyld rolle\n";
            result = false;
            System.out.println("role");
        }
        if (typeChoiceBox.getValue().equals("")) {
            setMessage += "vælg krediteringstype\n";
            result = false;
            System.out.println("type");
        }


        message.setText(setMessage);
        return result;
    }

    private void clearFormFields(){
        message.setText("");
        roleTextField.setText("");
        typeChoiceBox.setValue("");
        searchFieldCreditName.setText("");

    }

    private void activateSearchbar() {

        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below
        FilteredList<CreditName> creditNameFilteredList = new FilteredList<>(creditNameObservableList, b -> true);


        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        searchFieldCreditName.textProperty().addListener((observable, oldValue, newValue) -> {

            //this filters the productions based on the input
            creditNameFilteredList.setPredicate(creditName -> {

                //if no value has been put in, return true on every object
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchStringLowerCase = newValue.toLowerCase();

                //These check if the value of the object matches the search string
                //if they match they return true, which means they are added to the filtered list
                if(creditName != null){
                    if ( String.valueOf(creditName.getId()).toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else if (creditName.getFirstName() != null && creditName.getFirstName().toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else if (creditName.getLastName() != null && creditName.getLastName().toString().toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else if (creditName.getAddress() != null && creditName.getAddress().toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else if (creditName.getEmail() != null && creditName.getEmail().toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else if (creditName.getBio() != null && creditName.getBio().toLowerCase().contains(searchStringLowerCase)) {
                        return true;
                    } else return false;
                }else return false;

            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<CreditName> creditNameSortedList = new SortedList<>(creditNameFilteredList);
            //no idea what this does
            creditNameSortedList.comparatorProperty().bind(tableViewCreditName.comparatorProperty());
            //adding the filtered objects to the listview
            tableViewCreditName.setItems(creditNameSortedList);

        });
    }


}
