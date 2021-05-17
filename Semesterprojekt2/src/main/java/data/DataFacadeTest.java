package data;

import domain.CreditName;
import domain.Production;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFacadeTest {

    public static void main(String[] args) {
        // ** DATA FACADE TEST CENTER ** //

        // instants af datafacade
        DataFacade dbFacade = DataFacade.getInstance();

        // 1 test af getProduction
        Production test = dbFacade.getProduction(1);
        //System.out.println(test);

        // 2 test af getProductions
        List<Production> productionTest = dbFacade.getProductions();
        //System.out.println(productionTest);

        // test af deleteProduction - VIRKER!
        /*
        dbFacade.deleteProduction(1);
        test = dbFacade.getProduction(1);
        System.out.println(test);
         */

        // test af update produktion - VIRKER!
        // Opretter produktion der skal ersttte den gamle
        Production badehotellet = new Production();
        badehotellet.setProductionReference("SF666");
        badehotellet.setName("Badehotellet");
        badehotellet.setSeason(2);
        badehotellet.setEpisode(5);
        badehotellet.setReleaseDate(new Date(100000));
        badehotellet.setLength(46);
        badehotellet.setSubtitle(true);
        badehotellet.setSignLanguage(false);
        badehotellet.setActive(true);
        badehotellet.setValidated(true);
        badehotellet.setLanguage("Dansk");
        badehotellet.setProductionBio("En ny spændende sæson af badehotellet");
        badehotellet.setType("Serie");
        badehotellet.setCompanyProductionName("SF Film Production ApS");
        badehotellet.setProductionType("Serie");

        System.out.println(badehotellet);
        dbFacade.updateProduction(1, badehotellet);
        test = dbFacade.getProduction(1);
        System.out.println(test);

        // delete production test
        dbFacade.deleteProduction(7);

        // test af createProduction()
        Production badehotelletWrong = new Production();
        badehotelletWrong.setProductionReference("WRONG123");
        badehotelletWrong.setName("Badehotellet");
        badehotelletWrong.setSeason(99);
        badehotelletWrong.setEpisode(99);
        badehotelletWrong.setReleaseDate(new Date(100000));
        badehotelletWrong.setLength(99);
        badehotelletWrong.setSubtitle(true);
        badehotelletWrong.setSignLanguage(false);
        badehotelletWrong.setActive(true);
        badehotelletWrong.setValidated(true);
        badehotelletWrong.setLanguage("Dansk");
        badehotelletWrong.setProductionBio("En ny spændende sæson af badehotellet");
        badehotelletWrong.setType("Serie");
        badehotelletWrong.setCompanyProductionName("SF Film Production ApS");
        badehotelletWrong.setProductionType("Serie");

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Thriller");
        genres.add("Animation");
        badehotelletWrong.setGenres(genres);

        System.out.println("\n\nTester createProduction()");
        dbFacade.createProduction(badehotelletWrong);


        // Test af getProductions og print til konsol
        System.out.println("\n\n tester getProduction");
        System.out.println(dbFacade.getProductions());





        // test af deleteCreditName
        //dbFacade.deleteCreditName(1);

        // test af updateCredit()
//        CreditName creditName = new CreditName();
//        creditName.setFirstName("First Name (TEST)");
//        creditName.setLastName("Last Name (TEST)");
//        creditName.setPhone(99999999);
//        creditName.setEmail("testperson@testperson.dk");
//
//        dbFacade.createCreditName(creditName);


        // tester getCredit
        //Credit testGetCredit = dbFacade.getCredit(4);
        //System.out.println("\ntester hent af kreditering fra db");
        //System.out.println(testGetCredit);

        // tester getCredits
        //List<Credit> testList = dbFacade.getCredits(1);
        //System.out.println("\ntester liste af credits");
        //System.out.println(testList);

        //Tester getCreditName
        System.out.println("Tester getCreditNames");
        List<CreditName> testName = dbFacade.getCreditNames();
        for (CreditName i : testName) {
            System.out.println(i);
        }






        /* KOPIERET FRA GAMLE DATA MAIN

        public void createTestProductions() {
            // Opretter produktion 1: "Badehotellet"

            // Opretter personer
            CreditName creditName1 = new CreditName();
            creditName1.setId(1234);
            creditName1.setFirstName("Bodil");
            creditName1.setLastName("Jørgensen");
            creditName1.setAddress(null);
            creditName1.setPhone("65926104");
            creditName1.setEmail("BodilJoergensen@badehotellet.dk");

            CreditName creditName2 = new CreditName(1235, "Amalie", "Dollerup", "Bamsevej 4, 5600", "27201117", "AmalieDollerup@badehotellet.dk");

            // Opretter krediteringer
            Credit credit1 = new Credit();
            credit1.setRole("Far til Simon");
            credit1.setValidated(true);
            credit1.setPerson(creditName1);

            Credit credit2 = new Credit(creditName2, "Mor til Hans", "CreditType.Medvirkende");

            // Opretter liste med krediteringer
            ArrayList<Credit> badehotelletCredits = new ArrayList<>();
            badehotelletCredits.add(credit1);
            badehotelletCredits.add(credit2);


            // Opretter produktion
            Production badehotellet = new Production();
            badehotellet.setProductionReference("1");
            badehotellet.setName("Badehotellet");
            badehotellet.setReleaseDate(new Date(1000));
            badehotellet.setLength(42);
            badehotellet.setSubtitle(true);
            badehotellet.setSignLanguage(false);
            badehotellet.setCredits(badehotelletCredits);
            badehotellet.setActive(true);
            badehotellet.setValidated(true);

            //Opretter personer
            CreditName creditName3 = new CreditName();
            creditName3.setId(1236);
            creditName3.setFirstName("Jacob");
            creditName3.setLastName("Jacobsen");
            creditName3.setAddress("København");
            creditName3.setPhone("20568095");
            creditName3.setEmail("JacobJacobsen@DateMigNoegen.dk");

            CreditName creditName4 = new CreditName(1237, "Laura", "Laurasen", "Vejle", "52674582", "LauraLaurasen@DateMigNoegen.dk");

            // Opretter krediteringer
            Credit credit3 = new Credit();
            credit3.setRole("Medvirkende");
            credit3.setValidated(true);
            credit3.setPerson(creditName3);

            Credit credit4 = new Credit(creditName4, "En ny rolle", "CreditType.Medvirkende");

            // Oprette liste med krediteringer
            ArrayList<Credit> dateMigNoegenCredits = new ArrayList<>();
            dateMigNoegenCredits.add(credit3);
            dateMigNoegenCredits.add(credit4);

            // Oprette produktion 2
            ArrayList<Genre> genres = new ArrayList<>();
            genres.add(Genre.DRAMA);
            //Production dateMigNoegen = new Production("NF2", "Date mig nøgen", new Date(4000), genres,
            //ProductionType.SERIES, 20, Language.DANISH, true, true,
            //dateMigNoegenCredits, true, 18, true);

            // Tilføjer produktion produktionslisten
            //saveProduction(dateMigNoegen);
            */
    }
}
