package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    private final ObservableList<Production> productionObservableList = FXCollections.observableArrayList();

    public void initialize() {
        tvCreditsFacade = TvCreditsFacade.getInstance();
        productionObservableList.addAll(TvCreditsFacade.getInstance().getAllProductions());
        setTableViewProduction();
        activateSearchbar();
        activateDoubleClick();
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
        tableView.getItems().addAll(productionObservableList);
    }

    public void selectProductionButtonHandler(ActionEvent actionEvent) {
        productionChosen = (Production) tableView.getSelectionModel().getSelectedItem();
        message.setText("");
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

    private void activateSearchbar() {

        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below

        FilteredList<Production> productionFilteredList = new FilteredList<>(productionObservableList, b -> true);


        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {

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
                } else if (production.getCompanyProductionName().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else return false;
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Production> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(tableView.comparatorProperty());
            //adding the filtered objects to the listview
            tableView.setItems(productionSortedList);

        });
    }

    private void activateDoubleClick() {

        tableView.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Production rowData = row.getItem();
                    System.out.println("Double clock on: " + rowData.getName());
                    productionChosen = rowData;
                    try {
                        App.setRoot("addCredits");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }
}
