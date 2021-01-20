<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style><%@include file="css/style-product-management.css" %></style>
    <title>PhoneStore.kz</title>
</head>
<body>


<jsp:include page="header.jsp"/>

<h2 class="popular-smartphones">
    <a href="/phonestore/admin/product-management/add-form" class="button"><fmt:message key="label.add_item"/></a>
</h2>
<section class="products-grid">
    <c:forEach items="${phones}" var="phone">
        <div class="card">
            <img src="${phone.picture}" alt="phone photo" style="width:100%">
            <a href="/phonestore/view?id=${phone.id}"><h1 class="phone-brand-model">${phone.brand} ${phone.model}</h1></a>
            <p class="price">${phone.price}</p>
            <p class="memory">${phone.characteristics["memory"]}</p>
            <p class="color">${phone.color}</p>
            <a href="/phonestore/admin/product-management/edit-form?id=${phone.id}" class="button"><fmt:message key="label.edit"/></a>
            <a href="/phonestore/admin/product-management/delete?id=${phone.id}" class="button"><fmt:message key="label.delete"/></a>
        </div>
    </c:forEach>
</section>

</body>
</html>