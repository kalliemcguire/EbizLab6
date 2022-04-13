package domain;

import database.EmployeeDA;
import exceptions.RecordNotFoundException;
import java.io.Serializable;

import java.util.Date;

import java.util.ArrayList;
import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Employee_Type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Employee implements Serializable{
    private static final int HOURLY = 1, SALARY = 2;
    
    @Id
    @Column(name = "Employee_ID")
    private int employeeID;
    @Column(name = "Employee_Type")
    private int employeeType;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    private long SSN;
    @Column(name = "User_ID")
    private String userID;
    @Column(name = "Password")
    private String password;
    
    public Employee(){}
    
    public void add() {
        EmployeeDA.add(this);
    }
    
    public double calculateGrossPay(Date date) {
        return 0.0;
    }
    
    public static Employee find(int ID){
        return EmployeeDA.find(ID);
    }
    
    public static Employee findByUserID(String userID)throws RecordNotFoundException{
        return EmployeeDA.findByUserID(userID);
    }

    public int getEmployeeID() {
        return employeeID;
    }
    
    public static ArrayList<Employee> getEmployees() {
        return EmployeeDA.getEmployees();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public long getSSN() {
        return SSN;
    }

    public String getUserID() {
        return userID;
    }

    public void setEmployeeType(int employeeType) {
        this.employeeType = employeeType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setHourlyRate(double hR) {}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setOvertimeRate(double or) {}

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setSalary(double s) {}

    public void setSSN(long SSN) {
        this.SSN = SSN;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String toString(){
        return employeeID + "  " + lastName + ", " + firstName + "  " + SSN ;
    }
}