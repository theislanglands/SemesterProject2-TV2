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
    void deleteProduction(Production prod);
    boolean updateProduction(String productionID, Production replaceProduction);

    // Credits
    void createCredits(Credit cred, Production prod);
    List<Credit> getCredits();
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

    // Person
    void createPerson(CreditName pers);
    List<CreditName> getPerson();
    CreditName getPerson(int personID);
    void deletePerson(int personID);
    boolean updatePerson(int personID, CreditName replaceCreditName);



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
