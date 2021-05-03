package domain.TVCreditsPersistance;

import Interfaces.RDBMapper;
import domain.Credit;

// broker - den der forbinder til DB, og opretter et objekt ud fra SQL queries??

public class CreditRDBMapper implements RDBMapper {

    @Override
    public Object get(String ObjektID) {

        // SQL query med
        // resultSet = SQL query med Object ID: SELECT * FROM credits WHERE id = objectID ... etc

        // her skabes en ny credit som udfyldes fra resultSet
        Credit cd = new Credit();
        /*
        cd.setCreditType(resultSet.getColumn("CREDIT_TYPE"));
        cd.setPerson(resultSet.getColumn("PERSON"));
        cd.setValidated(resultSet.getColumn("VALIDATED"));
        cd.setRole(resultSet.getColumn("ROLE"));
        cd.setID(objectID);
        */
        return cd;
    }

    @Override
    public void put(String ObjectId, Object o) {
        // TODO: write method!
    }
}
