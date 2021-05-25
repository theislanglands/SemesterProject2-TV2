package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AdminController {

    public Button adminLandingButton;
    public Button searchProductionsButton;
    public Button showAllButton;
    public Button selectValidateButton;
    public Button validateProductionButton;
    public Button validateCreditButton;
    public Button validateAllCreditsButton;
    public Button deleteCreditButton;

    public Label productionsTableHeader;
    public Label creditsTableHeader;
    public Button deleteProductionButton;

    public TableView validationTableProductions;
    public TableView validationTableCredits;

    private Credit credit;
    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade = TvCreditsFacade.getInstance();
    private ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToAdminValidate() throws IOException {
        App.setRoot("adminValidate");

    }

    @FXML
    private void switchToAdminLanding() throws IOException {
        App.setRoot("administration");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerLanding");
    }

    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();
        setTableViewProduction();
        addProductions(tvCreditsFacade.getAllProductions());
        validateProductionButton.setDisable(true);
        validateAllCreditsButton.setDisable(true);
        activateDoubleClick();
    }

    // ACTION HANDLERS!
    @FXML
    public void validateProductionButtonHandler(ActionEvent event) {
        //Checks if production has any unvalidated credits
        if (tvCreditsFacade.getUnValidatedCredits(productionChosen.getId()).size() == 0) {
            //Validates the chosen production
            tvCreditsFacade.validateProduction(productionChosen);
            //Removes the now validated production
            validationTableProductions.getItems().remove(productionChosen);
        }
    }

    @FXML
    public void validateCreditButtonHandler(ActionEvent event) {
        // find chosen credit
        int chosenIndex = validationTableCredits.getSelectionModel().getFocusedIndex();
        Credit chosenCredit = creditObservableList.get(chosenIndex);
        //Validates the chosen credit
        tvCreditsFacade.validateCredit(chosenCredit);
        //Removes unvalidated credit from observable list
        creditObservableList.remove(chosenIndex);
        //Removes unvalidated credit from table + disappears from GUI
        validationTableCredits.getItems().remove(chosenIndex);
    }

    @FXML
    public void validateAllCreditsButtonHandler(ActionEvent event) {
        //Observable list of all credits
        ObservableList<Credit> allCredits = validationTableCredits.getItems();
        creditObservableList = validationTableCredits.getSelectionModel().getSelectedItems();
        for (int i = 0; i < creditObservableList.size(); i++) {
            tvCreditsFacade.validateCredit(allCredits.get(i));
        }
        allCredits.removeAll(creditObservableList);
    }

    private void activateDoubleClick() {

        validationTableProductions.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    productionChosen = row.getItem();

                    // Initialize and add credits to credit table with credits beloning to productioinChosen
                    setTableViewCredits();
                    addCreditsToTable();
                }
            });
            return row;
        });
    }


    private void setTableViewProduction() {

//        private ArrayList<String> genre missing
        validationTableProductions.getColumns().clear();
        validationTableProductions.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Production, String> col1 = new TableColumn<>("Produktions Reference");

        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("productionReference"));

        TableColumn<Production, String> col2 = new TableColumn<>("Titel");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Production, Date> col3 = new TableColumn<>("Udgivelses Dato");
        col3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Production, String> col4 = new TableColumn<>("Type");
        col4.setCellValueFactory(new PropertyValueFactory<>("productionType"));

        TableColumn<Production, String> col5 = new TableColumn<>("Episode");
        col5.setCellValueFactory(new PropertyValueFactory<>("episode"));

        TableColumn<Production, String> col6 = new TableColumn<>("Sæson");
        col6.setCellValueFactory(new PropertyValueFactory<>("season"));

        TableColumn<Production, String> col7 = new TableColumn<>("Længde");
        col7.setCellValueFactory(new PropertyValueFactory<>("length"));

        TableColumn<Production, String> col8 = new TableColumn<>("Sprog");
        col8.setCellValueFactory(new PropertyValueFactory<>("language"));

        //adding columns to the tableview

        validationTableProductions.getColumns().add(col1);
        validationTableProductions.getColumns().add(col2);
        validationTableProductions.getColumns().add(col3);
        validationTableProductions.getColumns().add(col4);
        validationTableProductions.getColumns().add(col5);
        validationTableProductions.getColumns().add(col6);
        validationTableProductions.getColumns().add(col7);
        validationTableProductions.getColumns().add(col8);

    }

    private void addProductions(List<Production> productionList) {
        //Clears the tables
        validationTableProductions.getItems().clear();
        validationTableCredits.getItems().clear();

        //adding all unvalidated production to the table view
        for (Production prod : productionList) {
            validationTableProductions.getItems().add(prod);
        }
    }

    private void setTableViewCredits() {
        validationTableCredits.getColumns().clear();
        validationTableCredits.getItems().clear();

        // create columns and set contents
        TableColumn<Credit, String> col1 = new TableColumn<>("Fornavn");
        // setting values to cell by specified getter
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Credit, String> col2 = new TableColumn<>("Efternavn");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Credit, String> col3 = new TableColumn<>("Rolle");
        col3.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Credit, String> col4 = new TableColumn<>("Krediteringstype");
        col4.setCellValueFactory(new PropertyValueFactory<>("creditType"));

        //adding columns to the tableview
        validationTableCredits.getColumns().add(col1);
        validationTableCredits.getColumns().add(col2);
        validationTableCredits.getColumns().add(col3);
        validationTableCredits.getColumns().add(col4);
    }

    private void addCreditsToTable() {
        validationTableCredits.getItems().clear();

        for (Credit credit : productionChosen.getCredits()) {
            validationTableCredits.getItems().add(credit);
        }
    }

    public void deleteCreditButtonHandler(ActionEvent actionEvent) {
        // finding selected Credit in table
        Credit selectedItem = (Credit) validationTableCredits.getSelectionModel().getSelectedItem();
        //delete credit in system
        tvCreditsFacade.deleteCredit(selectedItem);
        //delete from GUI and updates the table
        validationTableCredits.getItems().remove(selectedItem);
    }

    public void deleteProductionButtonHandler(ActionEvent actionEvent) {
        // deleteProductinon in system
        tvCreditsFacade.deleteProduction(productionChosen);
        // update Gui
        validationTableProductions.getItems().remove(productionChosen);
        validationTableCredits.getItems().clear();
    }

    public void selectValidateButtonHandler(ActionEvent actionEvent) {
        //Allows user to select multiple credits in the creditTable
        validationTableCredits.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //Changes the titles for production and credit headers
        selectValidateButton.setStyle("-fx-border-width: 3px; -fx-border-color: white; -fx-background-color: black");
        showAllButton.setStyle("-fx-border-width: 1px; -fx-border-color: white; -fx-background-color: black");
        //Updates GUI
        addProductions(tvCreditsFacade.getUnValidatedProductions());
        validateProductionButton.setDisable(false);
        validateAllCreditsButton.setDisable(false);
    }

    public void showAllButtonHandler(ActionEvent actionEvent) {
        //Allows user to select only one credit in the creditTable
        validationTableCredits.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectValidateButton.setStyle("-fx-border-width: 1px; -fx-border-color: white; -fx-background-color: black");
        showAllButton.setStyle("-fx-border-width: 3px; -fx-border-color: white; -fx-background-color: black");
        addProductions(tvCreditsFacade.getAllProductions());
        //Disables all validateButtons
        validateProductionButton.setDisable(true);
        validateAllCreditsButton.setDisable(true);
    }
}
