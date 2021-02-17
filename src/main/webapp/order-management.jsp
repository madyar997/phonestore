<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="container text-left">
        <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 2rem"><fmt:message key="label.all_orders"/></a>
    </div>

    <br>
    <c:forEach items="${orders}" var="order">
        <div class="container text-left">
            <a style="font-family: 'Segoe UI',sans-serif;font-weight: bolder;font-size: 1rem"><fmt:message key="label.order_number"/> ${order.key.id} ${order.key.orderDateTime} STATUS: ${order.key.status}</a>
            <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 1rem"><fmt:message key="label.total"/>: ${order.key.totalCost} KZT  </a>
        </div>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="label.customer_id"/></th>
                <th><fmt:message key="label.full_name"/></th>
                <th><fmt:message key="label.date"/></th>
                <th><fmt:message key="label.status"/></th>
                <th><fmt:message key="label.shipping_address"/></th>
                <th><fmt:message key="label.phone_number"/></th>
                <th><fmt:message key="label.city"/></th>
                <th><fmt:message key="label.zip"/></th>
                <th><fmt:message key="label.details"/></th>
                <th><fmt:message key="label.total"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>



                <tr>
                    <td>
                        <c:out value="${order.key.id}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.customerId}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.fullName}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.orderDateTime}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.status}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.address}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.phoneNumber}"/>
                    </td>

                    <td>
                        <c:out value="${order.key.city}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.zip}"/>
                    </td>

                    <td>
                        <c:out value="${order.key.details}"/>
                    </td>
                    <td>
                        <c:out value="${order.key.totalCost}"/>
                    </td>

                    <td>
                        <a href="/phonestore/admin/order-management/edit/form?id=<c:out value='${order.key.id}' />"><fmt:message key="label.edit"/></a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/phonestore/admin/order-management/delete?id=<c:out value='${order.key.id}' />"><fmt:message key="label.delete"/></a>
                    </td>
                </tr>

            </tbody>

        </table>
        <div class="container text-left">
            <a style="font-family: 'Segoe UI',sans-serif;font-weight: bold;font-size: 1rem"><fmt:message key="label.order_content"/>:</a>
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
        <br>
        <br>
        <br>

    </c:forEach>


</div>
</body>
</html>
