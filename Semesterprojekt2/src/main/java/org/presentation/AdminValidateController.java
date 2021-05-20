package org.presentation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminValidateController {


    private Credit credit;
    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade = TvCreditsFacade.getInstance();
    private ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    @FXML
    TableView validationTableProductions, validationTableCredits;


    @FXML
    Button validateProductionButton, validateCreditButton, validateAllCreditsButton, approveButton;

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
        App.setRoot("search");
    }

    // ACTION HANDLERS!
    @FXML
    public void validateProductionButtonHandler(ActionEvent event) {
        System.out.println("validateProdButton");
        // check om alle krediteringer er valideret.
        //if prod_id = null -> validate
//        productionChosen;
    }

    @FXML
    public void validateCreditButtonHandler(ActionEvent event) {
        // find chosen credit
        int chosenIndex = validationTableCredits.getSelectionModel().getFocusedIndex();
        Credit chosenCredit = creditObservableList.get(chosenIndex);

        tvCreditsFacade.validateCredit(chosenCredit);
        //Removes unvalidated credit from observable list
        creditObservableList.remove(chosenIndex);
        //Removes unvalidated credit from table + also disappears from GUI
        validationTableCredits.getItems().remove(chosenIndex);
    }

    @FXML
    public void validateAllCreditsButtonHandler(ActionEvent event) {
        ObservableList<Credit> allCredits = validationTableCredits.getItems();
        creditObservableList = validationTableCredits.getSelectionModel().getSelectedItems();
        for (int i = 0; i < creditObservableList.size(); i++) {
            tvCreditsFacade.validateCredit(allCredits.get(i));
        }
        allCredits.removeAll(creditObservableList);
    }



    //TODO approveButton + change b

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();

        /*
        //uses the public static objects from the other classes to know which credit to show
        if(ViewerSearchController.creditChosen != null){
            credit = ViewerSearchController.creditChosen;
            ViewerSearchController.creditChosen = null;
        }else if(ViewerProductionsController.creditChosen != null){
            credit = ViewerProductionsController.creditChosen;
            ViewerProductionsController.creditChosen = null;
        }

         */

        setTableViewProduction();
        addProductions();
        //addUnvalidatedCredits();
        activateDoubleClick();
        validationTableCredits.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void activateDoubleClick() {

        validationTableProductions.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Production rowData = row.getItem();

                    System.out.println("Double clock on: " + rowData.getName());
                    productionChosen = rowData;

                    addUnvalidatedCredits();
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

//        loadedColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
//        columns.add(loadedColumn);

        //    TableColumn<Production, String> col9 = new TableColumn<>("Produktionsselskab");
        //   col9.setCellValueFactory(new PropertyValueFactory<>("companyProductionName"));


        //adding columns to the tableview

        validationTableProductions.getColumns().add(col1);
        validationTableProductions.getColumns().add(col2);
        validationTableProductions.getColumns().add(col3);
        validationTableProductions.getColumns().add(col4);
        validationTableProductions.getColumns().add(col5);
        validationTableProductions.getColumns().add(col6);
        validationTableProductions.getColumns().add(col7);
        validationTableProductions.getColumns().add(col8);
        // validationTableView.getColumns().add(col9);
        // validationTableView.getColumns().add(col10);
    }

    private void addProductions() {
        //adding all unvalidated production to the table view
        List<Production> productionList = tvCreditsFacade.getUnValidatedProductions();
        for (Production prod : productionList) {
            validationTableProductions.getItems().add(prod);
        }
    }

    private void setTableViewCredits() {

        validationTableCredits.getColumns().clear();
        validationTableCredits.getItems().clear();


        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Credit, String> col1 = new TableColumn<>("First Name");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Credit, String> col2 = new TableColumn<>("Last Name");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Credit, String> col3 = new TableColumn<>("Role");
        col3.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Credit, String> col4 = new TableColumn<>("Credit Type");
        col4.setCellValueFactory(new PropertyValueFactory<>("creditType"));

        //adding columns to the tableview

        validationTableCredits.getColumns().add(col1);
        validationTableCredits.getColumns().add(col2);
        validationTableCredits.getColumns().add(col3);
        validationTableCredits.getColumns().add(col4);

    }


    private void addUnvalidatedCredits() {

        // System.out.println("prod id " + productionChosen.getId());

        if (creditObservableList.isEmpty()) {
            List<Credit> credits = tvCreditsFacade.getUnValidatedCredits(productionChosen.getId());
            //adding to the master list
            creditObservableList.removeAll();
            creditObservableList.addAll(credits);
            //adding master list to the view

            setTableViewCredits();
            validationTableCredits.getItems().addAll(creditObservableList);
        }

    }
}
