<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html>

<head>
    <title>Add new user</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<jsp:include page="header.jsp"/>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="/phonestore/admin/order-management/edit" method="post">
                <caption>
                    <h2><fmt:message key="label.edit"/></h2>
                </caption>
                <input type="hidden" name="id" value="<c:out value='${order.id}' />"/>

                <fieldset class="form-group">
                    <label><fmt:message key="label.customer_id"/></label> <input type="text" value="<c:out value='${order.customerId}' />"
                                                                               class="form-control" name="customer_id" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.full_name"/></label> <input type="text" value="<c:out value='${order.fullName}' />"
                                                                                class="form-control" name="full_name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.phone_number"/></label> <input type="text" value="<c:out value='${order.phoneNumber}' />"
                                                                                 class="form-control" name="phone_number" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.shipping_address"/></label> <input type="text" value="<c:out value='${order.address}' />"
                                                                           class="form-control" name="address">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.city"/></label> <input type="text" value="<c:out value='${order.city}' />"
                                                                                   class="form-control" name="city">
                </fieldset>


                <fieldset class="form-group">
                    <label><fmt:message key="label.zip"/></label> <input type="text" value="<c:out value='${order.zip}' />"
                                                                              class="form-control" name="zip">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.total"/></label> <input type="text" value="<c:out value='${order.totalCost}' />"
                                                                          class="form-control" name="total_cost">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.date"/></label> <input type="text" value="<c:out value='${order.orderDateTime}' />"
                                                                          class="form-control" name="date">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.status"/></label> <input type="text" value="<c:out value='${order.status}' />"
                                                                          class="form-control" name="status">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.details"/></label> <input type="text" value="<c:out value='${order.details}' />"
                                                                          class="form-control" name="details">
                </fieldset>

                <button type="submit" class="btn btn-success"><fmt:message key="label.save"/></button>
            </form>
        </div>
    </div>
</div>
</body>

</html>