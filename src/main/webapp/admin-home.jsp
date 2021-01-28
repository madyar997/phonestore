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
        <%@include file="css/style_admin_home.css" %>
    </style>
    <title>PhoneStore.kz</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="container">
    <li><a href="/phonestore/admin/product-management"><fmt:message key="label.product_management"/></a></li>
    <li style="padding-top: 20px"><a href="/phonestore/admin/user-management"><fmt:message key="label.user_management"/></a></li>
    <li style="padding-top: 20px"><a href="/phonestore/admin/order-management"><fmt:message key="label.order_management"/></a></li>
</div>
</body>
</html>