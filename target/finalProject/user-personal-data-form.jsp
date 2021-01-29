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
            <form action="/phonestore/user/edit/personal-data" method="post">
                <caption>
                    <h2><fmt:message key="label.edit"/></h2>
                </caption>
                <input type="hidden" name="id" value="<c:out value='${user.userId}' />"/>

                <fieldset class="form-group">
                    <label><fmt:message key="label.first_name"/></label> <input type="text" value="<c:out value='${user.firstName}' />"
                                                     class="form-control" name="first_name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.second_name"/></label> <input type="text" value="<c:out value='${user.secondName}' />"
                                                      class="form-control" name="second_name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.email"/></label> <input type="email" value="<c:out value='${user.email}' />"
                                                class="form-control" name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.phone_number"/></label> <input type="text" value="<c:out value='${user.phoneNumber}' />"
                                  pattern="^((\+7|7|8)+([0-9]){10})$" class="form-control" name="phone_number">
                </fieldset>


                <fieldset class="form-group">
                    <label><fmt:message key="label.address"/></label> <input type="text" value="<c:out value='${user.address}' />"
                                                  class="form-control" name="address">
                </fieldset>

                <button type="submit" class="btn btn-success"><fmt:message key="label.save"/></button>
            </form>
        </div>
    </div>
</div>
</body>

</html>