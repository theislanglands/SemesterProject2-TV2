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
                    //public static Production. Used from other controller, to know which production to show
                    productionChosen = row.getItem();
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
                    creditChosen = row.getItem();
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

}
