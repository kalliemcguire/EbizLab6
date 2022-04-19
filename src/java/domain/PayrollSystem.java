package domain;

import database.PayrollSystemDA;
import exceptions.LoginException;
import exceptions.RecordNotFoundException;

public class PayrollSystem {
    private static boolean isInitialized = false;

    public static void initialize() {
        
        if (!isInitialized)
            PayrollSystemDA.getEmFactory();
        isInitialized = true;
    }
    
    public static Employee login(String userID, String password) throws LoginException {
        Employee employee = null;
        
        try{
            employee = Employee.findByUserID(userID);
            
            if(employee.getPassword().equals(password))
                return employee;
            else{
                LoginException e = new LoginException("Invalid Password");
                throw e;
            }
        }
        
        catch(RecordNotFoundException e){
            LoginException ex = new LoginException("Employee " + userID + " not Found");
            throw ex;
        }
        
        catch(LoginException e){
            throw e;
        }
    }
}