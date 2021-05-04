package domain;

import domain.enums.CreditType;

public class Credit {

    private CreditName creditName;
    private String role;
    private CreditType creditType;
    private boolean isValidated = false;
    private String address, phone, email;

    public Credit(CreditName creditName, String role, CreditType creditType, boolean isValidated) {
        this.creditName = creditName;
        this.role = role;
        this.creditType = creditType;
        this.isValidated = isValidated;
    }

    public Credit(CreditName creditName, String role, CreditType creditType) {
        this.creditName = creditName;
        this.role = role;
        this.creditType = creditType;
    }

    public Credit(CreditName creditName, String role) {
        this.creditName = creditName;
        this.role = role;
    }

    public Credit() {
    }

    public CreditName getPerson() {
        return creditName;
    }

    public void setPerson(CreditName creditName) {
        this.creditName = creditName;
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
        return this.creditName.getFirstName();
    }
    public String getLastName(){
        return this.creditName.getLastName();
    }

    public String toString(){
        return (creditType +" - " + role + "\n");
    }

    public String getAddress() {
        return this.creditName.getAddress();
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
