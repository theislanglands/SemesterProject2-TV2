package domain;
import java.util.Date;

//Att. releaseDate refererer til det dato produktionen blev released.
//Skal krediterings-klassen droppes? Skal vi så bruge en hashMap / ArrayList for kreditering inde på den specifikke produktionen?
//toString-metoder for at sende information til databasen.


import java.util.ArrayList;

public class Production {

    private String name;
    private String productionId;
    private Date releaseDate;
    private ArrayList<Person> personArrayList;

    public Production(String name, String productionId, Date productionDate) {
        this.name = name;
        this.productionId = productionId;
        this.releaseDate = productionDate;
        personArrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return releaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductionId() {
        return productionId;
    }

    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public void addPerson(Person person){
        this.personArrayList.add(person);
    }

    public void setPersonArrayList(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    @Override
    public String toString() {
        return name + "," + productionId + "," + releaseDate + "," + personArrayList;
    }
}
