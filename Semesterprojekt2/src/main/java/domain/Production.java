package domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Date;

//Att. releaseDate refererer til det dato produktionen blev released.
//Skal krediterings-klassen droppes? Skal vi så bruge en hashMap / ArrayList for kreditering inde på den specifikke produktionen?
//toString-metoder for at sende information til databasen.


import java.util.ArrayList;

public class Production {

    // attributes
    private int id;
    private String productionReference; // productino company's own e.g. nf221
    private String name;
    private Date releaseDate;
    private ArrayList<String> genres;
    private String productionType;
    private int length;
    private String language;
    private boolean hasSubtitle;
    private boolean hasSignLanguage;
    private boolean isActive;
    private boolean isValidated;
    private int recommendedAge;
    private int season;
    private int episode;
    private String productionBio;
    private String productionCompanyName;
    private String imageUrl;

    private ArrayList<Credit> credits;

    // Constructors
    public Production(int id, String productionReference, String name, Date releaseDate, ArrayList<String> genres, String productionType, int length,
                      String language, boolean hasSubtitle, boolean hasSignLanguage, boolean isActive, boolean isValidated, int recommendedAge,
                      int season, int episode, String productionBio, String productionCompanyName, ArrayList<Credit> credits) {
        this.id = id;
        this.productionReference = productionReference;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.productionType = productionType;
        this.length = length;
        this.language = language;
        this.hasSubtitle = hasSubtitle;
        this.hasSignLanguage = hasSignLanguage;
        this.isActive = isActive;
        this.isValidated = isValidated;
        this.recommendedAge = recommendedAge;
        this.season = season;
        this.episode = episode;
        this.productionBio = productionBio;
        this.productionCompanyName = productionCompanyName;
        this.credits = credits;
    }

    public Production(String productionReference, String name, Date date){
        this.productionReference = productionReference;
        this.name = name;
        this.releaseDate = date;
        credits = new ArrayList<>();
    }

    public Production(){
        credits = new ArrayList<>();
    }

    //Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getProductionBio() {
        return productionBio;
    }
    public void setProductionBio(String productionBio) {
        this.productionBio = productionBio;
    }

    public void addCredit(Credit credit){
        credits.add(credit);
    }
    public void removeCredit(Credit credit){
        credits.remove(credit);
    }

    public String getProductionReference() {
        return productionReference;
    }
    public void setProductionReference(String productionReference) {
        this.productionReference = productionReference;
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

    public ArrayList<String> getGenres() {
        return this.genres;
    }
    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setProductionType(String type) {
        this.productionType = type;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean hasSubtitle() {
        return hasSubtitle;
    }
    public void setSubtitle(boolean hasSubtitle) {
        this.hasSubtitle = hasSubtitle;
    }

    public boolean hasSignLanguage() {
        return hasSignLanguage;
    }
    public void setSignLanguage(boolean hasSignLanguage) {
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

    public int getSeason() {
        return season;
    }
    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }
    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getProductionCompanyName() {
        return productionCompanyName;
    }
    public void setProductionCompanyName(String productionCompanyName) {
        this.productionCompanyName = productionCompanyName;
    }

    public String getProductionType() {
        return productionType;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /* BLIVER DEN BRUGT?
    public ImageView getImage(){
        Image image = new Image(getImageUrl());
        return new ImageView(image);
    }
    */

    //toString method
    @Override
    public String toString() {
        return "Production{" +
                "id=" + id +
                ", productionReference='" + productionReference + '\'' +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genres +
                ", productionType='" + productionType + '\'' +
                ", length=" + length +
                ", language='" + language + '\'' +
                ", hasSubtitle=" + hasSubtitle +
                ", hasSignLanguage=" + hasSignLanguage +
                ", isActive=" + isActive +
                ", isValidated=" + isValidated +
                ", recommendedAge=" + recommendedAge +
                ", season=" + season +
                ", episode=" + episode +
                ", productionBio='" + productionBio + '\'' +
                ", companyProductionName='" + productionCompanyName + '\'' +
                '}' + "\n";
    }

    // True/false if production has credits
    public boolean hasCredits(Credit credit) {
        for (Credit cred : credits) {
            if(cred.equals(credit)) {
                return true;
            }
        }
        return false;
    }
}


