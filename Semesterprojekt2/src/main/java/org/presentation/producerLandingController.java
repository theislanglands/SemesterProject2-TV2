package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class producerLandingController {
    public Button addCreditButton, addProductionButton, selectProductionButton, searchButton, logoutButton;

    public TableView tableView;
    public Label message;
    public TextField searchBar;

    public static Production getProductionChosen() {
        return productionChosen;
    }

    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade;

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();

        setTableViewProduction();
    }

    @FXML
    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerLanding");
    }

    @FXML
    public void switchToAddCredits(ActionEvent actionEvent) throws IOException {
        if (productionChosen != null){
            App.setRoot("addCredits");
        } else {
            message.setText("Vælg produktion først");
        }
    }

    @FXML
    public void switchToAddProduction(ActionEvent actionEvent) throws IOException {
        App.setRoot("addProduction");
    }

    private void setTableViewProduction() {

        tableView.getColumns().clear();
        tableView.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Production, String> col1 = new TableColumn<>("Reference");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("productionReference"));

        TableColumn<Production, String> col2 = new TableColumn<>("Titel");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Production, String> col3 = new TableColumn<>("Sæson");
        col3.setCellValueFactory(new PropertyValueFactory<>("season"));

        TableColumn<Production, String> col4 = new TableColumn<>("Episode");
        col4.setCellValueFactory(new PropertyValueFactory<>("episode"));

        TableColumn<Production, Date> col5 = new TableColumn<>("Udgivelses dato");
        col5.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        //adding columns to the tableview
        tableView.getColumns().clear();
        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);

        //adding data to the table view
        List<Production> productionList = tvCreditsFacade.getAllProductions();
        tableView.getItems().addAll(productionList);
    }

    public void selectProductionButtonHandler(ActionEvent actionEvent) {
        productionChosen = (Production) tableView.getSelectionModel().getSelectedItem();
    }

    public void search(ActionEvent actionEvent) {
        //missing implementation for search of credits. Maybe some sort of check of the ListView. not sure
        //gets text from the searchBar
        String searchString = searchBar.getText();
        List<Production> prods = tvCreditsFacade.getAllProductions();

        //initializing new array of productions that will match the search
        List<Production> newProds = new ArrayList<>();
        for (Production prod :
                prods) {
            //comparing toString of the production to the searchString. toLowerCase on both to make it caseInsensitive
            if(prod.toString().toLowerCase().contains(searchString.toLowerCase())){
                newProds.add(prod);
            }
        }

        //Clears the old data from before the search
        tableView.getItems().clear();
        //Displays new data that matches the search to the user
        tableView.getItems().addAll(newProds);
    }


    public void showSelected(ActionEvent actionEvent) {

    }
}
