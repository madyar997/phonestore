<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tutorial</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <style>
        <%@include file="css/view.css" %>
    </style>
    <meta name="robots" content="noindex,follow" />
</head>

<body>
<jsp:include page="header.jsp"/>
<main class="container">
    <div class="left-column">
        <img data-image="red" class="active" src="${phone.picture}" alt="" >
    </div>


    <!-- Right Column -->
    <div class="right-column">

        <!-- Product Description -->
        <div class="product-description">
            <span>${phone.brand}</span>
            <h1>${phone.model}</h1>
            <p>${phone.description}</p>
        </div>

        <!-- Product Configuration -->
        <div class="product-configuration">

            <!-- Product Color -->
            <div class="product-color">
                <span><fmt:message key="label.color"/>: ${phone.color}</span>

            </div>

            <!-- Model-year Configuration -->
            <div class="model-year">
                <span><fmt:message key="label.model_year"/>: ${phone.modelYear}</span>
            </div>
        </div>

        <div class="product_characteristics">
            <span><fmt:message key="label.characteristics"/></span>
            <table width="100%" cellspacing="0" cellpadding="5" >
                <c:forEach var="characteristic" items="${phone.characteristics}">
                    <tr>
                        <td width="200" valign="top">${characteristic.key}</td>
                        <td valign="top">${characteristic.value}</td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <div class="product-price">
            <span>${phone.price} KZT</span>
            <a href="/phonestore/cart/add?id=${phone.id}" class="cart-btn"><fmt:message key="label.add-to-card"/></a>
        </div>
    </div>
</main>
</body>
</html>
