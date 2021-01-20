<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html lang="${sessionScope.lang}">
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3 align="center" style="padding-top: 50px"><fmt:message key="label.logout_success" /></h3>
</body>
</html>
