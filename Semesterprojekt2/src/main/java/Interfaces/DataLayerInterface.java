package Interfaces;

import domain.Credit;
import domain.Person;
import domain.Production;
import domain.User;

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
    void createCredits(Credit cred);
    List<Credit> getCredits();
    Credit getCredit(int creditID);
    void deleteCredit(int creditID);
    boolean updateCredit(int creditID, Credit replaceCredit);

    // Person
    void createPerson(Person pers);
    List<Person> getPerson();
    Person getPerson(int personID);
    void deletePerson(int personID);
    boolean updatePerson(int personID, Person replacePerson);


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
