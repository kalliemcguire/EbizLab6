package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
@Table(name = "Salary_Employee")
public class SalaryEmployee extends Employee implements Serializable{
    @Column(name = "Salary")
    private double salary;
    
    public double calculateGrossPay(Date date) {
        return salary/52.0;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double s) {
        this.salary = s;
    }
    
    public String toString(){
        return super.toString() + "  " + salary;
    }
}