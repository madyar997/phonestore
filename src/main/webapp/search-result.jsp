<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <title>Search result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style><%@include file="css/style_home.css" %></style>
</head>
<body>


<jsp:include page="header.jsp"/>
<nav class="sidebar">
    <div class="widget">
        <h3 class="widget-title"><fmt:message key="label.brand"/></h3>
        <ul class="widget-list">
            <c:forEach items="${brands}" var="brandName">
                <a href="/phonestore/brand?brandId=${brandName.brandId}">${brandName.brandName}</a>
            </c:forEach>
        </ul>
    </div>
</nav>
<h2 class="popular-smartphones">
    <fmt:message key="label.search_result"/>
</h2>
<c:if test="${empty phones}">
    <h2 class="popular-smartphones">
        <fmt:message key="label.search_result_is_null"/>
    </h2>
</c:if>
<c:if test="${not empty phones}">
    <section class="products-grid" style="margin-left:300px">
        <c:forEach items="${phones}" var="phone">
            <div class="card">
                <img src="${phone.picture}" alt="phone photo" style="width:260px;height: 260px">
                <a href="/phonestore/view?id=${phone.id}"><h1
                        class="phone-brand-model">${phone.brand} ${phone.model}</h1></a>
                <p class="price">${phone.price} KZT</p>
                <p class="memory">${phone.characteristics["memory"]}</p>
                <p class="color">${phone.color}</p>
                <a href="/phonestore/cart/add?id=${phone.id}" name="${phone.id}" class="button"><fmt:message
                        key="label.add-to-card"/></a>
            </div>
        </c:forEach>
    </section>
</c:if>


</body>
</html>
