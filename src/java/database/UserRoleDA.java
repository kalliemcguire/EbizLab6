package database;

import domain.UserRole;
import javax.persistence.*;
import java.util.*;

public class UserRoleDA {
    
    private static ArrayList<UserRole> allRoles = new ArrayList<UserRole>();
    
    public static void add(UserRole u) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.persist(u);
            System.out.println("User role to add " + u);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static void delete(UserRole u){
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.remove(em.merge(u));
            System.out.println("User role to delete " + u);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static void update(UserRole u) {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        EntityTransaction transact = em.getTransaction();
        try {
            transact.begin();
            em.merge(u);
            System.out.println("User role to update " + u);
            transact.commit();
        } finally {
            em.close();
        }
    }
    
    public static UserRole findByRole(int userRoleID) {
        UserRole u = null;
        
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        try {
        u = em.find(UserRole.class, userRoleID);
        return u;
        } finally {
            em.close();
        }
    }
    
    public static ArrayList<UserRole> getAllRoles() {
        EntityManager em = PayrollSystemDA.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM UserRole u";

        TypedQuery<UserRole> q = em.createQuery(qString, UserRole.class);
	
	List<UserRole> uRoles;
	try{
            uRoles = q.getResultList();
            allRoles = new ArrayList(uRoles);
	} finally {em.close();}
        return allRoles;
    }
    
}
