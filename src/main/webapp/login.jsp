<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <title><fmt:message key="label.login"/></title>
    <style>
        <%@include file="css/style_login.css" %>
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div class="login_form">
        <div class="title"><fmt:message key="label.login"/></div>
        <form action="/phonestore/login" class="form" method="post" accept-charset="UTF-8">
            <div class="form_wrap">
                <div class="input_wrap">
                    <input type="email" name="email" placeholder="<fmt:message key="label.email"/>">
                </div>
                <div class="input_wrap">
                    <input type="password" name="password" placeholder="<fmt:message key="label.login_password"/>">
                </div>
                <div class="input_wrap">
                    <input type="submit" value="<fmt:message key="label.login"/>" class="submit_btn">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>