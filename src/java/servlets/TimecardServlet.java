package servlets;

import database.PayrollDA;
import database.UserRoleDA;
import domain.*;

import java.util.*;
import java.text.DateFormat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TimecardServlet extends HttpServlet {
    
    private static DateFormat dateFormatShort = DateFormat.getDateInstance(DateFormat.SHORT);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String idString;
        int id;
        String roleIdString;
        
        HttpSession session = request.getSession();
        Employee employee = (Employee)session.getAttribute("employee");
        PayrollSystem.initialize();
        
        String url = "/main.jsp";
        String option = request.getParameter("option");

        if (option.equals("add")){
            Timecard timecard = new Timecard();
            session.setAttribute("timecard", timecard);
            url = "/timecard.jsp";
        }
        
        if (option.equals("delete")){
            idString = request.getParameter("id");
            id = Integer.parseInt(idString);
            Timecard timecard = Timecard.find(id);
            timecard.delete();
            //Employee employee = (Employee)session.getAttribute("employee");
            id = employee.getEmployeeID();
            ArrayList timecards = Timecard.getEmployeeTimecards(id);
            session.setAttribute("timecards", timecards);
            url = "/timecardList.jsp";
        }
        
        if (option.equals("list")){
            //Employee employee = (Employee)session.getAttribute("employee");
            id = employee.getEmployeeID();

            if(employee.getClass().getSimpleName().equals("HourlyEmployee")) {
                ArrayList timecards = Timecard.getEmployeeTimecards(id);
                session.setAttribute("timecards", timecards);
                url = "/timecardList.jsp";
            }
            
            else {
                System.out.println("Salaried employee");
                String message = "There are no timecards for salaried employees";
                session.setAttribute("message", message);
            }
        }
        
        if (option.equals("listAll")) {
            ArrayList timecards = Timecard.getAllTimecards();
            session.setAttribute("timecards", timecards);
            url = "/timecardList.jsp";
        }
        
        if (option.equals("save")){
            Timecard timecard = (Timecard)session.getAttribute("timecard");
            
            //Employee employee = (Employee)session.getAttribute("employee");
            id = employee.getEmployeeID();
            
            String dateString = request.getParameter("date");
            String hoursString = request.getParameter("hours");
            String overtimeString = request.getParameter("overtime");
            Date date = new Date();
            try {
                date = dateFormatShort.parse(dateString);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
            double hours = Double.parseDouble(hoursString);
            double overtime = Double.parseDouble(overtimeString);
            
            timecard.setDate(date);
            timecard.setHoursWorked(hours);
            timecard.setOvertimeHours(overtime);
                
            if (timecard.getTimecardID() < 0) {
                timecard.setEmployeeID(id);
                timecard.add();
            }
            
            else {
                timecard.update();
            }
            
            ArrayList timecards = Timecard.getEmployeeTimecards(id);
            session.setAttribute("timecards", timecards);
            url = "/timecardList.jsp";
        }
        
        if (option.equals("update")){
            idString = request.getParameter("id");
            id = Integer.parseInt(idString);
            Timecard timecard = Timecard.find(id);
            session.setAttribute("timecard", timecard);
            url = "/timecard.jsp";
        }
        
        if (option.equals("payroll")){
            String dateString = request.getParameter("prDate");
            System.out.println("dateString " + dateString);
            Date date = new Date();
            try {
                date = dateFormatShort.parse(dateString);
                System.out.println("parsed dateString " + date);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
            Payroll.calculatePayroll(date);
            ArrayList<Payroll> payrollRecords = PayrollDA.getPayrollRecords(date);
            session.setAttribute("payrollRecords", payrollRecords);
            url = "/payroll.jsp";
        }
        
        if (option.equals("ownPayroll")) {
            //Employee employee = (Employee)session.getAttribute("employee");
            id = employee.getEmployeeID();
            ArrayList<Payroll> empPayrollRecords = PayrollDA.getEmployeePayrollRecords(id);
            session.setAttribute("empPayrollRecords", empPayrollRecords);
            url = "/ownpayroll.jsp";
        }
        
        if (option.equals("userRoles")) {
            ArrayList<UserRole> allRoles = UserRoleDA.getAllRoles();
            session.setAttribute("allRoles", allRoles);
            url = "/userroles.jsp";
        }
        
        //something wrong here or in my jsps making the addUserRole, updateUserRole, and saveUserRole options not work
        if (option.equals("addUserRole")) {
            UserRole newRole = new UserRole();
            session.setAttribute("role", newRole);
            System.out.println(newRole);
            UserRoleDA.add(newRole);
            ArrayList<UserRole> allRoles = UserRoleDA.getAllRoles();
            session.setAttribute("allRoles", allRoles);
            url = "/newrole.jsp";
        }
        
        if (option.equals("updateUserRole")) {
            idString = request.getParameter("roleID");
            id = Integer.parseInt(idString);
            UserRole u = UserRole.find(id);
            session.setAttribute("userRole", u);
            url = "/userroles.jsp";
        }
        
        if (option.equals("saveUserRole")) {
            roleIdString = request.getParameter("roleID");
            id = Integer.parseInt(roleIdString);
            UserRole u = UserRole.find(id);
            System.out.println(u);
            
            String descString = request.getParameter("description");
            String allTC = request.getParameter("alltimecards");
            String allURoles = request.getParameter("alluserroles");
            String calcPR = request.getParameter("calculatepayroll");
            String ownTC = request.getParameter("viewowntimecards");
            String ownPR = request.getParameter("viewownpayroll");
            
            boolean allTimecards = Boolean.parseBoolean(allTC);
            boolean allUserRoles = Boolean.parseBoolean(allURoles);
            boolean calculatePayroll = Boolean.parseBoolean(calcPR);
            boolean viewOwnTimecards = Boolean.parseBoolean(ownTC);
            boolean viewOwnPayroll = Boolean.parseBoolean(ownPR);
            
            u.setDescription(descString);
            u.setAllTimecards(allTimecards);
            u.setAllUserRoles(allUserRoles);
            u.setCalculatePayroll(calculatePayroll);
            u.setViewOwnTimecards(viewOwnTimecards);
            u.setViewOwnPayroll(viewOwnPayroll);
            
            UserRoleDA.update(u);
            
            ArrayList<UserRole> allRoles = UserRoleDA.getAllRoles();
            session.setAttribute("allRoles", allRoles);
            url = "/userroles.jsp";
        }
        
        //this is working fine
        if (option.equals("deleteUserRole")) {
            roleIdString = request.getParameter("roleID");
            id = Integer.parseInt(roleIdString);
            UserRole u = UserRole.find(id);
            u.delete();
            ArrayList<UserRole> allRoles = UserRoleDA.getAllRoles();
            session.setAttribute("allRoles", allRoles);
            url = "/userroles.jsp";
        }
        
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}