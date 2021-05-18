package domain;


import domain.Production;

import java.util.Date;
import java.util.List;

public interface TvCreditsInterface {


    List<Production> getProductions();

    // Methods for Enums
    List<String> getCreditTypes();
    List<String> getProductionTypes();
    List<String> getLanguages();
    List<String> getGenres();

}
