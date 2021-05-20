package data;

import domain.Production;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DataFacadeTest {

    private DataFacade dataFacade;

    @Before
    public void setUp() throws Exception {
        dataFacade = DataFacade.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {
    }

    @Test
    public void createProduction() {
    }

    @Test
    public void getProductions() {
    }

    @Test
    public void getProduction() {
    }

    @Test
    public void deleteProduction() {
    }

    @Test
    public void updateProduction() {
    }

    @Test
    public void getProductionsFromCreditName() {
        List<Production> testList = dataFacade.getProductionsFromCreditName(2);

        System.out.println(testList);
        assertNotNull(testList);
    }

    @Test
    public void createCredits() {
    }

    @Test
    public void getCredits() {
    }

    @Test
    public void getCredit() {
    }

    @Test
    public void deleteCredit() {
    }

    @Test
    public void updateCredit() {
    }

    @Test
    public void createCreditName() {
    }

    @Test
    public void getCreditNames() {
    }

    @Test
    public void getCreditName() {
    }

    @Test
    public void deleteCreditName() {
    }

    @Test
    public void updateCreditName() {
    }

    @Test
    public void createProductionName() {
    }

    @Test
    public void getGenres() {
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
    public void dateFormatter() {
    }

    @Test
    public void getAllCreditTypes() {
    }

    @Test
    public void getAllProductionTypes() {
    }

    @Test
    public void getAllLanguages() {
    }

    @Test
    public void getAllGenres() {
    }

    @Test
    public void insertImage() {
    }
}