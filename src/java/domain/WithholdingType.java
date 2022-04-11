package domain;

import database.WithholdingTypeDA;
import java.io.Serializable;

import java.util.ArrayList;

public class WithholdingType implements Serializable{
    private int ID;
    private String description;
    private double amount;
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