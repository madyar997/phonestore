<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html>
<head>
    <title>User Menu</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<br>
<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <div class="container text-left">
            <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 2rem"><fmt:message key="label.personal_data"/></a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="label.first_name"/></th>
                <th><fmt:message key="label.second_name"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.phone_number"/></th>
                <th><fmt:message key="label.address"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <!--   for (Todo todo: todos) {  -->

                <tr>

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
                        <c:out value="${user.address}"/>
                    </td>
                    <td>
                        <a href="/phonestore/user/personal-data-form?id=<c:out value='${user.userId}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            <!-- } -->
            </tbody>

        </table>
    </div>
    <div class="container">
        <div class="container text-left">
            <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 2rem"><fmt:message key="label.my_orders"/></a>
        </div>

        <br>
        <c:forEach items="${orders}" var="order">
            <div class="container text-left">
                <a style="font-family: 'Segoe UI',sans-serif;font-weight: bolder;font-size: 1rem"><fmt:message key="label.order_number"/> ${order.key.id} ${order.key.orderDateTime} STATUS: ${order.key.status}</a>
                <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 1rem"><fmt:message key="label.total"/>: ${order.key.totalCost} KZT  </a>
            </div>
            <c:forEach items="${order.value}" var="phone">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th><fmt:message key="label.quantity"/></th>
                        <th><fmt:message key="label.brand"/></th>
                        <th><fmt:message key="label.model"/></th>
                        <th><fmt:message key="label.color"/></th>
                        <th><fmt:message key="label.price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <!--   for (Todo todo: todos) {  -->

                    <tr>

                        <td>
                            <c:out value="${phone.key.id}"/>
                        </td>
                        <td>
                            <c:out value="${phone.value}"/>
                        </td>
                        <td>
                            <c:out value="${phone.key.brand}"/>
                        </td>
                        <td>
                            <c:out value="${phone.key.model}"/>
                        </td>
                        <td>
                            <c:out value="${phone.key.color}"/>
                        </td>
                        <td>
                            <c:out value="${phone.key.price}"/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:forEach>


        </c:forEach>


    </div>
</div>
</body>
</html>
