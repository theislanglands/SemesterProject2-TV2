package org.presentation;

import java.io.IOException;
import java.util.Date;

import domain.Production;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminValidateController {

    @FXML
    TableView validationTableView;

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

    private void setTableViewProduction(){

//        private ArrayList<String> genre missing


        validationTableView.getColumns().clear();
        validationTableView.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Production, String> col1 = new TableColumn<>("Produktions ID");

        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Production, String> col2 = new TableColumn<>("Title");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Production, Date> col3 = new TableColumn<>("Release Date");
        col3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Production, String> col4 = new TableColumn<>("Type");
        col4.setCellValueFactory(new PropertyValueFactory<>("productionType"));

        TableColumn<Production, String> col5 = new TableColumn<>("Episode");
        col5.setCellValueFactory(new PropertyValueFactory<>("episode"));

        TableColumn<Production, String> col6 = new TableColumn<>("Season");
        col6.setCellValueFactory(new PropertyValueFactory<>("season"));

        TableColumn<Production, String> col7 = new TableColumn<>("Length");
        col7.setCellValueFactory(new PropertyValueFactory<>("length"));

        TableColumn<Production, String> col8 = new TableColumn<>("Language");
        col8.setCellValueFactory(new PropertyValueFactory<>("language"));

        TableColumn<Production, String> col9 = new TableColumn<>("Company");
        col9.setCellValueFactory(new PropertyValueFactory<>("companyProductionName"));

        //adding columns to the tableview

        validationTableView.getColumns().add(col1);
        validationTableView.getColumns().add(col2);
        validationTableView.getColumns().add(col3);
        validationTableView.getColumns().add(col4);
        validationTableView.getColumns().add(col5);
        validationTableView.getColumns().add(col6);
        validationTableView.getColumns().add(col7);
        validationTableView.getColumns().add(col8);
        validationTableView.getColumns().add(col9);


    }
}