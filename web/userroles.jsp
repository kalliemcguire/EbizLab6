<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Roles</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
    </head>
    <body>
        <h1>User Roles</h1>
        <table>
            <tr>
                <th>Role ID</th>
                <th>Role Description</th>
                <th>View, add, update, delete all Timecards</th>
                <th>View, add, update, delete all User Roles</th>
                <th>Calculate Payroll</th>
                <th>View own Timecards</th>
                <th>View own Payroll Records</th>
            </tr>
            <c:forEach var="role" items="${allRoles}">
                <tr>
                    <td><c:out value='${role.userRoleID}'/></td>
                    <td class="right"><c:out value='${role.description}'/></td>
                    <td class="right"><c:out value='${role.allTimecards}'/></td>
                    <td class="right"><c:out value='${role.allUserRoles}'/></td>
                    <td class="right"><c:out value='${role.calculatePayroll}'/></td>
                    <td class="right"><c:out value='${role.viewOwnTimecards}'/></td>
                    <td class="right"><c:out value='${role.viewOwnPayroll}'/></td>
                <td>
                    <form action="timecard" method="post">
                        <input type="hidden" name="option" value="updateUserRole">
                        <input type="hidden" name="id" value="${role.userRoleID}">
                        <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="timecard" method="post">
                        <input type="hidden" name="option" value="deleteUserRole">
                        <input type="hidden" name="id" value="${role.userRoleID}">
                        <input type="submit" value="Delete">
                        </form>
                    </td>
            </c:forEach>
        </table>
        <form action="timecard" method="post">
            <input type="hidden" name="option" value="addUserRole">
            <input type="submit" value="Add User Role">
        </form>
        <form action="main.jsp" method="post">
            <input type="hidden" name="option" value="return">
            <input type="submit" value="Return to Main Menu">
        </form>
    </body>
</html>
