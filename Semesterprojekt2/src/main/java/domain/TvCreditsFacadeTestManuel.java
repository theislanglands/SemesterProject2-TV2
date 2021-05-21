package domain;

import data.DataFacade;

import java.util.List;

public class TvCreditsFacadeTestManuel {

    public static void main(String[] args) {
        TvCreditsFacade tvCreditsFacade = TvCreditsFacade.getInstance();
        DataFacade dataFace = DataFacade.getInstance();

        dataFace.deleteProduction(5);

    }


}
