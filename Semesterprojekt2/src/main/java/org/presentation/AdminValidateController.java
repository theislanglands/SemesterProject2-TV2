package org.presentation;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminValidateController {


    private Credit credit;
    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade = TvCreditsFacade.getInstance();
    private final ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    @FXML
    TableView validationTableView, validationTableView2;


    @FXML
    Button adminLandingButton, searchProductionsButton, invalidateButton, selectNoneButton, approveButton, validateButton, selectAllButton, changeProductionButton, changeCreditButton, addProductionButton, addCreditButton;

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

    public void initialize(){
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

    }

    private void activateDoubleClick() {

        validationTableView.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Production rowData = row.getItem();

                    System.out.println("Double clock on: " + rowData.getName());
                    productionChosen = rowData;

                    setTableViewCredits();
                    addUnvalidatedCredits();
                }
            });
            return row ;
        });
    }





    private void setTableViewProduction(){

//        private ArrayList<String> genre missing


        validationTableView.getColumns().clear();
        validationTableView.getItems().clear();

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

        validationTableView.getColumns().add(col1);
        validationTableView.getColumns().add(col2);
        validationTableView.getColumns().add(col3);
        validationTableView.getColumns().add(col4);
        validationTableView.getColumns().add(col5);
        validationTableView.getColumns().add(col6);
        validationTableView.getColumns().add(col7);
        validationTableView.getColumns().add(col8);
        // validationTableView.getColumns().add(col9);
        // validationTableView.getColumns().add(col10);
    }

    private void addProductions(){
        //adding all unvalidated production to the table view
        List<Production> productionList = tvCreditsFacade.getUnValidatedProductions();
        for (Production prod : productionList) {
            validationTableView.getItems().add(prod);
        }
    }

    private void setTableViewCredits(){

        validationTableView2.getColumns().clear();
        validationTableView2.getItems().clear();


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

        validationTableView2.getColumns().add(col1);
        validationTableView2.getColumns().add(col2);
        validationTableView2.getColumns().add(col3);
        validationTableView2.getColumns().add(col4);

    }



    private void addUnvalidatedCredits(){

        System.out.println("prod id " + productionChosen.getId());

        List<Credit> credits = tvCreditsFacade.getUnValidatedCredits(productionChosen.getId());

        //adding to the master list
        creditObservableList.addAll(credits);
        //adding master list to the view
        validationTableView2.getItems().addAll(creditObservableList);


    }
}
