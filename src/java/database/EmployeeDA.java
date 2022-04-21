package database;

import domain.Employee;
import exceptions.RecordNotFoundException;
import javax.persistence.*;

import java.util.*;

public class EmployeeDA {
    
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    
    public static void add(Employee emp) {
    EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.persist(emp);
            System.out.println("Employee to add " + emp);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static Employee find(int ID){
        Employee emp;
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        try {
            emp = em.find(Employee.class, ID);
            return emp;
        }
        finally {em.close();}
    }
    
    public static void update(Employee emp){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.merge(emp);
            System.out.println("Employee to update " + emp);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static void delete(Employee emp){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.remove(em.merge(emp));
            System.out.println("Employee to delete " + emp);
            transact.commit();
        } finally {
            em.close();
        }
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
        employees.clear();
                
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
	String qString = "SELECT e FROM Employee e";

	TypedQuery<Employee> q = em.createQuery(qString, Employee.class);
	
	List<Employee> emps;
            try{
		emps = q.getResultList();
		employees = new ArrayList(emps);
		} finally {
                    em.close();
                }
        System.out.println("employees ArrayList from EmployeeDA.getEmployees() " + employees);
	return employees;
    }
    
}