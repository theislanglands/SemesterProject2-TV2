package data;

import static org.junit.Assert.*;
public class DataFacadeTest {

    private DataFacade dataFacade;

    @Before
    void setUp() throws Exception{
        dataFacade = new DataFacade();
    }

    @org.junit.Test
    public void getInstance() {
        System.out.println("hi");
    }

    @org.junit.Test
    public void createProduction() {
        Production testProduction = new Production();
        testProduction.setProductionReference("TEST123");
        testProduction.setName("TEST PRODUCTION NAME");
        testProduction.setSeason(1);
        testProduction.setEpisode(2);
        testProduction.setReleaseDate(new Date(100000));
        testProduction.setLength(3);
        testProduction.setSubtitle(true);
        testProduction.setSignLanguage(false);
        testProduction.setActive(true);
        testProduction.setValidated(true);
        testProduction.setLanguage("Dansk");
        testProduction.setProductionBio("TEST BIO");
        testProduction.setType("Serie");
        testProduction.setCompanyProductionName("SF Film Production ApS");
        testProduction.setProductionType("Serie");

        ArrayList<String> genres = new ArrayList<>();
        genres.add("Thriller");
        genres.add("Animation");
        badehotelletWrong.setGenres(genres);

        assertEquals("Creating new production")
    }

    @org.junit.Test
    public void getProductions() {
    }

    @org.junit.Test
    public void getProduction() {
    }

    @org.junit.Test
    public void deleteProduction() {
    }

    @org.junit.Test
    public void updateProduction() {
    }

    @org.junit.Test
    public void createCredits() {
    }

    @org.junit.Test
    public void getCredits() {
    }

    @org.junit.Test
    public void getCredit() {
    }

    @org.junit.Test
    public void deleteCredit() {
    }

    @org.junit.Test
    public void updateCredit() {
    }

    @org.junit.Test
    public void createCreditName() {
    }

    @org.junit.Test
    public void getCreditNames() {
    }

    @org.junit.Test
    public void getCreditName() {
    }

    @org.junit.Test
    public void deleteCreditName() {
    }

    @org.junit.Test
    public void updateCreditName() {
    }

    @org.junit.Test
    public void createProductionName() {
    }

    @org.junit.Test
    public void getGenres() {
    }

    @org.junit.Test
    public void validateProduction() {
    }

    @org.junit.Test
    public void invalidateProduction() {
    }

    @org.junit.Test
    public void validateCredit() {
    }

    @org.junit.Test
    public void invalidateCredit() {
    }

    @org.junit.Test
    public void dateFormatter() {
    }

    @org.junit.Test
    public void getAllCreditTypes() {
    }

    @org.junit.Test
    public void getAllProductionTypes() {
    }

    @org.junit.Test
    public void getAllLanguages() {
    }

    @org.junit.Test
    public void getAllGenres() {
    }

    @org.junit.Test
    public void insertImage() {
    }
}