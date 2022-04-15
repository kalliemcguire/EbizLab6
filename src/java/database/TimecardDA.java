package database;

import domain.Timecard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class TimecardDA {
    private static ArrayList<Timecard> timecards = new ArrayList<Timecard>(5);
    private static ArrayList<Timecard> employeeTimecards = new ArrayList<Timecard>();
    
    public static void add(Timecard tc) {
        tc.setTimecardID(timecards.size());
        timecards.add(tc);
    }
    
    public static void delete(Timecard t){
        timecards.remove(t);
    }
    
    public static Timecard find(int id){
        Timecard timecard= null;
        for(int i = 0; i<timecards.size(); i++)
            if(timecards.get(i).getTimecardID() == id)
                return timecards.get(i);
        return timecard;
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
		}
		finally {em.close();}

		return employeeTimecards;
    }
    
    public static ArrayList<Timecard> getEmployeeTimecards(int ID, Date begDate, Date endDate) {
        employeeTimecards.clear();
        Timecard timecard = null;
                
        for (int i = 0; i < timecards.size(); i++){
            timecard = timecards.get(i);

            if (timecard.getEmployeeID() == ID){
                if(timecard.getDate().compareTo(begDate) >= 0 && timecard.getDate().compareTo(endDate) <= 0)
                    employeeTimecards.add(timecard);
            }
        }
        
        return employeeTimecards;
    }
    
    public static void update(Timecard tc) {
        Timecard timecard = find(tc.getTimecardID());
        timecard.setDate(tc.getDate());
        timecard.setHoursWorked(tc.getHoursWorked());
        timecard.setOvertimeHours(tc.getOvertimeHours());
    }
}