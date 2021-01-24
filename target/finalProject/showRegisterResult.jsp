<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${result eq 'User with this email is already registered'}">
    <h3 align="center" style="padding-top: 50px"><fmt:message key="label.already_registered"/></h3>
</c:if>
<c:if test="${result eq 'Passwords do not match'}">
    <h3 align="center" style="padding-top: 50px"><fmt:message key="label.passwords_do_not_match"/></h3>
</c:if>
<c:if test="${result eq 'Check if you filled all the fields'}">
    <h3 align="center" style="padding-top: 50px"><fmt:message key="label.some_fields_are_null"/></h3>
</c:if>
<c:if test="${result eq 'registered successfully'}">
    <h3 align="center" style="padding-top: 50px"><fmt:message key="label.registered_successfully"/></h3>
</c:if>



</body>
</html>
