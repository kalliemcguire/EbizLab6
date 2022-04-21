package domain;

import java.util.*;
import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
@Table(name = "Hourly_Employee")
public class HourlyEmployee extends Employee{
    @Column(name = "Hourly_Rate")
    private double hourlyRate;
    @Column(name = "Overtime_Rate")
    private double overtimeRate;
    
    public double calculateGrossPay(Date date){
        ArrayList<Timecard> timecards;
        Timecard timecard;
        Date beginDate, endDate, timecardDate;
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar " + calendar);
        double grossPay = 0;
        
        endDate = date;
        System.out.println("endDate " + endDate);
        calendar.setTime(date);
        System.out.println("date " + date);
        calendar.add(Calendar.DATE, -6);
        beginDate = calendar.getTime();
        System.out.println("beginDate " + beginDate);
        
        timecards = Timecard.getEmployeeTimecards(this.getEmployeeID());
        System.out.println("employee ID being passed " + this.getEmployeeID());
        System.out.println("employee timecards " + timecards);
        
        for(int i = 0; i < timecards.size(); i++) {
            timecard = timecards.get(i);
            timecardDate = timecard.getDate();
            System.out.println("timecardDate " + timecardDate);
            if(timecardDate.compareTo(beginDate) >= 0 && timecardDate.compareTo(endDate) <= 0 ){
                System.out.println("compare to beginDate " + timecardDate.compareTo(beginDate));
                System.out.println("compare to endDate " + timecardDate.compareTo(endDate));
                grossPay += timecard.getHoursWorked() * this.getHourlyRate();
                grossPay += timecard.getOvertimeHours() * this.getHourlyRate() * this.getOvertimeRate();
            }
        }
        return grossPay;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }
    
    public String toString(){
        return super.toString() + "  " + hourlyRate + "  " + overtimeRate;
    }
}