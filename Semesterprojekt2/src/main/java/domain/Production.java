package domain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Date;
import java.util.ArrayList;

public class Production {

    // attributes
    private int id;
    private String productionReference; // production company's own reference e.g. nf221
    private String name;
    private Date releaseDate; // Refers to the date, the production was released
    private ArrayList<String> genres;
    private String productionType;  // ex Serie, Film
    private int length;
    private String language;
    private boolean hasSubtitle;
    private boolean hasSignLanguage;
    private boolean isActive; // not used at the moment
    private boolean isValidated;
    private int season;
    private int episode;
    private String productionBio;
    private String productionCompanyName;
    private String imageUrl;

    private ArrayList<Credit> credits;

    // Constructors
    public Production(){
        credits = new ArrayList<>();
    }

    public Production(String productionReference, String name, Date date){
        this.productionReference = productionReference;
        this.name = name;
        this.releaseDate = date;
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


