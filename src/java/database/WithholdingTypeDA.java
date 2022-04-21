package database;

import domain.WithholdingType;

import java.util.*;
import javax.persistence.*;

public class WithholdingTypeDA {
    private static ArrayList<WithholdingType> withholdingTypes = new ArrayList<WithholdingType>(5);
    
    public static void add(WithholdingType wt) {
        withholdingTypes.add(wt);
    }

    public static ArrayList<WithholdingType> getWithholdingTypes() {
        withholdingTypes.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
	String qString = "SELECT w FROM WithholdingType w";

	TypedQuery<WithholdingType> q = em.createQuery(qString, WithholdingType.class);
	
	List<WithholdingType> with;
        try{
            with = q.getResultList();
            withholdingTypes = new ArrayList(with);
	} finally {
            em.close();
        }
        
        System.out.println("ArrayList from WithholdingTypeDA.getWithholdingTypes() " + with);
	return withholdingTypes;
    }
    
    public static void initialize() {
    }
}