<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Payroll Records</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
    </head>
        <body>
        <h1>Payroll Records</h1>
        <table>
            <tr>
                <th>Date</th>
                <th>Employee ID</th>
                <th>Gross Pay</th>
                <th>Deductions</th>
                <th>Net Pay</th>
            </tr>
            <c:forEach var="record" items="${payrollRecords}">
                <tr>
                    <td><c:out value='${record.dateFormatted}'/></td>
                    <td class="right"><c:out value='${record.employeeID}'/></td>
                    <td class="right"><c:out value='${record.grossPayFormatted}'/></td>
                    <td class="right"><c:out value='${record.totalDeductionsFormatted}'/></td>
                    <td class="right"><c:out value='${record.netPayFormatted}'/></td>
            </c:forEach>
        </table>
        <form action="main.jsp" method="post">
            <input type="hidden" name="option" value="return">
            <input type="submit" value="Return to Main Menu">
        </form>
    </body>
</html>
