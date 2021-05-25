package domain;


import data.DataFacade;
import org.junit.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TvCreditsFacadeTest {

    private static TvCreditsFacade tvCreditsFacade;
    private static DataFacade dataFacade;
    private static Production testProduction;
    private static CreditName testCreditName;
    private static CreditName testCreditName2;
    private static Credit testCredit;
    private static Credit testCredit2;
    private static List<Integer> productionIds = new ArrayList<>();



    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("BeforeClass");
        tvCreditsFacade = TvCreditsFacade.getInstance();

        // collects the highest present episode-number from production_tabel
        // in order to avoid duplicate keys while testing
        List<Production> testList = tvCreditsFacade.getAllProductions();;
        int[] productionEpisodes = new int[testList.size()];
        int highestEpisode = 0;
        int episode;
        for (Production prod : testList) {
            episode = prod.getEpisode();
            if (episode > highestEpisode) {
                highestEpisode = episode;
            }
        }

        // Creates a testProduction
        testProduction = new Production();
        testProduction.setProductionReference("SF666");
        testProduction.setName("Badehotellet");
        testProduction.setSeason(4);
        testProduction.setEpisode(highestEpisode+1);
        testProduction.setReleaseDate(new Date(100000));
        testProduction.setLength(46);
        testProduction.setSubtitle(true);
        testProduction.setSignLanguage(false);
        testProduction.setActive(true);
        testProduction.setValidated(true);
        testProduction.setLanguage("Dansk");
        testProduction.setProductionBio("En ny spændende sæson af badehotellet");
        testProduction.setProductionType("Serie");
        testProduction.setProductionCompanyName("SF Film Production ApS");
        testProduction.setImageUrl("http://rannok.dk/TVCredits/production_images/Unknown_production.jpg");

        ArrayList<String> testGenres = new ArrayList<>();
        testGenres.add("Reality");
        testGenres.add("Fantasy");
        testProduction.setGenres(testGenres);

        // Creating testNames & testCredits
        testCreditName = new CreditName();
        testCreditName.setFirstName("Lars");
        testCreditName.setLastName("Larsen");
        testCreditName.setAddress("Bamsevej 4, 5600");
        testCreditName.setPhone(27201117);
        testCreditName.setEmail("AmalieDollerup@badehotellet.dk");

        testCredit = new Credit();
        testCredit.setRole("Far til Simon");
        testCredit.setValidated(true);
        testCredit.setProductionId(1);
        testCredit.setCreditName(testCreditName);
        testCredit.setCreditType("Dukkefører");

        testCreditName2 = new CreditName();
        testCreditName2.setFirstName("Bodille");
        testCreditName2.setLastName("Jensen");
        testCreditName2.setAddress("nullway 07");
        testCreditName2.setPhone(65926774);
        testCreditName2.setEmail("BJn@badehotellet.dk");

        testCredit2 = new Credit();
        testCredit2.setRole("EN FED ROLLE ALTSÅ!");
        testCredit2.setValidated(false);
        testCredit2.setProductionId(1);
        testCredit2.setCreditName(testCreditName2);
        testCredit2.setCreditType("Lys");

    }





    @Test
    public void getInstance() {
        assertNotNull("Unable to create Instance of TvCreditsFacade", tvCreditsFacade);
    }

    @Test
    public void getProduction() {
        Production prod = null;
        prod = tvCreditsFacade.getProduction(1);
        assertNotNull("Unable to retrieve production", prod);
    }

    @Test
    public void getAllProductions() {
        List<Production> testList = null;
        testList = tvCreditsFacade.getAllProductions();
        assertNotNull("Unable to retrieve list of productions", testList);
    }

    @Test
    public void getValidatedProductions() {
        List<Production> testValidatedProductionsList = tvCreditsFacade.getValidatedProductions();
        List<Production> allProductionsList = tvCreditsFacade.getAllProductions();

        for (Production p : testValidatedProductionsList) {
            assertTrue("a production on the list is not validated", (p.isValidated()));
        }

        int countValidated = 0;
        for (Production p : allProductionsList) {
            if (p.isValidated()) countValidated++;
        }
        assertEquals("number of validated productions not correct",countValidated, testValidatedProductionsList.size());
    }

    @Test
    public void getUnValidatedProductions() {
        List<Production> testListAllProductions = tvCreditsFacade.getAllProductions();
        List<Production> testListUnValidatedProductions = tvCreditsFacade.getUnValidatedProductions();
        int countAllUnValidated = 0;

        for (Production prod : testListUnValidatedProductions) {
            assertFalse("A production on the list is validated", (prod.isValidated()));
        }

        for (Production prod : testListAllProductions) {
            if (!prod.isValidated()) {
                countAllUnValidated++;
            }
        }
        assertEquals("Number of unvalidated productions not correct", countAllUnValidated, testListUnValidatedProductions.size());
    }

    @Test
    public void saveProduction() {
        tvCreditsFacade.saveProduction(testProduction);

        List<Production> allProductions = tvCreditsFacade.getAllProductions();

        Production testProduction2 = allProductions.get(allProductions.size()-1);

        productionIds.add(testProduction2.getId());

        assertEquals(testProduction, testProduction2);
    }

    @Test
    public void updateProduction() {
    }

    @Test
    public void validateProduction() {
    }

    @Test
    public void invalidateProduction() {
    }

    @Test
    public void validateCredit() {
    }

    @Test
    public void invalidateCredit() {
    }

    @Test
    public void addCredit() {
    }

    @Test
    public void deleteCredit() {
    }

    @Test
    public void getCreditTypes() {
    }

    @Test
    public void getProductionTypes() {
    }

    @Test
    public void getLanguages() {
    }

    @Test
    public void getGenres() {
    }


    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("AfterClass: ");
        DataFacade dataFacade2 = DataFacade.getInstance();
        dataFacade2.deleteProduction(17);

//        for (Integer productionId : productionIds) {
//            dataFacade.deleteProduction(11);
//        }

    }

}