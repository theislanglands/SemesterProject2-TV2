package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCreditsFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewerSearchController {
    public TableView listviewProductions;
    public TableView listviewCredits;
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

    public void initialize(){
        tvCreditsFacade = TvCreditsFacade.getInstance();

        setTableViewProduction();
        setTableViewCredits();

        addAllProductions();
        addAllCredits();

        activateDoubleClick();



        activateSearchbar();

        landingSearch = ViewerLandingController.landingSearch;

        textSearchBar.setText(landingSearch);

    }

    //Example from youtube: https://www.youtube.com/watch?v=FeTrcNBVWtg
    //same concept: https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/

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
                else if(production.getCompanyProductionName().toLowerCase().contains(searchStringLowerCase)){
                    return true;
                }
                else return false;
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Production> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(listviewProductions.comparatorProperty());
            //adding the filtered objects to the listview
            listviewProductions.setItems(productionSortedList);


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
        creditSortedList.comparatorProperty().bind(listviewCredits.comparatorProperty());
        listviewCredits.setItems(creditSortedList);
    }


    private void setTableViewProduction(){

//        private ArrayList<String> genre missing


        listviewProductions.getColumns().clear();
        listviewProductions.getItems().clear();

        //creates a new column in the TableView with header "ID", type Production and cellValue String
        TableColumn<Production, String> col1 = new TableColumn<>("ID");
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

        listviewProductions.getColumns().add(col1);
        listviewProductions.getColumns().add(col2);
        listviewProductions.getColumns().add(col3);
        listviewProductions.getColumns().add(col4);
        listviewProductions.getColumns().add(col5);
        listviewProductions.getColumns().add(col6);
        listviewProductions.getColumns().add(col7);
        listviewProductions.getColumns().add(col8);
        listviewProductions.getColumns().add(col9);


    }

    private void addAllProductions(){
        //adds all the productions to the master data list productionObservableList
        productionObservableList.addAll(tvCreditsFacade.getAllProductions());
        //adding all data to the table view
        listviewProductions.getItems().addAll(productionObservableList);
    }

    private void setTableViewCredits(){

        listviewCredits.getColumns().clear();
        listviewCredits.getItems().clear();


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

        listviewCredits.getColumns().add(col1);
        listviewCredits.getColumns().add(col2);
        listviewCredits.getColumns().add(col3);
        listviewCredits.getColumns().add(col4);


    }

    private void addAllCredits(){
        //looping through each production and extracting each credit
        List<Production> productionList = tvCreditsFacade.getAllProductions();
        List<Credit> credits = new ArrayList<>();
        for (Production prod :
                productionList) {
            if(prod != null){
                credits.addAll(prod.getCredits());
            }
        }
        //adding to the master list
        creditObservableList.addAll(credits);
        //adding master list to the view
        listviewCredits.getItems().addAll(creditObservableList);
    }

    //activates the tableViews to allow doubleClick on cells to redirect to other page
    public void activateDoubleClick(){
        //production
        listviewProductions.setRowFactory(tv -> {
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
        listviewCredits.setRowFactory(tv -> {
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















    //no longer in use. Was very, very, very slow
    private void searchProduction(String s){

        listviewProductions.getItems().clear();

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
            listviewProductions.getItems().addAll(searchList);
        }else{
            listviewProductions.getItems().add(new Production());
        }

    }

    //no longer in use. Was very, very, very slow

    private void searchCredits(String s){

        listviewCredits.getItems().clear();
        listviewProductions.getItems().clear();

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
            listviewCredits.getItems().addAll(credits);
        }else{
            listviewCredits.getItems().add(new Credit());
        }
        if(!productions.isEmpty()){
            listviewProductions.getItems().addAll(productions);
        }else{
            listviewProductions.getItems().add(new Production());
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
