<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<p style="text-align: center;font-weight: bold;padding-top: 50px"><fmt:message key="label.order_confirm_line1"/></p>
<p style="text-align: center;"><fmt:message key="label.order_confirm_line2"/> ${orderId}</p>
<p style="text-align: center;"> <fmt:message key="label.order_confirm_line3"/> <a href="/phonestore/user/home"><fmt:message key="label.order_confirm_line4"/></a>.</p>
</body>
</html>
