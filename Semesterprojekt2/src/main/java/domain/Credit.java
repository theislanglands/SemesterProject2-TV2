package domain;

public class Credit {
    // Attributes
    private CreditName creditName;
    private String role;
    private String creditType;
    private boolean isValidated = false;

    // Constructors
    public Credit(CreditName creditName, String role, String creditType, boolean isValidated) {
        this.creditName = creditName;
        this.role = role;
        this.creditType = creditType;
        this.isValidated = isValidated;
    }

    public Credit(CreditName creditName, String role, String creditType) {
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

    // Methods
    public CreditName getCreditName() {
        return creditName;
    }

    public void setCreditName(CreditName creditName) {
        this.creditName = creditName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreditType() {
        return this.creditType;
    }

    public void setCreditType(String creditType) {
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
        return ("\n" + this.creditType + "\nrolle: " + this.role + "\nNavn: " + this.creditName.toString() + "\n");
    }

    public String getAddress() {
        return this.creditName.getAddress();
    }

    public String getEmail() {
        return this.creditName.getEmail();
    }

    public int getPhone() {
        return this.creditName.getPhone();
    }

    public void setAddress(String address) {
        this.creditName.setAddress(address);
    }

    public void setPhone(int phone) {
       this.creditName.setPhone(phone);
    }

    public void setEmail(String email) {
        this.creditName.setEmail(email);
    }

}
