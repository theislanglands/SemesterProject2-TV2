package domain;

import java.io.Serializable;
import java.util.Date;

//Att. releaseDate refererer til det dato produktionen blev released.
//Skal krediterings-klassen droppes? Skal vi så bruge en hashMap / ArrayList for kreditering inde på den specifikke produktionen?
//toString-metoder for at sende information til databasen.


import java.util.ArrayList;

public class Production implements Serializable {

    //Enums: genre, type, language
    private int id;
    private String productionReference; //e.g. nf221
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
    private String companyProductionName;
    private ArrayList<Credit> credits;

    public Production(int id, String productionReference, String name, Date releaseDate, ArrayList<String> genres, String productionType, int length,
                      String language, boolean hasSubtitle, boolean hasSignLanguage, boolean isActive, boolean isValidated, int recommendedAge,
                      int season, int episode, String productionBio, String companyProductionName, ArrayList<Credit> credits) {
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
        this.companyProductionName = companyProductionName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
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

    public void addGenre(String genre) {
        this.genres.add(genre);
    }

    public String getType() {
        return productionType;
    }

    public void setType(String type) {
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

    public int getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
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

    public boolean hasCredit(Credit credit) {
        for (Credit cred :
                credits) {
            if(cred == credit){
                return true;
            }
        }
        return false;
    }

    public String getCompanyProductionName() {
        return companyProductionName;
    }

    public void setCompanyProductionName(String companyProductionName) {
        this.companyProductionName = companyProductionName;
    }

    public int getProductionTypeId() {
        return 11;
    }

    public int getlanguageId() {
        return 12;
    }

    public int getNameInt(){
        return 13;
    }

    public String getProductionCompanyName() {
        return companyProductionName;
    }

    public String getProductionType() {
        return productionType;
    }

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
                ", companyProductionName='" + companyProductionName + '\'' +
                ", credits=" + credits +
                '}';
    }
}


