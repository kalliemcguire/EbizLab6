<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/> 
    </head>
        <body>
        <h1>Update User Roles</h1>
        <form action="userroles.jsp" method="post">
            <input type="hidden" name="option" value="saveUserRole">
            <label class="pad_top">Description</label>
            <input type="text" name="description" value="${role.Description}" required>
            <br>
            <label class="pad_top">View, add, update, delete all Timecards</label>
            <input type="text" name="alltimecards" value="${role.allTimecards}" required>
            <br>
            <label class="pad_top">View, add, update, delete all User Roles</label>
            <input type="text" name="alluserroles" value="${role.allUserRoles}">
            <br>
            <label class="pad_top">Calculate Payroll</label>
            <input type="text" name="calculatepayroll" value="${role.calculatePayroll}">
            <br>
            <label class="pad_top">View own Timecards</label>
            <input type="text" name="viewowntimecards" value="${role.viewOwnTimecards}">
            <br>
            <label class="pad_top">View own Payroll Records</label>
            <input type="text" name="viewownpayroll" value="${role.viewOwnPayroll}">
            <br>
            <label>&nbsp;</label>
            <form action="userroles.jsp">
            <input type="submit" value="Save" class="margin_left">
        </form>
            <label>&nbsp;</label>
            <form action="userroles.jsp">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
