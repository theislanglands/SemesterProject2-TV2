package domain;

import data.DataFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TvCreditsFacadeTest {

    private TvCreditsFacade tvCreditsFacade;

    @Before
    public void setUp() throws Exception {
        tvCreditsFacade = TvCreditsFacade.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() {
        assertNotNull(tvCreditsFacade);
    }

    @Test
    public void getProduction() {
    }

    @Test
    public void getAllProductions() {
    }

    @Test
    public void getValidatedProductions() {
    }

    @Test
    public void getUnValidatedProductions() {
    }

    @Test
    public void saveProduction() {
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
}