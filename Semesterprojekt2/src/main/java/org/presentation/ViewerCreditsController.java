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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ViewerCreditsController extends TableViewInitializer{

    public TableView tableViewProductions;
    public TextField searchTextField;

    private Credit credit;
    public static Production productionChosen;
    private TvCreditsFacade tvCreditsFacade;

    public Text textName;
    public Text textPersonBio;
    public Text textPersonBirthplace;
    public Text textPersonBirthyear;
    public Text textRole;
    public Text textSurname;
    public ImageView creditImage;

    private final ObservableList<Production> productionObservableList = FXCollections.observableArrayList();

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
        tvCreditsFacade = TvCreditsFacade.getInstance();

        //uses the public static objects from the other classes to know which credit to show
        if(ViewerSearchController.creditChosen != null){
            credit = ViewerSearchController.creditChosen;
            ViewerSearchController.creditChosen = null;
        }else if(ViewerProductionsController.creditChosen != null){
            credit = ViewerProductionsController.creditChosen;
            ViewerProductionsController.creditChosen = null;
        }

        //abstract class TableViewInitializer for shared functionality
        setTableViewProduction(tableViewProductions);
        addProductions(tableViewProductions, productionObservableList, tvCreditsFacade.getProductionsFromCreditName(credit.getCreditName().getId()));
        activateProductionSearchbar(searchTextField, productionObservableList, tableViewProductions);

        activateDoubleClick();
        setPageCredit();
    }

    //sets the left side of the page with info about the credit in question
    private void setPageCredit() {
        textName.setText(credit.getFirstName());
        textSurname.setText(credit.getLastName());
        textPersonBio.setText(credit.getCreditName().getBio());
        textPersonBirthplace.setText(credit.getCreditName().getCountry());
        textPersonBirthyear.setText(String.valueOf(credit.getCreditName().getDateOfBirth().getYear() + 1900));
        textRole.setText(credit.getCreditType());
        creditImage.setImage(new Image(credit.getImageUrl()));
    }

    private void activateDoubleClick() {
        tableViewProductions.setRowFactory(tv -> {
            TableRow<Production> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
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
    }

    public void switchToPrimary(MouseEvent mouseEvent) throws IOException {
        App.setRoot("primary");
    }
}
