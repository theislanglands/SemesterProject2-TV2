package domain;


import domain.Production;

import java.util.Date;
import java.util.List;

public interface TvCreditsInterface {

    List<Production> getAllProductions();
    Production getProduction(int productionId);
    List<Production> getValidatedProductions();
    List<Production> getUnValidatedProductions();

    boolean saveProduction(Production prod);

    boolean updateProduction(int productionID, Production replaceProduction);

    // Methods for Enums
    List<String> getCreditTypes();
    List<String> getProductionTypes();
    List<String> getLanguages();
    List<String> getGenres();


}
