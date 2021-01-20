<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<jsp:include page="header.jsp"/>
<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center"><fmt:message key="label.list_users"/></h3>
        <hr>
        <div class="container text-left">

            <a href="/phonestore/admin/user-management/create/form" class="btn btn-success">Add New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="label.first_name"/></th>
                <th><fmt:message key="label.second_name"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.phone_number"/></th>
                <th><fmt:message key="label.role"/></th>
                <th><fmt:message key="label.address"/></th>
                <th><fmt:message key="label.password"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="user" items="${users}">

                <tr>
                    <td>
                        <c:out value="${user.userId}"/>
                    </td>
                    <td>
                        <c:out value="${user.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${user.secondName}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.phoneNumber}"/>
                    </td>
                    <td>
                        <c:out value="${user.role}"/>
                    </td>
                    <td>
                        <c:out value="${user.address}"/>
                    </td>
                    <td>
                        <c:out value="${user.password}"/>
                    </td>
                    <td>
                        <a href="/phonestore/admin/user-management/edit/form?id=<c:out value='${user.userId}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/phonestore/admin/user-management/delete?id=<c:out value='${user.userId}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>