package data;
import Interfaces.RDBMapper;
import domain.TVCreditsPersistance.CreditRDBMapper;
import domain.TVCreditsPersistance.ProductionRDBMapper;

public class DataFacade  {
    // laves til singelton


    public Object get(String ObjektID, Class<?> persistenceClass) {

        RDBMapper mapper = null;

        // TODO: kan evt. laves lidt mere fiks så den tager en klasse og returnerer tilhørende mapper uden en lang række af if!
        if (persistenceClass.getName().equals("Credit")) {
            mapper = (RDBMapper) new CreditRDBMapper();
        }


        if (persistenceClass.getName().equals("Production")) {
            mapper = (RDBMapper) new ProductionRDBMapper();
        }


        // uddeleger til relevant mapper!

        return mapper.get(ObjektID);
    }

    public void put(String OID, Object o) {

    }
}