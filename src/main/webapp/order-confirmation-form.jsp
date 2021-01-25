<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="css/style-order-confirmation-form.css" %>
    </style>
</head>
<body>
<aside>
    <jsp:include page="header.jsp"/>
</aside>


<div class="row">
    <div class="col-75">
        <div class="container1">
            <form action="/phonestore/order-confirmation" method="post">

                <div class="row">
                    <div class="col-50">
                        <h3><fmt:message key="label.payment_details"/></h3>
                        <label for="fname"><i class="fa fa-user"></i><fmt:message key="label.full_name"/></label>
                        <input type="text" id="fname" name="full_name" value="${sessionScope.user.firstName} ${sessionScope.user.secondName}">

                        <label for="email"><i class="fa fa-envelope"></i><fmt:message key="label.email"/></label>
                        <input type="text" id="email" name="phone_number" value="${sessionScope.user.phoneNumber}">

                        <label for="adr"><i class="fa fa-address-card-o"></i> <fmt:message key="label.address"/></label>
                        <input type="text" id="adr" name="address" value="${sessionScope.user.address}" required>


                        <div class="row">
                            <div class="col-50">
                                <label for="city"><i class="fa fa-institution"></i> <fmt:message key="label.city"/></label>
                                <input type="text" id="city" name="city" required>
                            </div>

                            <div class="col-50">
                                <label for="zip"><fmt:message key="label.zip"/></label>
                                <input type="text" id="zip" name="zip"  required pattern="^\d{6}$">
                            </div>
                        </div>
                    </div>

                    <div class="col-50">
                        <h3><fmt:message key="label.payment"/></h3>
                        <label for="fname"><fmt:message key="label.accepting_carts"/></label>
                        <div class="icon-container">
                            <i class="fa fa-cc-visa" style="color:navy;"></i>
                            <i class="fa fa-cc-amex" style="color:blue;"></i>
                            <i class="fa fa-cc-mastercard" style="color:red;"></i>
                            <i class="fa fa-cc-discover" style="color:orange;"></i>
                        </div>
                        <label for="cname"><fmt:message key="label.card_name"/></label>
                        <input type="text" id="cname" name="card_name" required>

                        <label for="ccnum"><fmt:message key="label.card_number"/></label>
                        <input type="text" id="ccnum" name="card_number" required pattern="^(?:(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((?:2131|1800|35[0-9]{3})[0-9]{11}))$">

                        <div class="row">
                            <div class="col-50">
                                <label for="expyear"><fmt:message key="label.exp"/></label>
                                <input type="text" id="expyear" name="exp" required pattern="^(0[1-9]|1[0-2])\/\d{2}$">
                            </div>

                            <div class="col-50">
                                <label for="cvv">CVV</label>
                                <input type="text" id="cvv" name="cvv" required pattern="^[0-9]{3,4}$">
                            </div>
                        </div>
                    </div>

                </div>

                <input type="submit" value="<fmt:message key="label.confirm"/>" class="btn1">
            </form>
        </div>
    </div>

    <div class="col-25">
        <div class="container">
            <h4><fmt:message key="label.cart"/>
                <span class="price" style="color:black">
          <i class="fa fa-shopping-cart"></i>
          <b>${sessionScope.cart.totalQuantity}</b>
        </span>
            </h4>
            <c:forEach var="phone" items="${sessionScope.cart.productList}">
                <p><a href="#"></a>${phone.value} x ${phone.key.brand} ${phone.key.model} ${phone.key.color} ${phone.key.characteristics["memory"]  }  <span
                        class="price"> ${phone.key.price} KZT</span></p>
            </c:forEach>
            <hr>
            <p><fmt:message key="label.total"/> <span class="price" style="color:black"><b>${sessionScope.cart.totalCost}</b></span></p>
        </div>
    </div>
</div>
</body>
</html>
