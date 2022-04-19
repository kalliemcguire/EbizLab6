package domain;

import database.WithholdingTypeDA;
import java.io.Serializable;

import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "Withholding_Type")
public class WithholdingType implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Withholding_ID")
    private int ID;
    @Column(name = "Withholding_Description")
    private String description;
    @Column(name = "Withholding_Amount")
    private double amount;
    @Column(name = "Withholding_Rate")
    private double rate;

    public WithholdingType() {
    }
    
    public void add() {
        WithholdingTypeDA.add(this);
    }

    public double getAmount() {
        return amount;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getID() {
        return ID;
    }
    
    public double getRate() {
        return rate;
    }
    
    public static ArrayList<WithholdingType> getWithholdingTypes() {
        return WithholdingTypeDA.getWithholdingTypes();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public String toString(){
        return ID + "  " + description + "  " + amount + "  " + rate; 
    }
}