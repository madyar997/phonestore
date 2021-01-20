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
    <style><%@include file="css/style_home.css" %></style>
    <title>PhoneStore.kz</title>
</head>
<body>


<jsp:include page="header.jsp"/>
<nav class="sidebar">
    <div class="widget">
        <h3 class="widget-title"><fmt:message key="label.brand" /></h3>
        <ul class="widget-list">
            <c:forEach items="${brands}" var="brandName">
                <a href="/phonestore/brand?brandId=${brandName.brandId}">${brandName.brandName}</a>
            </c:forEach>
        </ul>
    </div>
</nav>
    <h2 class="popular-smartphones">
        <fmt:message key="label.brand.featured" />
    </h2>
<section class="products-grid" style="margin-left:300px">
    <c:forEach items="${phones}" var="phone">
        <div class="card">
            <img src="${phone.picture}" alt="phone photo" style="width:260px;height: 260px" >
            <a href="/phonestore/view?id=${phone.id}"><h1 class="phone-brand-model">${phone.brand} ${phone.model}</h1></a>
            <p class="price">${phone.price} KZT</p>
            <p class="memory">${phone.characteristics["memory"]}</p>
            <p class="color">${phone.color}</p>
            <a href="/phonestore/cart/add?id=${phone.id}" class="button"><fmt:message key="label.add-to-card" /></a>
        </div>
    </c:forEach>
</section>

</section>
<footer>
    <div class="footer-bg">
        <div class="copyright">
            <div style="text-align: center;">
                <p><strong><fmt:message key="label.footer-text" /></strong></p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>