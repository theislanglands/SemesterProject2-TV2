package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewerSearchController extends TableViewInitializer {
    public TableView tableViewProductions;
    public TableView tableViewCredits;
    public TextField textSearchBar;

    private TvCreditsFacade tvCreditsFacade;

    public static Production productionChosen;
    public static Credit creditChosen;
    public static String landingSearch = "";

    //These hold all data of the 2 lists.
    private final ObservableList<Production> productionObservableList = FXCollections.observableArrayList();
    private final ObservableList<Credit> creditObservableList = FXCollections.observableArrayList();

    @FXML
    private void switchToViewerLanding() throws IOException {
        App.setRoot("viewerLanding");
    }

    @FXML
    private void switchToViewerSearch() throws IOException {
        App.setRoot("viewerSearch");
    }

    @FXML
    private void switchToViewerOops() throws IOException {
        App.setRoot("viewerOops");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    public void initialize(){
        tvCreditsFacade = TvCreditsFacade.getInstance();

        setTableViewProduction(tableViewProductions);
        setTableViewCredits(tableViewCredits);

        addProductions(tableViewProductions, productionObservableList, tvCreditsFacade.getAllProductions());
        addAllCredits(tableViewCredits, creditObservableList, tvCreditsFacade.getAllProductions());

        activateCreditSearchbar(textSearchBar, creditObservableList, tableViewCredits);
        activateProductionSearchbar(textSearchBar, productionObservableList, tableViewProductions);

        activateDoubleClick();


        landingSearch = ViewerLandingController.landingSearch;
        textSearchBar.setText(landingSearch);

    }

    //Example from youtube: https://www.youtube.com/watch?v=FeTrcNBVWtg
    //same concept: https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/



    //activates the tableViews to allow doubleClick on cells to redirect to other page
    public void activateDoubleClick(){
        //production
        tableViewProductions.setRowFactory(tv -> {
            //setting up rows to be able to listen for clicks
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Production rowData = row.getItem();
                    System.out.println("Double clock on: " + rowData.getName());
                    //public static Production. Used from other controller, to know which production to show
                    productionChosen = rowData;
                    try {
                        App.setRoot("viewerProductions");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });

        //credit
        tableViewCredits.setRowFactory(tv -> {
            TableRow<Credit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Credit rowData = row.getItem();
                    System.out.println("Double clock on: " + rowData.getFirstName());
                    creditChosen = rowData;
                    try {
                        App.setRoot("viewerCredits");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });
    }

    public void switchToPrimary(MouseEvent mouseEvent) throws IOException {
        App.setRoot("primary");
    }



//----------------------Below this no longer in use-------------------------


    private void activateSearchbar() {

        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below
        FilteredList<Production> productionFilteredList = new FilteredList<>(productionObservableList, b -> true);
        FilteredList<Credit> creditFilteredList = new FilteredList<>(creditObservableList, b -> true);

        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        textSearchBar.textProperty().addListener((observable, oldValue, newValue) ->{

            //this filters the productions based on the input
            productionFilteredList.setPredicate(production -> {

                //if no value has been put in, return true on every object
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searchStringLowerCase = newValue.toLowerCase();

                //These check if the value of the object matches the search string
                //if they match they return true, which means they are added to the filtered list
                if(String.valueOf(production.getId()).toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(production.getName().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(production.getReleaseDate().toString().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(production.getProductionType().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(production.getLanguage().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else if(production.getProductionCompanyName().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else return false;
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Production> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(tableViewProductions.comparatorProperty());
            //adding the filtered objects to the listview
            tableViewProductions.setItems(productionSortedList);


            //This is the same as above, but for the credit list
            creditFilteredList.setPredicate(credit -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searchStringlowerCase = newValue.toLowerCase();


                if(credit.getFirstName().toLowerCase().contains(searchStringlowerCase)){
                    return true;
                }
                else if(credit.getLastName().toLowerCase().contains(searchStringlowerCase)){
                    return true;
                }
                else if(credit.getRole().toLowerCase().contains(searchStringlowerCase)){
                    return true;
                }
                else if(credit.getCreditType().toLowerCase().contains(searchStringlowerCase)){
                    return true;
                }
                else{
                    return false;
                }
            });

        } );

        SortedList<Credit> creditSortedList = new SortedList<>(creditFilteredList);
        creditSortedList.comparatorProperty().bind(tableViewCredits.comparatorProperty());
        tableViewCredits.setItems(creditSortedList);
    }


    private void setTableViewProduction(){

//        private ArrayList<String> genre missing


        tableViewProductions.getColumns().clear();
        tableViewProductions.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        // TableColumn<Production, String> col1 = new TableColumn<>("ID");
        //deciding what values go in the cells. Here it calls production.getId() to find value for the cell
        // col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Production, String> col2 = new TableColumn<>("Titel");
        col2.setCellValueFactory(new PropertyValueFactory<>("name"));

//        TableColumn<Production, Date> col3 = new TableColumn<>("Udgivelses Dato");
//        col3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

//        TableColumn<Production, String> col4 = new TableColumn<>("Type");
//        col4.setCellValueFactory(new PropertyValueFactory<>("productionType"));

        TableColumn<Production, String> col5 = new TableColumn<>("Episode");
        col5.setCellValueFactory(new PropertyValueFactory<>("episode"));

        TableColumn<Production, String> col6 = new TableColumn<>("Sæson");
        col6.setCellValueFactory(new PropertyValueFactory<>("season"));

//        TableColumn<Production, String> col7 = new TableColumn<>("Længde");
//        col7.setCellValueFactory(new PropertyValueFactory<>("length"));

//        TableColumn<Production, String> col8 = new TableColumn<>("Sprog");
//        col8.setCellValueFactory(new PropertyValueFactory<>("language"));

//        TableColumn<Production, String> col9 = new TableColumn<>("Udgiver");
//        col9.setCellValueFactory(new PropertyValueFactory<>("productionCompanyName"));

        //TableColumn<Production, ImageView> col10 = new TableColumn<>("image");
        //col10.setCellValueFactory(new PropertyValueFactory<>("image"));





        //adding columns to the tableview

        // tableViewProductions.getColumns().add(col1);
        tableViewProductions.getColumns().add(col2);
//        tableViewProductions.getColumns().add(col3);
//        tableViewProductions.getColumns().add(col4);
        tableViewProductions.getColumns().add(col5);
        tableViewProductions.getColumns().add(col6);
//        tableViewProductions.getColumns().add(col7);
//        tableViewProductions.getColumns().add(col8);
//        tableViewProductions.getColumns().add(col9);
        //tableViewProductions.getColumns().add(col10);


    }

    private void addAllProductions(){
        //adds all the productions to the master data list productionObservableList

        List<Production> allProductions = tvCreditsFacade.getAllProductions();
        for (Production prod :
                allProductions) {
            if(prod.isValidated()){
                productionObservableList.add(prod);
            }
        }

        //adding all data to the table view
        tableViewProductions.getItems().addAll(productionObservableList);
    }

    private void setTableViewCredits(){

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

    private void addAllCredits(){
        //looping through each production and extracting each credit
        List<Production> productionList = tvCreditsFacade.getAllProductions();
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


    //no longer in use. Was very, very, very slow
    private void searchProduction(String s){

        tableViewProductions.getItems().clear();

        //getting all productions to search through
        List<Production> productionList = tvCreditsFacade.getAllProductions();

        //productions that match will be added to this list
        List<Production> searchList = new ArrayList<>();

        //looping through all productions and adding if they match the search String
        for (Production prod :
                productionList) {

            //using toString from production, to lowerCase and replacing all whitespace
            //better user experience
            String productionInfo = prod.toString().toLowerCase().replaceAll("\\s","");

            //if serach String is a substring of production info add it to the list
            if(productionInfo.contains(s.toLowerCase())){
                searchList.add(prod);
            }
        }

        //if we ahve matches add them to the view
        if(!searchList.isEmpty()){
            tableViewProductions.getItems().addAll(searchList);
        }else{
            tableViewProductions.getItems().add(new Production());
        }

    }

    //no longer in use. Was very, very, very slow

    private void searchCredits(String s){

        tableViewCredits.getItems().clear();
        tableViewProductions.getItems().clear();

        //getting all productions
        List<Production> productionList = tvCreditsFacade.getAllProductions();
        //initializing list that will be filled with matches
        List<Credit> credits = new ArrayList<>();
        List<Production> productions = new ArrayList<>();

        //looping through all productions
        for (Production prod :
                productionList) {

            //using toString from production, to lowerCase and replacing all whitespace
            //better user experience

            String productionInfo = prod.toString().toLowerCase().replaceAll("\\s","");

            if(productionInfo.contains(s.toLowerCase())){
                //if the searchstring is a substring of production toString add all credits to the list
                credits.addAll(prod.getCredits());
                productions.add(prod);
                //avoiding dublicate credits
                //moves to next production
                continue;
            }

            //looping through all credits in the production
            for (Credit cred :
                    prod.getCredits()) {
                //same ass productionInfo, but for this specific credit
                String creditInfo = cred.toString().toLowerCase().replaceAll("\\s","");
                if (creditInfo.contains(s.toLowerCase())) {
                    credits.add(cred);
                }
            }

        }
        //if list is not empty add all to the view, else add an empty object
        if(!credits.isEmpty()){
            tableViewCredits.getItems().addAll(credits);
        }else{
            tableViewCredits.getItems().add(new Credit());
        }
        if(!productions.isEmpty()){
            tableViewProductions.getItems().addAll(productions);
        }else{
            tableViewProductions.getItems().add(new Production());
        }
    }

    //no longer in use, was very, very very slow
    public void search(KeyEvent keyEvent) {

        //on key pressed get the text, remove whitespace and search productions and credits for matches
        String s = textSearchBar.getText();
        s = s.replaceAll("\\s","");

        //searchCredits(s);
        //searchProduction(s);
    }


}
