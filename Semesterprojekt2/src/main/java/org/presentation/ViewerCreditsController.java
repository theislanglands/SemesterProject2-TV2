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
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ViewerCreditsController {

    public TableView listviewProductions;
    public TextField searchTableButton;

    private Credit credit;
    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade;

    public Text textName;
    public Text textPersonBio;
    public Text textPersonBirthplace;
    public Text textPersonBirthyear;
    public Text textRole;
    public Text textSurname;

    private final ObservableList<Production> productionObservableList = FXCollections.observableArrayList();

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

        //uses the public static objects from the other classes to know which credit to show
        if(ViewerSearchController.creditChosen != null){
            credit = ViewerSearchController.creditChosen;
            ViewerSearchController.creditChosen = null;
        }else if(ViewerProductionsController.creditChosen != null){
            credit = ViewerProductionsController.creditChosen;
            ViewerProductionsController.creditChosen = null;
        }

        setPageCredit();


        setTableViewProduction();
        addProductions();

        activateDoubleClick();
        activateSearchbar();
    }

    private void setPageCredit() {

        textName.setText(credit.getFirstName());
        textSurname.setText(credit.getLastName());
        textPersonBio.setText(credit.getCreditName().getBio());
        textPersonBirthplace.setText(credit.getCreditName().getCountry());
        textPersonBirthyear.setText(String.valueOf(credit.getCreditName().getDateOfBirth().getYear() + 1900));
        textRole.setText(credit.getCreditType());

    }

    private void activateSearchbar() {

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
                } else if (production.getCompanyProductionName().toLowerCase().contains(searchStringLowerCase)) {
                    return true;
                } else return false;
            });

            //Sorted list that is passed all objects of the filtered list. Dont know why
            SortedList<Production> productionSortedList = new SortedList<>(productionFilteredList);
            //no idea what this does
            productionSortedList.comparatorProperty().bind(listviewProductions.comparatorProperty());
            //adding the filtered objects to the listview
            listviewProductions.setItems(productionSortedList);

        });
    }

    private void activateDoubleClick() {

        listviewProductions.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Production rowData = row.getItem();
                    System.out.println("Double clock on: " + rowData.getName());
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
    }

    private void addProductions(){
        //adding data to the table view
        List<Production> productionList = tvCreditsFacade.getAllProductions();

        for (Production prod :
                productionList) {
            List<Credit> credits = prod.getCredits();
            for (Credit cred :
                    credits) {
                //works on firstName, but might put in too many people

                if(cred.getFirstName().equals(credit.getFirstName())){
                    productionObservableList.add(prod);
                    break;
                }
            }
        }
        listviewProductions.setItems(productionObservableList);
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
}
