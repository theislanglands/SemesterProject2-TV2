package domain;

import data.DataFacade;
import data.DataLayerInterface;

import java.util.ArrayList;
import java.util.List;

//Controllerklasse FACADE
public final class TvCreditsFacade implements TvCreditsInterface {

    //Singleton object that is acessed in all controllers
    private static final TvCreditsFacade INSTANCE = new TvCreditsFacade();
    private static DataLayerInterface dataconnect;



    // attributes
    private List<Production> productions;

    // Constructor
    private TvCreditsFacade() {
        dataconnect = DataFacade.getInstance();
        this.productions = dataconnect.getProductions();
    }

    // returns singelton instance
    public static TvCreditsFacade getInstance() {
        return INSTANCE;
    }

    // Interface methods

    // Productions
    @Override
    public Production getProduction(int productionId) {
        for (Production p : productions) {
            if (p.getId() == productionId) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Production> getAllProductions() {
        return this.productions;
    }

    @Override
    public List<Production> getValidatedProductions() {
        List<Production> returnList = new ArrayList<Production>();

        for (Production p : productions) {
            if (p.isValidated()) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    @Override
    public List<Production> getUnValidatedProductions() {
        List<Production> returnList = new ArrayList<Production>();
        for (Production p : productions) {
            if (!p.isValidated()) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    @Override
    public boolean saveProduction(Production prod) {
        if (dataconnect.createProduction(prod)) {
            productions.add(prod);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduction(int productionID, Production replaceProduction) {
        return dataconnect.updateProduction(productionID, replaceProduction);
    }

    @Override
    public void validateProduction(Production prod){
        if (!prod.isValidated()) {
            prod.setValidated(true);
            dataconnect.validateProduction(prod.getId());
        }
    }

    @Override
    public void invalidateProduction(Production prod){
        if (prod.isValidated()) {
            prod.setValidated(false);
            dataconnect.invalidateProduction(prod.getId());
        }
    }

    @Override
    public void validateCredit(Credit credit) {
        if (!credit.isValidated()) {        // checks if credit validation status is "not validated"
            credit.setValidated(true);
            dataconnect.validateCredit(credit);
        }
    }

    @Override
    public void invalidateCredit(Credit credit) {
        if (credit.isValidated()) {         // checks if credit validation status is "validated"
            credit.setValidated(false);
            dataconnect.validateCredit(credit);
        }
    }

    // CREDITS
    @Override
    public void addCredit(Credit credit) {
        int productionId = credit.getProductionId();

        // finding production
        for (Production prod : productions) {
            if (prod.getId() == productionId) {
                prod.addCredit(credit);

                // updating database
                dataconnect.updateProduction(productionId, prod);
                break;
            }
        }
    }

    @Override
    public void deleteCredit(Credit credit) {

        for (Production prod : productions) {
            if (prod.hasCredit(credit)) {
                prod.removeCredit(credit);
            }
            dataconnect.deleteCredit(credit);
        }
    }

    // Methods for Enums
    @Override
    public List<String> getCreditTypes() {
        return dataconnect.getAllCreditTypes();
    }

    @Override
    public List<String> getProductionTypes() {
        return dataconnect.getAllProductionTypes();
    }

    @Override
    public List<String> getLanguages() {
        return dataconnect.getAllLanguages();
    }

    @Override
    public List<String> getGenres() {
        return dataconnect.getAllGenres();
    }


}








/*
    private Administrator facade;

    public TvCredits(){
        facade = new Administrator();
    }


    //Calling implementation in associated classes.
    @Override
    public void verifyProduction(Production production) {
        facade.verifyProduction(production);
    }

    @Override
    public void addProduction(int ID, String Name, Date date) {
        facade.addProduction(ID, Name, date);
    }

    @Override
    public void addCredit(Production production) {
        facade.addCredit(production);
    }

    @Override
    public void search(String searchString) {
        facade.search(searchString);
    }

    @Override
    public void searchProduction(String searchString) {
        facade.searchProduction(searchString);
    }

    @Override
    public void searchCredit(String searchString) {
        facade.searchCredit(searchString);
    }
*/

// private Producer producerObj = new Producer(1,"Test", "Testsen");
// private Administrator administrator = new Administrator(2,"Teste", "Testesen");



