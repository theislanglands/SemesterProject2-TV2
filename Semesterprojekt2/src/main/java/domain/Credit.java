package domain;

import domain.enums.CreditType;

public class Credit {

    private Person person;
    private String role;
    private CreditType creditType;
    private boolean isValidated = false;

    public Credit(Person person, String role, CreditType creditType, boolean isValidated) {
        this.person = person;
        this.role = role;
        this.creditType = creditType;
        this.isValidated = isValidated;
    }

    public Credit(Person person, String role, CreditType creditType) {
        this.person = person;
        this.role = role;
        this.creditType = creditType;
    }

    public Credit(Person person, String role) {
        this.person = person;
        this.role = role;
    }

    public Credit() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public String getFirstName(){
        return this.person.getFirstName();
    }
    public String getLastName(){
        return this.person.getLastName();
    }

    public String toString(){
        return (creditType +" - " + role + "\n");
    }
}
