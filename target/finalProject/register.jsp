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

                    <div class="input_wrap">
                        <input type="text" name="first_name" placeholder="<fmt:message key="label.first_name"/>" required>
                    </div>
                    <div class="input_wrap">
                        <input type="text" name="second_name" placeholder="<fmt:message key="label.second_name"/>" required>
                    </div>

                <div class="input_wrap">
                    <input type="email" name="email" placeholder="<fmt:message key="label.email"/>" required>
                </div>

                <div class="input_wrap">
                    <input type="tel" name="phone_number" min="10" max="11"  placeholder="<fmt:message key="label.phone_number"/>" required>
                </div>

                <div class="input_wrap">
                    <input type="text" name="address" placeholder="<fmt:message key="label.address"/>" required>
                </div>

                <div class="input_wrap">
                    <input type="password" class="password" name="password" placeholder="<fmt:message key="label.password"/>" required>
                </div>

                <div class="input_wrap">
                    <input type="password" class="confirm_password" name="confirm_password" placeholder="<fmt:message key="label.confirm_password"/>" required>
                </div>
                <div class="error" style="height: 50px"></div>
                <div class="input_wrap">
                    <input type="submit" value="<fmt:message key="label.register"/>" class="submit_btn" id="submit">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>