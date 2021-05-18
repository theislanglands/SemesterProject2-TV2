package domain;

import java.util.List;

public class TvCreditsFacadeTest {


    public static void main(java.lang.String[] args) {

        TvCreditsFacade tvCreditsFacade = TvCreditsFacade.getInstance();

        List<Production> testProductions = tvCreditsFacade.getAllProductions();
        System.out.println(testProductions);
    }
}
