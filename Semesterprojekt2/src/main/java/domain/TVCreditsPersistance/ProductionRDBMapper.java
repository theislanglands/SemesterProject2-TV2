package domain.TVCreditsPersistance;

// broker - den der forbinder til DB, og opretter et objekt ud fra SQL queries!

import Interfaces.RDBMapper;

public class ProductionRDBMapper implements RDBMapper {

    @Override
    public Object get(String ObjectId) {
        return null;
    }

    @Override
    public void put(String ObjectId, Object o) {

    }
}
