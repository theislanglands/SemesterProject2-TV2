package data;

import domain.Production;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DataFacadeTest1 {
    //Instance of dataFacade that will be accessed throughout the tests
    DataFacade dbFacade = DataFacade.getInstance();
    //Run the tests contained in the class

    //Ensures instance of dataFacade isn't null before any test, useful?
    @Test
    public void getInstance() {

    }

    @Test
    public void createProduction() {
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
//        ArrayList<String> testGenres = new ArrayList<>();
//        testGenres.add("Thriller");
//        testGenres.add("Animation");
//        testProduction.setGenres(testGenres);
//
//        System.out.println("\n\nTester createProduction()");
//        //hardcoded production
//        boolean a1 = dbFacade.createProduction(badehotelletWrong);
//
//        //SELECT id FROM productions WHERE id = ?
//        boolean a2 = dbFacade.createProduction(dbFacade.getProduction(2));
//
////        assertTrue(a1);
//        //It's a mess, I know ://
    }

    @Test
    public void getProductions() {
    }

    @Test
    public void getProduction() {
//        dataFacade.getProduction(getProductionId);
    }

    @Test
    public void deleteProduction() {
    }

    @Test
    public void updateProduction() {
    }

    @Test
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