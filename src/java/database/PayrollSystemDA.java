package database;

import javax.persistence.*;

public class PayrollSystemDA {
    
    
//can put the createEntityManagerFactory in initialize, put initialize at beginning of each servlet
//then have a terminate method that closes it and put it at the end of each servlet
//terminate method should fix the problem of having to restart the glassfish server
private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab_5PU");

    public static void initialize() {
        EmployeeDA.initialize();
        TimecardDA.initialize();
        WithholdingTypeDA.initialize();
    }
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
