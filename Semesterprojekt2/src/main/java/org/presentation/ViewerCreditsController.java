package org.presentation;

import domain.Credit;

public class ViewerCreditsController {

    private Credit credit;

    public void initialize(){
        credit = ViewerSearchController.creditChosen;
    }
}
