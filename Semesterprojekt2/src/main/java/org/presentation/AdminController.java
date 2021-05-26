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

public class AdminController extends TableViewInitializer{

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

    @FXML
    private void switchToAdminOops() throws IOException {
        App.setRoot("adminOops");
    }

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();

        setTableViewProductionBig(validationTableProductions);
        addProductions(tvCreditsFacade.getAllProductions());


        activateDoubleClick();

        validateProductionButton.setDisable(true);
        validateAllCreditsButton.setDisable(true);


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
                    setTableViewCredits(validationTableCredits);
                    addCreditsToTable();
                }
            });
            return row;
        });
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

}
