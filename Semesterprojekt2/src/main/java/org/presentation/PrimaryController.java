package org.presentation;

import java.io.IOException;
import java.util.Date;

import domain.Production;
import domain.TvCredits;
import javafx.fxml.FXML;

public class PrimaryController {

    TvCredits facade = new TvCredits();


    @FXML
    private void switchToSecondary() throws IOException {



        Production prod1 = facade.createProduction(2, "test", new Date());
        prod1.setId(5);

        App.setRoot("secondary");
    }
}
