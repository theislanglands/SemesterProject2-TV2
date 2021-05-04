package data;

import Interfaces.DataLayerInterface;
import domain.CreditName;
import domain.Production;
import domain.Credit;
import domain.enums.CreditType;
import domain.enums.Genre;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataMain implements DataLayerInterface {

    // Liste til at simulere persistenslag
    private List<Production> productionPersistence = new ArrayList<>();

    @Override
    public boolean createProduction(Production prod) {
        return false;
    }

    @Override
    public List<Production> getProductions() {
        return productionPersistence;
    }

    @Override
    public Production getProduction(int id) {
        return null;
    }

    @Override
    public void deleteProduction(Production prod) {

    }

    //@Override
    public void saveProduction(Production prod) {
        productionPersistence.add(prod);
    }

    @Override
    public boolean updateProduction(java.lang.String productionID, Production replaceProduction) {

        // fidner index af produktion med produktionID
        int index = -1;
        for (int i = 0; i < productionPersistence.size(); i++ ) {
            if (productionPersistence.get(i).getId().equals(productionID)) index = i;
        }

        // produktionsID ikke fundet
        if (index == -1) {
            System.out.println("Produktion ikke fundet");
            return false;
        }

        productionPersistence.set(index, replaceProduction);
        System.out.println("Produktion ændret");
        return true;
    }

    @Override
    public void createCredits(Credit cred, Production prod) {

    }

    @Override
    public List<Credit> getCredits() {
        return null;
    }

    @Override
    public Credit getCredit(int creditID) {
        return null;
    }

    @Override
    public void deleteCredit(int creditID) {

    }

    @Override
    public boolean updateCredit(int creditID, Credit replaceCredit) {
        return false;
    }

    @Override
    public List<String> getCreditTypes() {
        return null;
    }

    @Override
    public List<String> getProductionTypes() {
        return null;
    }

    @Override
    public List<String> getLanguages() {
        return null;
    }

    @Override
    public List<String> getGenres() {
        return null;
    }

    @Override
    public void createPerson(CreditName pers) {

    }

    @Override
    public List<CreditName> getPerson() {
        return null;
    }

    @Override
    public CreditName getPerson(int personID) {
        return null;
    }

    @Override
    public void deletePerson(int personID) {

    }

    @Override
    public boolean updatePerson(int personID, CreditName replaceCreditName) {
        return false;
    }

    public void createTestProductions() {
        // Opretter produktion 1: "Badehotellet"
        // Opretter personer
        CreditName creditName1 = new CreditName();
        creditName1.setId(1234);
        creditName1.setFirstName("Bodil");
        creditName1.setLastName("Jørgensen");
        creditName1.setAddress(null);
        creditName1.setPhone(65926104);
        creditName1.setEmail("BodilJoergensen@badehotellet.dk");

        CreditName creditName2 = new CreditName(1235, "Amalie", "Dollerup", "Bamsevej 4, 5600", 27201117, "AmalieDollerup@badehotellet.dk");

        // Opretter krediteringer
        Credit credit1 = new Credit();
        credit1.setRole("Far til Simon");
        credit1.setValidated(true);
        credit1.setPerson(creditName1);

        Credit credit2 = new Credit(creditName2, "Mor til Hans", CreditType.Medvirkende);

        // Opretter liste med krediteringer
        ArrayList<Credit> badehotelletCredits = new ArrayList<>();
        badehotelletCredits.add(credit1);
        badehotelletCredits.add(credit2);

        // Opretter produktion
        Production badehotellet = new Production();
        badehotellet.setId("1");
        badehotellet.setName("Badehotellet");
        badehotellet.setReleaseDate(new Date(1000));
        badehotellet.setLength(42);
        badehotellet.setHasSubtitle(true);
        badehotellet.setHasSignLanguage(false);
        badehotellet.setCredits(badehotelletCredits);
        badehotellet.setActive(true);
        badehotellet.setValidated(true);

        // Tilføjer produktion produktionslisten
        saveProduction(badehotellet);

        //Opretter personer
        CreditName creditName3 = new CreditName();
        creditName3.setId(1236);
        creditName3.setFirstName("Jacob");
        creditName3.setLastName("Jacobsen");
        creditName3.setAddress("København");
        creditName3.setPhone(20568095);
        creditName3.setEmail("JacobJacobsen@DateMigNoegen.dk");

        CreditName creditName4 = new CreditName(1237, "Laura", "Laurasen", "Vejle", 52674582, "LauraLaurasen@DateMigNoegen.dk");

        // Opretter krediteringer
        Credit credit3 = new Credit();
        credit3.setRole("Medvirkende");
        credit3.setValidated(true);
        credit3.setPerson(creditName3);

        Credit credit4 = new Credit(creditName4, "En ny rolle", CreditType.Medvirkende);

        // Oprette liste med krediteringer
        ArrayList<Credit> dateMigNoegenCredits = new ArrayList<>();
        dateMigNoegenCredits.add(credit3);
        dateMigNoegenCredits.add(credit4);

        // Oprette produktion 2
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.DRAMA);
        //Production dateMigNoegen = new Production("NF2", "Date mig nøgen", new Date(4000), genres,
                //ProductionType.SERIES, 20, Language.DANISH, true, true,
                //dateMigNoegenCredits, true, 18, true);

        // Tilføjer produktion produktionslisten
        //saveProduction(dateMigNoegen);
    }


    // test af Data-Main
    public static void main(java.lang.String[] args) {
//        DataLayerInterface dataconnect = new DataMain();
//        dataconnect.createTestProductions();
//
//        List<Production> testList = dataconnect.getProductions();
//        System.out.println(testList);
    }
}





