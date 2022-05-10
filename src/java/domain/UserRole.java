package domain;

import database.UserRoleDA;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UserRole implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_ID")
    private int userRoleID;
    @Column(name = "RoleDescription")
    private String description;
    @Column(name = "AllTC")
    private boolean allTimecards;
    @Column(name = "AllRoles")
    private boolean allUserRoles;
    @Column(name = "CalculatePayroll")
    private boolean calculatePayroll;
    @Column(name = "OwnTC")
    private boolean viewOwnTimecards;
    @Column(name = "OwnPayroll")
    private boolean viewOwnPayroll;
    
    public UserRole(){
        this.setUserRoleID(0);
        this.setDescription("");
        this.setAllTimecards(false);
        this.setAllUserRoles(false);
        this.setCalculatePayroll(false);
        this.setViewOwnTimecards(false);
        this.setViewOwnPayroll(false);
    }
    
    public void add() {
        UserRoleDA.add(this);
    }
    
    public void delete() {
        UserRoleDA.delete(this);
    }
    
    public void update() {
        UserRoleDA.update(this);
    }
    
    public static UserRole find(int id) {
        return UserRoleDA.find(id);
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(int userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllTimecards() {
        return allTimecards;
    }

    public void setAllTimecards(boolean allTimecards) {
        this.allTimecards = allTimecards;
    }

    public boolean isAllUserRoles() {
        return allUserRoles;
    }

    public void setAllUserRoles(boolean allUserRoles) {
        this.allUserRoles = allUserRoles;
    }

    public boolean isCalculatePayroll() {
        return calculatePayroll;
    }

    public void setCalculatePayroll(boolean calculatePayroll) {
        this.calculatePayroll = calculatePayroll;
    }

    public boolean isViewOwnTimecards() {
        return viewOwnTimecards;
    }

    public void setViewOwnTimecards(boolean viewOwnTimecards) {
        this.viewOwnTimecards = viewOwnTimecards;
    }

    public boolean isViewOwnPayroll() {
        return viewOwnPayroll;
    }

    public void setViewOwnPayroll(boolean viewOwnPayroll) {
        this.viewOwnPayroll = viewOwnPayroll;
    }

    @Override
    public String toString() {
        return "UserRole: " + "userRoleID= " + userRoleID + ", description= " + description + ", allTimecards= " + allTimecards + ", allUserRoles= " 
                + allUserRoles + ", calculatePayroll= " + calculatePayroll + ", viewOwnTimecards= " + viewOwnTimecards + ", viewOwnPayroll= " + viewOwnPayroll;
    }
}
