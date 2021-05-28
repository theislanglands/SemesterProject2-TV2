package domain;

import data.DataFacade;
import data.DataLayerInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Controllerklasse FACADE
public final class TvCreditsFacade implements TvCreditsInterface {

    //Singleton object that is acessed in all controllers
    private static final TvCreditsFacade INSTANCE = new TvCreditsFacade();
    private static DataLayerInterface dataconnect;

    // Attribute
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

    // PRODUCTIONS
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
    public List<Production> getProductionsFromCreditName(int creditNameId) {
        return dataconnect.getProductionsFromCreditName(creditNameId);
    }

    @Override
    public boolean saveProduction(Production prod) {
        int productionId = dataconnect.createProduction(prod);
        if (productionId != -1) {
            prod.setId(productionId);
            productions.add(prod);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduction(int productionID, Production replaceProduction) {
        if (dataconnect.updateProduction(productionID, replaceProduction) == true) {
            productions.add(productionID, replaceProduction);
            return true;
        }

        return false;
    }

    @Override
    public void validateProduction(Production prod) {
        if (!prod.isValidated()) {
            prod.setValidated(true);
            dataconnect.validateProduction(prod.getId());
        }
    }

    @Override
    public void invalidateProduction(Production prod) {
        if (prod.isValidated()) {
            prod.setValidated(false);
            dataconnect.invalidateProduction(prod.getId());
        }
    }

    @Override
    public void deleteProduction(Production production) {
        productions.remove(production);
        dataconnect.deleteProduction(production.getId());
    }

    // CREDIT METHODS
    @Override
    public List<Credit> getUnValidatedCredits(int productionId) {

        List<Credit> returnList = new ArrayList<>();

        // finding production
        Production prod = null;
        for (Production p : productions) {
            if (p.getId() == productionId) {
                prod = p;
            }
        }
        for (Credit c : prod.getCredits()) {
            if (!c.isValidated()) {
                returnList.add(c);
            }
        }
        return returnList;
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

    @Override
    public void deleteCredit(Credit credit) {

        for (Production prod : productions) {
            if (prod.hasCredits(credit)) {
                prod.removeCredit(credit);
            }
            dataconnect.deleteCredit(credit);
        }
    }

    @Override
    public void addCreditToProduction(Credit credit) {
        int productionId = credit.getProductionId();

        for (Production prod :
                productions) {
            if(prod.getId() == productionId){
                prod.addCredit(credit);
                break;
            }
        }

        invalidateProduction(getProduction(productionId));
        dataconnect.createCredits(credit, productionId);
    }

    // CREDIT NAME METHODS
    @Override
    public Set<CreditName> getAllCreditNames() {
        Set<CreditName> creditNames = new HashSet<>();
        creditNames.addAll(dataconnect.getCreditNames());
        return creditNames;
    }

    @Override
    public void addCreditName(CreditName creditName) {
        dataconnect.createCreditName(creditName);
    }

    // Methods for database
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

