package database;

import domain.Timecard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class TimecardDA {
    private static ArrayList<Timecard> employeeTimecards = new ArrayList<Timecard>();
    
    public static void add(Timecard tc) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.persist(tc);
            System.out.println("Timecard to add " + tc);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static void delete(Timecard tc){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.remove(em.merge(tc));
            System.out.println("Timecard to delete " + tc);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static Timecard find(int id){
        Timecard tc;
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        try {
            tc = em.find(Timecard.class, id);
            return tc;
        }
        finally {em.close();}
    }
    
    public static void initialize(){
    }

    public static ArrayList<Timecard> getEmployeeTimecards(int userID) {
        employeeTimecards.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
	String qString = "SELECT tc FROM Timecard tc" +
                       " WHERE tc.employeeID = :id";
        //employeeID is an attribute name even though it is in a query
	TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        q.setParameter("id", userID);
	
        //list is superclass for arraylist
	List<Timecard> tCards;
        try{
        //returns list object, then create arraylist and pass it the list object
            tCards = q.getResultList();
            employeeTimecards = new ArrayList(tCards);
        } finally {
            em.close();
        }

    return employeeTimecards;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) {
        employeeTimecards.clear();
        
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        String qString = "SELECT tc FROM Timecard tc" +
                " WHERE tc.timecardID = :ID";
        TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        q.setParameter("ID", ID);
        
        List<Timecard> tCards;
        try {
            tCards = q.getResultList();
            employeeTimecards = new ArrayList(tCards);
        } finally {
            em.close();
        }
        
        return employeeTimecards;
    }
    
    public static ArrayList<Timecard> getAllTimecards() {
        employeeTimecards.clear();
        
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        String qString = "SELECT tc FROM Timecard tc";
        
        TypedQuery<Timecard> q = em.createQuery(qString, Timecard.class);
        
        List<Timecard> allTimecards;
        try {
            allTimecards = q.getResultList();
            employeeTimecards = new ArrayList(allTimecards);
        } finally {
            em.close();
        }
        
        return employeeTimecards;
    }
    
    public static void update(Timecard tc) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.merge(tc);
            System.out.println("Timecard to update " + tc);
            transact.commit();
        } finally {
            em.close();
        }
    }
}