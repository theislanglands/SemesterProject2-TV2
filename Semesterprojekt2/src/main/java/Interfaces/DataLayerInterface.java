package Interfaces;

import domain.Credit;
import domain.CreditName;
import domain.Production;

import java.util.List;

public interface DataLayerInterface {

    // flg metoder defineres for hver klasse!

    // Production
    boolean createProduction(Production prod);
    List<Production> getProductions();
    Production getProduction(int id);
    void deleteProduction(int id);
    boolean updateProduction(int sourceProductionID, Production replaceProduction);

    // Credits
    void createCredits(Credit cred, Production prod);
    List<Credit> getCredits(Production prod);
    Credit getCredit(int creditID);
    void deleteCredit(int creditID);
    boolean updateCredit(int creditID, Credit replaceCredit);

    // Metoder der erstatter ENUMS
    // Credit types (erstatter enum)
    List<String> getCreditTypes();

    // Production types (erstatter enum)
    List<String> getProductionTypes();

    // Language Type (erstatter enum)
    List<String> getLanguages();

    // Genre Types (erstatter enum)
    List<String> getGenres();

    // CreditName
    void createCreditName(CreditName creditName);
    List<CreditName> getCreditNames();
    CreditName getCreditNames(int creditNameID);
    void deleteCreditName(int creditNameID);
    boolean updateCreditName(int creditNameID, CreditName replaceCreditName);



    /*
    // User
    void createUser(User user);
    List<User> getUser();
    User getUser(int userID);
    void deleteUser(int userID);
    boolean updateCredit(int userID, User replaceUser);
    */

    // void createTestProductions();
}
