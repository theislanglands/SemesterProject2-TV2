package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TableViewInitializer {

    public void activateProductionSearchbar(TextField searchTableButton, ObservableList productionObservableList, TableView tableViewProductions) {


        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below
        FilteredList<Production> productionFilteredList = new FilteredList<>(productionObservableList, b -> true);


        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        searchTableButton.textProperty().addListener((observable, oldValue, newValue) -> {

            //this filters the productions based on the input
            productionFilteredList.setPredicate(production -> {

                //if no value has been put in, return true on every object
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchStringLowerCase = newValue.toLowerCase();

                //These check if the value of the object matches the search string
                //if they match they return true, which means they are added to the filtered list
                if (String.valueOf(production.getId()).toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else if (production.getName().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else if (production.getReleaseDate().toString().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else if (production.getProductionType().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else if (production.getLanguage().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else if (production.getProductionCompanyName().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else return false;
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Production> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(tableViewProductions.comparatorProperty());
            //adding the filtered objects to the listview
            tableViewProductions.setItems(productionSortedList);

        });
    }

    public void activateCreditSearchbar( TextField searchTableButton, ObservableList creditObservableList, TableView tableViewCredits ) {

        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below
        FilteredList<Credit> creditFilteredList = new FilteredList<>(creditObservableList, b -> true);


        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        searchTableButton.textProperty().addListener((observable, oldValue, newValue) -> {

            //this filters the productions based on the input
            creditFilteredList.setPredicate(credit -> {

                //if no value has been put in, return true on every object
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchStringLowerCase = newValue.toLowerCase();

                //These check if the value of the object matches the search string
                //if they match they return true, which means they are added to the filtered list

                if(credit.getFirstName().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(credit.getLastName().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(credit.getRole().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(credit.getCreditType().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Credit> productionSortedList = new SortedList<>(creditFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(tableViewCredits.comparatorProperty());
            //adding the filtered objects to the listview
            tableViewCredits.setItems(productionSortedList);

        });
    }

    public void setTableViewProduction(TableView tableViewProductions){



//        private ArrayList<String> genre missing


        tableViewProductions.getColumns().clear();
        tableViewProductions.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        //TableColumn<Production, String> col1 = new TableColumn<>("ID");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        // col1.setCellValueFactory(new PropertyValueFactory<>("id"));

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

        TableColumn<Production, String> col9 = new TableColumn<>("Udgiver");
        col9.setCellValueFactory(new PropertyValueFactory<>("productionCompanyName"));

        //adding columns to the tableview
        // tableViewProductions.getColumns().add(col1);
        tableViewProductions.getColumns().add(col2);
        tableViewProductions.getColumns().add(col3);
        tableViewProductions.getColumns().add(col4);
        tableViewProductions.getColumns().add(col5);
        tableViewProductions.getColumns().add(col6);
        tableViewProductions.getColumns().add(col7);
        tableViewProductions.getColumns().add(col8);
        tableViewProductions.getColumns().add(col9);


    }

    public void addProductions(TableView tableViewProductions, ObservableList productionObservableList, List<Production> productions){
        //adding data to the table view
        List<Production> productionList = productions;

        for (Production prod :
                productionList) {
            if(prod.isValidated()){
                productionObservableList.add(prod);
            }

        }
        //productionObservableList.addAll(productionList);

        tableViewProductions.setItems(productionObservableList);
    }




    public void setTableViewCredits(TableView tableViewCredits){

        tableViewCredits.getColumns().clear();
        tableViewCredits.getItems().clear();


        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Credit, String> col1 = new TableColumn<>("Fornavn");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Credit, String> col2 = new TableColumn<>("Efternavn");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Credit, String> col3 = new TableColumn<>("Rolle");
        col3.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<Credit, String> col4 = new TableColumn<>("Type");
        col4.setCellValueFactory(new PropertyValueFactory<>("creditType"));

        //adding columns to the tableview

        tableViewCredits.getColumns().add(col1);
        tableViewCredits.getColumns().add(col2);
        tableViewCredits.getColumns().add(col3);
        tableViewCredits.getColumns().add(col4);


    }

    public void addCredits(Production production, ObservableList creditObservableList, TableView tableViewCredits) {
        List<Credit> credits = production.getCredits();

        for (Credit cred :
                credits) {
            if(cred.isValidated()){
                creditObservableList.add(cred);
            }

        }

        tableViewCredits.setItems(creditObservableList);

    }

    public void addAllCredits(TableView tableViewCredits, ObservableList creditObservableList, List<Production> productions){
        //looping through each production and extracting each credit
        List<Production> productionList = productions;
        List<Credit> credits = new ArrayList<>();
        for (Production prod :
                productionList) {
            if(prod != null){
                for (Credit cred :
                        prod.getCredits()) {
                    if(cred.isValidated()){
                        credits.add(cred);
                    }
                }
                //credits.addAll(prod.getCredits());
            }
        }
        //adding to the master list
        creditObservableList.addAll(credits);
        //adding master list to the view
        tableViewCredits.getItems().addAll(creditObservableList);
    }
}
