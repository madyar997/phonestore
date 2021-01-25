<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>Error page </title>
    <style><%@include file="css/style_error.css" %></style>
</head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>404</h1>
        </div>
        <h2><fmt:message key="label.page_not_found"/></h2>
        <p><fmt:message key="label.page_not_found_reason"/></p>
        <a href="/PHONESTORE"><fmt:message key="label.go_to_homepage"/></a>
    </div>
</div>
</body>
</html>
