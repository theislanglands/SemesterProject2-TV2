package domain;


import domain.Production;


import java.util.List;

public interface TvCreditsInterface {

    // getters for Productions
    Production getProduction(int productionId);
    List<Production> getAllProductions();
    List<Production> getValidatedProductions();
    List<Production> getUnValidatedProductions();

    // save & update production (for producers)
    boolean saveProduction(Production prod);
    boolean updateProduction(int productionID, Production replaceProduction);

    // validate productions & credits(for admin)
    void validateProduction(Production prod);
    void invalidateProduction(Production prod);
    void validateCredit(Credit credit);
    void invalidateCredit(Credit credit);

    // Credits
    void addCredit(Credit credit);
    void deleteCredit(Credit credit);

    // Methods for Enums
    List<String> getCreditTypes();
    List<String> getProductionTypes();
    List<String> getLanguages();
    List<String> getGenres();
}
