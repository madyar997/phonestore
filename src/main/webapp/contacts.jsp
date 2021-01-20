<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>COntacts</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<p style="padding-top: 50px; align-content: center;text-align: center"><fmt:message key="label.contact_us"/>: 87073929045</p>
<p style="align-content: center;text-align: center"><fmt:message key="label.email"/>: madiar.997@gmail.com</p>
<p style="align-content: center;text-align: center"><fmt:message key="label.location"/></p>
</body>
</html>
