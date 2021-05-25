package domain;

public class Credit {
    // Attributes
    private CreditName creditName;
    private String role;
    private String creditType;
    private boolean isValidated = false;
    private int productionId;

    // Constructors
    public Credit() {
    }

    public Credit(CreditName creditName, String role, String creditType, boolean isValidated, int productionId) {
        this.creditName = creditName;
        this.role = role;
        this.creditType = creditType;
        this.isValidated = isValidated;
        this.productionId = productionId;
    }

    // Getters / Setters Methods
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

    public int getProductionId() {
        return productionId;
    }
    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }

    // Methods that calls creditName
    public String getFirstName(){
        return this.creditName.getFirstName();
    }
    public String getLastName(){
        return this.creditName.getLastName();
    }

    public String getImageUrl() {
        return this.creditName.getImageUrl();
    }
    public void setImageUrl(String imageUrl) {
        this.creditName.setImageUrl(imageUrl);
    }

    //toString method
    public String toString(){
        return ("\n" + this.creditType + "\nrolle: " + this.role + "\nNavn: " + this.creditName.toString() + "\n");
    }
}
