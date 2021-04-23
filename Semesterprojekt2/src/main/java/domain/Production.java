package domain;
import domain.enums.Genre;
import domain.enums.Language;
import domain.enums.ProductionType;

import java.io.Serializable;
import java.util.Date;

//Att. releaseDate refererer til det dato produktionen blev released.
//Skal krediterings-klassen droppes? Skal vi så bruge en hashMap / ArrayList for kreditering inde på den specifikke produktionen?
//toString-metoder for at sende information til databasen.


import java.util.ArrayList;

public class Production implements Serializable {

    //Enums: genre, type, language
    private String id; //e.g. nf221
    private String name;
    private Date releaseDate;
    private ArrayList<Genre> genre;
    private ProductionType productionType;
    private int length;
    private Language language;
    private boolean hasSubtitle;
    private boolean hasSignLanguage;
    private boolean isActive;
    private boolean isValidated = false;

    private ArrayList<Credit> credits;

    public Production(String id, String name, Date releaseDate, ArrayList<Genre> genre,
                      ProductionType productionType, int length, Language language, boolean hasSubtitle,
                      boolean hasSignLanguage, ArrayList<Credit> credits, boolean isActive, boolean isValidated) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.productionType = productionType;
        this.length = length;
        this.language = language;
        this.hasSubtitle = hasSubtitle;
        this.hasSignLanguage = hasSignLanguage;
        this.credits = credits;
        this.isActive = isActive;
    }

    public Production(String id, String name, Date date){
        this.id = id;
        this.name = name;
        this.releaseDate = date;
    }

    public Production(){

    }

    public void addCredit(Credit credit){
        credits.add(credit);
    }

    public void removeCredit(Credit credit){
        credits.remove(credit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }

    public ProductionType getType() {
        return productionType;
    }

    public void setType(ProductionType type) {
        this.productionType = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isHasSubtitle() {
        return hasSubtitle;
    }

    public void setHasSubtitle(boolean hasSubtitle) {
        this.hasSubtitle = hasSubtitle;
    }

    public boolean isHasSignLanguage() {
        return hasSignLanguage;
    }

    public void setHasSignLanguage(boolean hasSignLanguage) {
        this.hasSignLanguage = hasSignLanguage;
    }

    public ArrayList<Credit> getCredits() {
        return credits;
    }

    public void setCredits(ArrayList<Credit> credits) {
        this.credits = credits;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    @Override
    public String toString() {
        return name + "," + id + "," + releaseDate + ",";
    }
}
