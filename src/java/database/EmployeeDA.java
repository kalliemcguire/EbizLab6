package database;

import domain.Employee;
import exceptions.RecordNotFoundException;
import javax.persistence.*;

import java.util.ArrayList;

public class EmployeeDA {
    
    private static ArrayList<Employee> employees = new ArrayList<Employee>(5);
    
    public static void add(Employee emp) {
        employees.add(emp);
    }
    
    public static Employee find(int ID){
        for (int i = 0; i < employees.size(); i++)
            if (employees.get(i).getEmployeeID() == ID)
                return employees.get(i);
        return null;
    }
    
    public static Employee findByUserID(String userID) throws RecordNotFoundException{
        Employee emp = null;
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        
        String qString = "SELECT emp FROM Employee emp " +
                "WHERE emp.userID = :id";
        System.out.println("User ID: " + userID);
        
        TypedQuery<Employee> e = em.createQuery(qString, Employee.class);
        System.out.println("TypedQuery from EmployeeDA.findByUserID = " + e);
        e.setParameter("id", userID);
        System.out.println("UserID set parameter: " + userID);
        
        try {
            emp = e.getSingleResult();
            System.out.println("Employee: " + emp);
            return emp;
        } catch (NoResultException exc) {
            RecordNotFoundException ex = new RecordNotFoundException("Employee not found");
            throw ex;
        } finally {
            em.close();
        }
    }
    
    public static void initialize(){
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }
    
}