package org.presentation;


import domain.Production;
import domain.TvCredits;

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
import java.util.Locale;

public class searchController {


    public Button search;
    public Button searchCredit;
    public Button searchProduction;
    public TableView tableView;

    public TextField searchBar;
    public Button searchButton;


    private TvCredits tvCredits;


    public void initialize(){
        tvCredits = TvCredits.getInstance();
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
        //missing implementation
        //same procedure as searchProduction()

    }

    public void searchProduction(ActionEvent actionEvent) {
        setTableViewProduction();
    }

    public void search(ActionEvent actionEvent) {

        //missing implementation for search of credits. Maybe some sort of check of the ListView. not sure


        //gets text from the searchBar
        String searchString = searchBar.getText();
        List<Production> prods = tvCredits.getProductions();

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

    private void setTableViewProduction(){

        //missing columns

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

        //adding columns to the tableview
        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);

        //adding data to the table view
        List<Production> productionList = tvCredits.getProductions();
        tableView.getItems().addAll(productionList);


    }
}
