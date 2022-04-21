package database;

import javax.persistence.*;

public class PayrollSystemDA {

private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab_5PU");

    public static void initialize() {
        EmployeeDA.initialize();
        TimecardDA.initialize();
        WithholdingTypeDA.initialize();
    }
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
    
    public static void terminate() {
        emf.close();
    }
}
