package org.presentation;


import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class searchController {


    public Button search;
    public Button searchCredit;
    public Button searchProduction;
    public TableView tableView;

    public TextField searchBar;
    public Button searchButton;


    private TvCreditsFacade tvCreditsFacade;


    public void initialize(){
        tvCreditsFacade = TvCreditsFacade.getInstance();
        search.setDisable(true);

        //init on search production
        searchProduction.setDisable(true);
        //Sets up the TableView for production search
        setTableViewProduction();

    }

    public void switchToPrimary(ActionEvent actionEvent) {
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("productions");
    }

    public void searchCredit(ActionEvent actionEvent) {
        searchCredit.setDisable(true);
        searchProduction.setDisable(false);
        setTableViewCredits();
    }

    public void searchProduction(ActionEvent actionEvent) {
        searchProduction.setDisable(true);
        searchCredit.setDisable(false);
        setTableViewProduction();
    }

    public void search(ActionEvent actionEvent) {

        //missing implementation for search of credits. Maybe some sort of check of the ListView. not sure


        //gets text from the searchBar
        String searchString = searchBar.getText();
        List<Production> prods = tvCreditsFacade.getProductions();

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
        searchProduction.setDisable(false);
        searchCredit.setDisable(false);

        Object selected = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().clear();
        if(selected instanceof Production){

            //dont think this actually works atm. Maybe we have bad data, not sure

            List<Credit> credits = ((Production) selected).getCredits();
            setTableViewCredits();
            tableView.getItems().clear();
            tableView.getItems().add(credits);
        }else if(selected instanceof Credit){
            //Show Person
        }
    }

    private void setTableViewProduction(){

        tableView.getColumns().clear();
        tableView.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Production, String> col1 = new TableColumn<>("ID");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Production, String> col2 = new TableColumn<>("Title");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Production, Date> col3 = new TableColumn<>("Release Date");
        col3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        TableColumn<Production, String> col4 = new TableColumn<>("Length");
        col4.setCellValueFactory(new PropertyValueFactory<>("length"));

        TableColumn<Production, String> col5 = new TableColumn<>("Højde");
        col5.setCellValueFactory(new PropertyValueFactory<>("højde"));

        //adding columns to the tableview
        tableView.getColumns().clear();
        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);

        //adding data to the table view
        List<Production> productionList = tvCreditsFacade.getProductions();
        tableView.getItems().addAll(productionList);


    }

    private void setTableViewCredits(){

        tableView.getColumns().clear();
        tableView.getItems().clear();


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

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);

        //adding data to the table view
        List<Production> productionList = tvCreditsFacade.getProductions();
        List<Credit> credits = new ArrayList<>();
        for (Production prod :
                productionList) {
            credits.addAll(prod.getCredits());
            credits.add(null);
        }
        tableView.getItems().addAll(credits);

    }


}
