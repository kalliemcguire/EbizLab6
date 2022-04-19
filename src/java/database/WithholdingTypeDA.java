package database;

import domain.WithholdingType;

import java.util.ArrayList;

public class WithholdingTypeDA {
    private static ArrayList<WithholdingType> withholdingTypes = new ArrayList<WithholdingType>(5);
    
    public static void add(WithholdingType wt) {
        withholdingTypes.add(wt);
    }

    public static ArrayList<WithholdingType> getWithholdingTypes() {
        return withholdingTypes;
    }
    
    public static void initialize() {
    }
}