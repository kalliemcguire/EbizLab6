<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Successful</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
    </head>
    <body>
        <h1>ABC Corporation Payroll System</h1>
        <h2>Welcome ${employee.firstName}</h2>
        <h3>Select an option</h3>
        <p>${message}</p>
        <form action="timecard" method="post">
            <label>Update My Timecards</label>
            <input type="hidden" name="option" value="list">
            <input type="submit" value="Select">
        </form>
        <br>
        <c:if test="${userRole.allTimecards}">
        <form action ="timecard" method="post">
            <label>Update All Timecards</label>
            <input type="hidden" name="option" value="listAll">
            <input type="submit" value="Select">
        </form>
        <br>
        </c:if>
        <c:if test="${userRole.calculatePayroll}">
        <form action="timecard" method="post">
            <label>Calculate Payroll</label>
            <input type="text" name="prDate" value="${prDate}"required>
            <input type="hidden" name="option" value="payroll">
            <input type="submit" value="Calculate">
        </form>
        </c:if>
        <br>    
        <form action="timecard" method="post">
            <label>View My Payroll Records</label>
            <input type="hidden" name="option" value="ownPayroll">
            <input type="submit" value="Select">
        </form>
        <br>
        <c:if test="${userRole.allUserRoles}">
        <form action="timecard" method="post">
            <label>Update User Roles</label>
            <input type="hidden" name="option" value="userRoles">
            <input type="submit" value="Select">
        </form>
        </c:if>
    </body>
</html>