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
    List<Credit> getCredits(int prodId);
    Credit getCredit(int creditID);
    void deleteCredit(int creditID);
    boolean updateCredit(int creditID, Credit replaceCredit);

    // Metoder der erstatter ENUMS

    List<String> getCreditTypes();
    List<String> getProductionTypes();
    List<String> getLanguages();
    List<String> getAllGenres();

    // CreditName
    void createCreditName(CreditName creditName);
    List<CreditName> getCreditNames();
    CreditName getCreditName(int creditNameID);
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
