package org.presentation;

import domain.Credit;
import domain.Production;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class ViewerProductionsController extends TableViewInitializer {

    public TableView tableViewCredits;
    public Text textProductionBio;
    public Text textFilmTitel;
    public Text textGenre1;
    public Text textGenre2;
    public Text textGenre3;
    public Text textLength;
    public Text textProductionCompany;
    public Text textYear;
    public Text textDirector;
    public TextField textSearchBar;
    private Production production;
    public ImageView productionImage;

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

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToViewerOops() throws IOException {
        App.setRoot("viewerOops");
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

        setTableViewCredits(tableViewCredits);
        addCredits(production, creditObservableList, tableViewCredits);
        activateCreditSearchbar(textSearchBar, creditObservableList, tableViewCredits);

        activateDoubleClick();
        setPageProduction();
    }

    private void setPageProduction(){

        textProductionBio.setText(production.getProductionBio());
        textFilmTitel.setText(production.getName());

        if(production.getGenres().size()>0){
            textGenre1.setText(production.getGenres().get(0));
        }

        textLength.setText(String.valueOf(production.getLength()));
        textProductionCompany.setText(production.getProductionCompanyName());
        textYear.setText(String.valueOf(production.getReleaseDate().getYear() + 1900));

        tableViewCredits.setStyle("-fx-background-color:gray");

        if(production.getImageUrl() != null){
            productionImage.setImage(new Image(production.getImageUrl()));
        }else{
            productionImage.setImage(new Image("http://rannok.dk/TVCredits/production_images/Unknown_production.jpg"));
        }
    }



    private void activateDoubleClick(){

        tableViewCredits.setRowFactory(tv -> {
            TableRow<Credit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
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
