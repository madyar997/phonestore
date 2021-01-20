<%@ page import="com.epam.tcfp.phonestore.constants.Constants" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<c:set var="Admin" value="<%=Constants.ID_ADMIN%>" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<!------ Include the above in your HEAD tag ---------->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/phonestore">PHONESTORE.KZ</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <li class="nav-item m-auto ${pageContext.request.requestURI eq '/home.jsp' ? ' active' : ''}" >
                    <a class="nav-link" href="/phonestore"><fmt:message key="label.home"/></a>
                </li>
                <li class="nav-item ${pageContext.request.requestURI eq '/contact.jsp' ? ' active' : ''}">
                    <a class="nav-link" href="/contacts.jsp"><fmt:message key="label.contacts"/></a>
                </li>
                <li class="nav-item ${pageContext.request.requestURI eq '/cart.jsp' ? ' active' : ''}">
                    <a class="nav-link" href="/phonestore/cart"><fmt:message key="label.cart"/> <span class="sr-only">(current)</span></a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user ==null}">
                        <li class="nav-item ${pageContext.request.requestURI eq '/login.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/login/form"><fmt:message key="label.login"/></a>
                        </li>
                        <li class="nav-item ${pageContext.request.requestURI eq '/register.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/register/form"><fmt:message key="label.register"/></a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.user.role == Admin}">
                        <li class="nav-item ${pageContext.request.requestURI eq '/admin-home.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/admin/home"><fmt:message key="label.admin.home"/></a>
                        </li>
                        <li class="nav-item ${pageContext.request.requestURI eq '/logout.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/logout"><fmt:message key="label.logout"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item ${pageContext.request.requestURI eq '/user-home.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/user/home"><fmt:message key="label.user.account"/></a>
                        </li>
                        <li class="nav-item ${pageContext.request.requestURI eq '/logout.jsp' ? ' active' : ''}">
                            <a class="nav-link" href="/phonestore/logout"><fmt:message key="label.logout"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                        <input type="search" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"
                               placeholder="<fmt:message key="label.search_model"/>..." name="search" >
                        <div class="input-group-append">
                            <button type="submit" class="btn btn-secondary btn-number">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>

                </div>
                <a class="btn btn-success btn-sm ml-3" href="/phonestore/cart">
                    <i class="fa fa-shopping-cart"></i> <fmt:message key="label.cart"/>
                    <c:choose>
                        <c:when test="${sessionScope.cart.totalQuantity > 0}">
                            <span class="badge badge-light">${cart.totalQuantity}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-light"></span>
                        </c:otherwise>
                    </c:choose>

                </a>
            </form>
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="?sessionLocale=ru">Рус</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="?sessionLocale=en">En</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>