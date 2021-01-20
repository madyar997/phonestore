<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<jsp:include page="header.jsp"/>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="/phonestore/admin/user-management/create" method="post">

                <caption>
                    <h2><fmt:message key="label.add_user"/></h2>
                </caption>

                <fieldset class="form-group">
                    <label><fmt:message key="label.first_name"/></label> <input type="text"
                                                     class="form-control" name="first_name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.second_name"/></label> <input type="text"
                                                      class="form-control" name="second_name" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.email"/></label> <input type="email"
                                                class="form-control" name="email">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.phone_number"/></label> <input type="tel"
                                                       class="form-control" name="phone_number">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.role"/></label> <input type="text"
                                               class="form-control" name="role">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.address"/></label> <input type="text"
                                                  class="form-control" name="address">
                </fieldset>

                <fieldset class="form-group">
                    <label><fmt:message key="label.password"/></label> <input type="password"
                                                   class="form-control" name="password">
                </fieldset>
                <fieldset class="form-group">
                    <label><fmt:message key="label.confirm_password"/></label> <input type="password"
                                                   class="form-control" name="confirm_password">
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>