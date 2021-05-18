package org.presentation;

import domain.Credit;
import domain.Production;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

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
    private Production production;

    public static Credit creditChosen;


    public void initialize(){

        if(ViewerSearchController.productionChosen != null){
            production = ViewerSearchController.productionChosen;
            ViewerSearchController.productionChosen = null;
        }else if(ViewerCreditsController.productionChosen != null){
            production = ViewerCreditsController.productionChosen;
            ViewerCreditsController.productionChosen = null;
        }

        setTableViewCredits();
        listviewProductions.getItems().addAll(production.getCredits());
        textProductionBio.setText(production.getProductionBio());
        textFilmTitel.setText(production.getName());
        textGenre1.setText(production.getGenres().get(0));
       // textGenre2.setText(production.getGenres().get(1));
        //textGenre3.setText(production.getGenres().get(2));
        textLength.setText(String.valueOf(production.getLength()));
        textProductionCompany.setText(production.getProductionCompanyName());
        textYear.setText(String.valueOf(production.getReleaseDate().getYear()));
        //textDirector.setText(production.getProducer());

        listviewProductions.setStyle("-fx-background-color:gray");

        activateDoubleClick();

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
}
