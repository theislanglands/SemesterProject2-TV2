package data;

import Interfaces.DataLayerInterface;
import domain.Person;
import domain.Production;
import domain.Credit;
import domain.enums.CreditType;
import domain.enums.Genre;
import domain.enums.Language;
import domain.enums.ProductionType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DataMain implements DataLayerInterface {

    @Override
    public List<Production> getProductions() {

        // Denne liste viser alle de produktioner vi har i vores "database"
        List<Production> returnProductions = new ArrayList<>();

        // Opretter produktion 1: "Badehotellet"
        // Opretter personer
        Person person1 = new Person();
        person1.setId(1234);
        person1.setFirstName("Bodil");
        person1.setLastName("Jørgensen");
        person1.setAddress(null);
        person1.setPhone(65926104);
        person1.setEmail("BodilJoergensen@badehotellet.dk");

        Person person2 = new Person(1235, "Amalie", "Dollerup", "Bamsevej 4, 5600", 27201117, "AmalieDollerup@badehotellet.dk");

        // Opretter krediteringer
        Credit credit1 = new Credit();
        credit1.setRole("Far til en eller anden");
        credit1.setValidated(true);
        credit1.setPerson(person1);

        Credit credit2 = new Credit(person2, "Mor til Hans", CreditType.Medvirkende);

        // Opretter liste med krediteringer
        ArrayList<Credit> badehotelletCredits = new ArrayList<>();
        badehotelletCredits.add(credit1);
        badehotelletCredits.add(credit2);


        // Opretter produktion
        Production badehotellet = new Production();
        badehotellet.setId(1);
        badehotellet.setName("Badehotellet");
        badehotellet.setReleaseDate(new Date(1000));
        badehotellet.setLength(42);
        badehotellet.setHasSubtitle(true);
        badehotellet.setHasSignLanguage(false);
        badehotellet.setCredits(badehotelletCredits);
        badehotellet.setActive(true);
        badehotellet.setValidated(true);

        // Tilføjer produktion produktionslisten
        returnProductions.add(badehotellet);


        //Opretter personer
        Person person3 = new Person();
        person3.setId(1236);
        person3.setFirstName("Jacob");
        person3.setLastName("Jacobsen");
        person3.setAddress("København");
        person3.setPhone(20568095);
        person3.setEmail("JacobJacobsen@DateMigNoegen.dk");

        Person person4 = new Person(1237, "Laura", "Laurasen", "Vejle", 52674582, "LauraLaurasen@DateMigNoegen.dk");

        // Opretter krediteringer
        Credit credit3 = new Credit();
        credit3.setRole("Medvirkende");
        credit3.setValidated(true);
        credit3.setPerson(person3);

        Credit credit4 = new Credit(person4, "En ny rolle", CreditType.Medvirkende);

        // Oprette liste med krediteringer
        ArrayList<Credit> dateMigNoegenCredits = new ArrayList<>();
        dateMigNoegenCredits.add(credit3);
        dateMigNoegenCredits.add(credit4);


        // Oprette produktion 2
        ArrayList<Genre> genres = new ArrayList<>();
        genres.add(Genre.DRAMA);
        Production dateMigNoegen = new Production(2, "Date mig nøgen", new Date(4000), genres,
                ProductionType.SERIES, 20, Language.DANISH, true, true,
                dateMigNoegenCredits, true, true);

        // Tilføjer produktion produktionslisten
        returnProductions.add(dateMigNoegen);


        return returnProductions;
    }
}




