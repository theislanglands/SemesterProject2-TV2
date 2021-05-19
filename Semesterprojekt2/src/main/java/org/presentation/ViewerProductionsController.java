package org.presentation;

import domain.Credit;
import domain.Production;
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
import javafx.scene.text.Text;

import java.io.IOException;

public class ViewerProductionsController {

    public TableView listviewProductions;
    public Text textProductionBio;
    public Text textFilmTitel;
    public Text textGenre1;
    public Text textGenre2;
    public Text textGenre3;
    public Text textLength;
    public Text textProductionCompany;
    public Text textYear;
    public Text textDirector;
    public TextField searchTableButton;
    private Production production;

    public static Credit creditChosen;
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

        //using the public static Production from other classes to know which production to show
        if(ViewerSearchController.productionChosen != null){
            production = ViewerSearchController.productionChosen;
            ViewerSearchController.productionChosen = null;
        }else if(ViewerCreditsController.productionChosen != null){
            production = ViewerCreditsController.productionChosen;
            ViewerCreditsController.productionChosen = null;
        }

        setTableViewCredits();
        creditObservableList.addAll(production.getCredits());
        listviewProductions.setItems(creditObservableList);




        textProductionBio.setText(production.getProductionBio());
        textFilmTitel.setText(production.getName());
        if(production.getGenres().size()>0){
            textGenre1.setText(production.getGenres().get(0));
            // textGenre2.setText(production.getGenres().get(1));
            //textGenre3.setText(production.getGenres().get(2));
        }

        textLength.setText(String.valueOf(production.getLength()));
        textProductionCompany.setText(production.getProductionCompanyName());
        textYear.setText(String.valueOf(production.getReleaseDate().getYear()));
        //textDirector.setText(production.getProducer());

        listviewProductions.setStyle("-fx-background-color:gray");

        activateDoubleClick();
        activateSearchbar();

    }


    private void setTableViewCredits(){

        listviewProductions.getColumns().clear();
        listviewProductions.getItems().clear();


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

        listviewProductions.getColumns().add(col1);
        listviewProductions.getColumns().add(col2);
        listviewProductions.getColumns().add(col3);
        listviewProductions.getColumns().add(col4);


    }

    private void activateDoubleClick(){

        listviewProductions.setRowFactory(tv -> {
            TableRow<Credit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
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


    private void activateSearchbar() {

        //These lists will contain all the objects from the "big" list (p/cObservableList) that return true in the filter below
        FilteredList<Credit> productionFilteredList = new FilteredList<>(creditObservableList, b -> true);


        //adding a listener to the searchBar
        //only newValue is used, not sure what the other 2 does
        searchTableButton.textProperty().addListener((observable, oldValue, newValue) -> {

            //this filters the productions based on the input
            productionFilteredList.setPredicate(credit -> {

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
            SortedList<Credit> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(listviewProductions.comparatorProperty());
            //adding the filtered objects to the listview
            listviewProductions.setItems(productionSortedList);

        });
    }
}
