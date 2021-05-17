package org.presentation;

import domain.Credit;
import domain.Production;
import domain.TvCredits;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ViewerCreditsController {

    public TableView listviewProductions;
    private Credit credit;
    public static Production productionChosen;
    private TvCredits tvCredits;

    public void initialize(){
        tvCredits = TvCredits.getInstance();

        if(ViewerSearchController.creditChosen != null){
            credit = ViewerSearchController.creditChosen;
            ViewerSearchController.creditChosen = null;
        }else if(ViewerProductionsController.creditChosen != null){
            credit = ViewerProductionsController.creditChosen;
            ViewerProductionsController.creditChosen = null;
        }


        setTableViewProduction();
        addProductions();

        activateDoubleClick();


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
        List<Production> productionList = tvCredits.getProductions();

        for (Production prod :
                productionList) {
            List<Credit> credits = prod.getCredits();
            for (Credit cred :
                    credits) {
                //works on firstName, but might put in too many people
                if(cred.getFirstName().equals(credit.getFirstName())){
                    listviewProductions.getItems().add(prod);
                    break;
                }
            }
        }

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
