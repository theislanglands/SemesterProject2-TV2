package data;

import domain.Credit;
import domain.CreditName;
import domain.Production;

import java.util.List;

public interface DataLayerInterface {

    // Production
    int createProduction(Production prod); // returns created ID, -1 if unsuccesful
    List<Production> getProductions();
    Production getProduction(int id);
    void deleteProduction(int id);
    boolean updateProduction(int sourceProductionID, Production replaceProduction);
    List<Production> getProductionsFromCreditName(int creditNameId);

    // Credits
    void createCredits(Credit cred, int productionId);
    List<Credit> getCredits(int prodId);
    Credit getCredit(int creditID);
    void deleteCredit(Credit credit);
    boolean updateCredit(int creditID, Credit replaceCredit);

    // Validation
    void validateProduction(int productionID);
    void invalidateProduction(int productionID);
    void validateCredit(Credit credit);
    void invalidateCredit(Credit credit);

    // Metoder der erstatter ENUMS
    List<String> getAllCreditTypes();
    List<String> getAllProductionTypes();
    List<String> getAllLanguages();
    List<String> getAllGenres();

    // CreditName
    int createCreditName(CreditName creditName);
    List<CreditName> getCreditNames();
    CreditName getCreditName(int creditNameID);
    void deleteCreditName(int creditNameID);
    boolean updateCreditName(int creditNameID, CreditName replaceCreditName);


    // FUTURE ITERATIONS - System Admin Functionality
    /*
    // User
    void createUser(User user);
    List<User> getUser();
    User getUser(int userID);
    void deleteUser(int userID);
    boolean updateCredit(int userID, User replaceUser);
    */
}
