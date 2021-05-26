package org.presentation;

import domain.CreditName;
import domain.TvCreditsFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class ProducerAddCreditNameController {


    public TextArea firstNameText;
    public TextArea lastNameText;
    public TextArea addressText;
    public DatePicker dateOfBirth;
    public TextArea bioText;
    public Button saveButton;
    public TextArea emailText;
    public TextArea countryText;
    public Label message;

    private TvCreditsFacade tvCreditsFacade;

    public void initialize(){

        tvCreditsFacade = TvCreditsFacade.getInstance();
        message.setVisible(false);

    }

    public void saveCreditName(ActionEvent actionEvent) throws IOException {
        if(requiredFields()){
            CreditName creditName = new CreditName();
            creditName.setFirstName(firstNameText.getText());
            creditName.setLastName(lastNameText.getText());
            creditName.setDateOfBirth(Date.from(dateOfBirth.getValue().atStartOfDay((ZoneId.systemDefault())).toInstant()));
            creditName.setAddress(addressText.getText());
            creditName.setEmail(emailText.getText());
            creditName.setCountry(countryText.getText());
            creditName.setBio(bioText.getText());

            tvCreditsFacade.addCreditName(creditName);

            message.setText(creditName.getFirstName() + " " + creditName.getLastName() + " er tilføjet.");
            App.setRoot("addCredits");
        }
        message.setVisible(true);
    }

    private boolean requiredFields() {
        return !firstNameText.getText().isEmpty() && !lastNameText.getText().isEmpty() && dateOfBirth.getValue() != null;
    }

    @FXML
    public void switchToAddCredit(ActionEvent actionEvent) throws IOException {
        App.setRoot("addCredits");
    }

    @FXML
    public void switchToPrimary(ActionEvent actionEvent) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    public void switchToViewer(ActionEvent actionEvent) throws IOException {
        App.setRoot("viewerLanding");
    }

    public void switchToProductions(ActionEvent actionEvent) throws IOException {
        App.setRoot("producerLanding");
    }
}
