package data;

import domain.Credit;
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
        //Production test = dbFacade.getProduction(1);
        //System.out.println(test);

        // 2 test af getProductions
        //List<Production> productionTest = dbFacade.getProductions();
        //System.out.println(productionTest);

        // test af deleteProduction - VIRKER!
        /*
        dbFacade.deleteProduction(1);
        test = dbFacade.getProduction(1);
        System.out.println(test);
         */

        // test af update produktion - VIRKER!
        // Opretter produktion der skal ersttte den gamle
//        Production badehotellet = new Production();
//        badehotellet.setProductionReference("SF666");
//        badehotellet.setName("Badehotellet");
//        badehotellet.setSeason(2);
//        badehotellet.setEpisode(5);
//        badehotellet.setReleaseDate(new Date(100000));
//        badehotellet.setLength(46);
//        badehotellet.setSubtitle(true);
//        badehotellet.setSignLanguage(false);
//        badehotellet.setActive(true);
//        badehotellet.setValidated(true);
//        badehotellet.setLanguage("Dansk");
//        badehotellet.setProductionBio("En ny spændende sæson af badehotellet");
//        badehotellet.setType("Serie");
//        badehotellet.setCompanyProductionName("SF Film Production ApS");
//        badehotellet.setProductionType("Serie");
//
//        ArrayList<String> genres2 = new ArrayList<>();
//        genres2.add("Reality");
//        genres2.add("Fantasy");
//        badehotellet.setGenres(genres2);
//
//        System.out.println(badehotellet);
//        dbFacade.updateProduction(1, badehotellet);
//        test = dbFacade.getProduction(1);
//        System.out.println(test);

        // delete production test
        //dbFacade.deleteProduction(7);

        // test af createProduction()
//        Production badehotelletWrong = new Production();
//        badehotelletWrong.setProductionReference("WRONG123");
//        badehotelletWrong.setName("Badehotellet 2");
//        badehotelletWrong.setSeason(2);
//        badehotelletWrong.setEpisode(6);
//        badehotelletWrong.setReleaseDate(new Date(100000));
//        badehotelletWrong.setLength(99);
//        badehotelletWrong.setSubtitle(true);
//        badehotelletWrong.setSignLanguage(false);
//        badehotelletWrong.setActive(true);
//        badehotelletWrong.setValidated(true);
//        badehotelletWrong.setLanguage("Dansk");
//        badehotelletWrong.setProductionBio("En ny spændende sæson af badehotellet");
//        badehotelletWrong.setType("Serie");
//        badehotelletWrong.setCompanyProductionName("SF Film Production ApS");
//        badehotelletWrong.setProductionType("Serie");
//
//        ArrayList<String> genres = new ArrayList<>();
//        genres.add("Thriller");
//        genres.add("Animation");
//        badehotelletWrong.setGenres(genres);
//
//        System.out.println("\n\nTester createProduction()");
//        dbFacade.createProduction(badehotelletWrong);


        // Test af getProductions og print til konsol
//        System.out.println("\n\n tester getProduction");
//        System.out.println(dbFacade.getProductions());





        // test af deleteCreditName
        //dbFacade.deleteCreditName(1);

        // test af createCreditName()
//        CreditName creditName = new CreditName();
//        creditName.setFirstName("First Name (TEST)");
//        creditName.setLastName("Last Name (TEST)");
//        creditName.setPhone(99999999);
//        creditName.setEmail("testperson@testperson.dk");
//
//        dbFacade.createCreditName(creditName);


        // tester getCredit
//        Credit testGetCredit = dbFacade.getCredit(4);
//        System.out.println("\ntester hent af kreditering fra db");
//        System.out.println(testGetCredit);

        // tester getCredits
        //List<Credit> testList = dbFacade.getCredits(1);
        //System.out.println("\ntester liste af credits");
        //System.out.println(testList);

        //Tester getCreditNames
//        System.out.println("Tester getCreditNames");
//        List<CreditName> testName = dbFacade.getCreditNames();
//        for (CreditName i : testName) {
//            System.out.println(i);
//        }

        // tester createCredits

//        System.out.println("\n\n Test af createCredits: ");
//
//        CreditName creditName2 = new CreditName(1235, "Lars", "Larsen", "Bamsevej 4, 5600", 27201117, "AmalieDollerup@badehotellet.dk");
//
//        Credit credit1 = new Credit();
//        credit1.setRole("Far til Simon");
//        credit1.setValidated(true);
//        credit1.setCreditName(creditName2);
//        credit1.setCreditType("Dukkefører");
//        dbFacade.createCredits(credit1, 1);


        // Test af deleteCredit()
//        System.out.println("\n\nTest af delete credit");
//        dbFacade.deleteCredit(38);



        // Test af updateCredit()
//        System.out.println("\n\nTest af updateCredit()");
//        CreditName creditName1 = new CreditName();
//        creditName1.setFirstName("Bodille");
//        creditName1.setLastName("Jensen");
//        creditName1.setAddress("nullway 07");
//        creditName1.setPhone(65926774);
//        creditName1.setEmail("BJn@badehotellet.dk");
//
//        Credit credit2 = new Credit();
//        credit2.setRole("EN FED ROLLE ALTSÅ!");
//        credit2.setValidated(false);
//        credit2.setProductionId(1);
//        credit2.setCreditName(creditName1);
//        credit2.setCreditType("Lys");
//
//        dbFacade.updateCredit(4, credit2);


        // Test af updateCreditName()
//        System.out.println("\n\nTest af updateCreditName()");
//        CreditName creditName3 = new CreditName();
//        creditName3.setFirstName("Frank");
//        creditName3.setLastName("ErIGodtHumør");
//        creditName3.setAddress("Regnvejrsvej 12");
//        creditName3.setPhone(10101010);
//        creditName3.setEmail("FrankHappy@badehotellet.dk");
//
//        dbFacade.updateCreditName(22, creditName3);


        // Test af insertImage()
        // dbFacade.insertImage("Semesterprojekt2/src/main/resources/org/presentation/Logo/drukplakat.jpeg", "Druk",1);

        // Test om check for tomt ResultQuery virker
        //dbFacade.getCreditNameId("a","s");




        /* KOPIERET FRA GAMLE DATA MAIN

        public void createTestProductions() {
            // Opretter produktion 1: "Badehotellet"

            // Opretter personer
            CreditName creditName2 = new CreditName();
            creditName2.setId(1234);
            creditName2.setFirstName("Bodil");
            creditName2.setLastName("Jørgensen");
            creditName2.setAddress(null);
            creditName2.setPhone("65926104");
            creditName2.setEmail("BodilJoergensen@badehotellet.dk");

            CreditName creditName2 = new CreditName(1235, "Amalie", "Dollerup", "Bamsevej 4, 5600", "27201117", "AmalieDollerup@badehotellet.dk");

            // Opretter krediteringer
            Credit credit1 = new Credit();
            credit1.setRole("Far til Simon");
            credit1.setValidated(true);
            credit1.setPerson(creditName2);

            Credit credit2 = new Credit(creditName2, "Mor til Hans", "CreditType.Medvirkende");

            // Opretter liste med krediteringer
            ArrayList<Credit> badehotelletCredits = new ArrayList<>();
            badehotelletCredits.add(credit1);
            badehotelletCredits.add(credit2);


            //Opretter produktion
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
