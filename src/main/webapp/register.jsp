<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <style>
        <%@include file="css/style_register.css" %>
    </style>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="/js/register-ajax.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div class="registration_form">
        <div class="title"><fmt:message key="label.register"/></div>

        <form action="/phonestore/register" class="form" method="post" accept-charset="UTF-8">
            <div class="form_wrap">
                <label for="first_name"><fmt:message key="label.first_name"/>: </label>
                <div class="input_wrap">
                    <input type="text" id="first_name" name="first_name"  required>
                </div>

                <label for="second_name"><fmt:message key="label.second_name"/>: </label>
                <div class="input_wrap">
                    <input type="text" id="second_name" name="second_name"  required>
                </div>

                <label for="email"><fmt:message key="label.email"/>: (example@example.com)</label>
                <div class="input_wrap">
                    <input type="email" id="email" name="email" required>
                </div>


                <label for="phone_number"><fmt:message key="label.phone_number"/> (+7XXXXXXXXXX): </label>
                <div class="input_wrap">
                    <input type="text" id="phone_number"  name="phone_number"
                         pattern="^((\+7|7|8)+([0-9]){10})$"  required>
                </div>


                <label for="address"><fmt:message key="label.address"/>: </label>
                <div class="input_wrap">
                    <input type="text" id="address" name="address" required>
                </div>

                <label for="password"><fmt:message key="label.password"/>:</label>
                <div class="input_wrap">
                    <input type="password" class="password" id="password" name="password" pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$"
                           onkeyup="check()" required>
                </div>


                <label for="confirm_password"><fmt:message key="label.confirm_password"/>:</label>
                <div class="input_wrap">
                    <input type="password" class="confirm_password" id="confirm_password" name="confirm_password"
                           pattern="^(?=.*\d)(?=.*[a-zA-Z]).{8,}$" onkeyup="check()" required>
                </div>
                <div class="error" style="height: 50px">
                    <span id="error"></span>
                </div>
                <div class="input_wrap">
                    <input type="submit" value="<fmt:message key="label.register"/>" class="submit_btn" id="submit">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>