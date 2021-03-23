package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Production {

    private String name;
    private String productionId;
    private ArrayList<Person> personArrayList;

    public Production(String name, String productionId) {
        this.name = name;
        this.productionId = productionId;
        personArrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
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
}
