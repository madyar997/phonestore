<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session" />
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <title>PhoneStore.kz</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"> </th>
                        <th scope="col"><fmt:message key="label.product"/></th>
                        <th scope="col"><fmt:message key="label.available"/></th>
                        <th scope="col"><fmt:message key="label.quantity"/></th>
                        <th scope="col"><fmt:message key="label.price"/></th>
                        <th> </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart.productList}" var="phone">
                        <tr>
                            <td><img src="${phone.key.picture}" style="width: 50px;height: 50px" /> </td>
                            <td>${phone.key.brand} ${phone.key.model} ${phone.key.color} ${phone.key.characteristics["memory"]}</td>
                            <c:choose>
                                <c:when test="${phone.key.quantity > '0'}">
                                    <td><fmt:message key="label.in_stock"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td><fmt:message key="label.out_od_order"/></td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                        <button type="button" data-quantity="minus" data-field="quantity"
                                                onclick="location.href = '/phonestore/cart/change-quantity?id=${phone.key.id}&action=minus'"
                                                style="width: 30px;height: 30px;background-color: #E1E8EE;border-radius: 6px;border: none;cursor: pointer;">
                                            <i class="fa fa-minus" aria-hidden="true"></i>
                                        </button>
                                            ${phone.value}
                                        <button type="button" data-quantity="plus" data-field="quantity"
                                                onclick="location.href = '/phonestore/cart/change-quantity?id=${phone.key.id}&action=plus'"
                                                style="width: 30px;height: 30px;background-color: #E1E8EE;border-radius: 6px;border: none;cursor: pointer;">
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                        </button>
                            </td>


                            <td>${phone.key.price * phone.value} KZT</td>
                            <td class="text-right"><button onclick="location.href = '/phonestore/cart/delete?id=${phone.key.id}'" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </td>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><fmt:message key="label.shipping"/></td>
                        <td class="text-right">${cart.shipping} KZT</td>
                    </tr>

                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><strong><fmt:message key="label.total"/></strong></td>
                        <td class="text-right"><strong>${cart.totalCost} KZT</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-6">
                    <button onclick="location.href = '/phonestore'" class="btn btn-block btn-light"><fmt:message key="label.continue_shopping"/></button>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button onclick="location.href = '/phonestore/checkout'" class="btn btn-lg btn-block btn-success text-uppercase"><fmt:message key="label.checkout"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
