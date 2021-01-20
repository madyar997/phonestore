<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="resources" scope="session"/>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<br>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="/phonestore/admin/product-management/edit?id=${phone.id}" method="post">
                <caption>
                    <h2><fmt:message key="label.edit"/></h2>
                </caption>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.brand"/></label>
                        <select name="brand">
                            <c:forEach items="${brands}" var="brandName">
                                <option><p>${brandName.brandName}</p></option>
                            </c:forEach>
                        </select>
                    </fieldset>
                <fieldset class="form-group">
                    <label><fmt:message key="label.model"/></label> <input type="text" value="<c:out value='${phone.model}' />"
                                               class="form-control" name="model" required="required">
                </fieldset>
                    <fieldset class="form-group">
                        <label><fmt:message key="label.availability"/></label> <input type="number" value="<c:out value='${phone.quantity}' />"
                                                    class="form-control" name="quantity" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.color"/></label> <input type="text" value="<c:out value='${phone.color}' />"
                                                    class="form-control" name="color" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.model_year"/></label> <input type="number" value="<c:out value='${phone.modelYear}' />"
                                                         class="form-control" name="model_year">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.price"/></label> <input type="number" value="<c:out value='${phone.price}' />"
                                                    class="form-control" name="price">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.description"/></label> <input type="text" value="<c:out value='${phone.description}' />"
                                                          class="form-control" name="phone_description">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.picture_path"/></label> <input type="text" value="<c:out value='${phone.picture}' />"
                                                           class="form-control" name="picture_path">
                    </fieldset>


                    <fieldset class="form-group">
                        <label><fmt:message key="label.memory"/></label> <input type="text" value="<c:out value='${characteristics.memory}' />"
                                                     class="form-control" name="memory" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.display"/></label> <input type="text" value="<c:out value='${characteristics.display}' />"
                                                      class="form-control" name="display" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.screen_size"/></label> <input type="text"
                                                          value="<c:out value='${characteristics.screenSize}' />"
                                                          class="form-control" name="screen_size">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.camera"/></label> <input type="text" value="<c:out value='${characteristics.camera}' />"
                                                     class="form-control" name="camera">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.front_camera"/></label> <input type="text"
                                                           value="<c:out value='${characteristics.frontCamera}' />"
                                                           class="form-control" name="front_camera">
                    </fieldset>

                    <fieldset class="form-group">
                        <label><fmt:message key="label.ram"/></label> <input type="text" value="<c:out value='${characteristics.ram}' />"
                                                  class="form-control" name="ram">
                    </fieldset>
                    <fieldset class="form-group">
                        <label><fmt:message key="label.processor"/></label> <input type="text" value="<c:out value='${characteristics.processor}' />"
                                                        class="form-control" name="processor">
                    </fieldset>
                    <fieldset class="form-group">
                        <label><fmt:message key="label.battery"/></label> <input type="text" value="<c:out value='${characteristics.battery}' />"
                                                      class="form-control" name="battery">
                    </fieldset>
                    <fieldset class="form-group">
                        <label><fmt:message key="label.sizes"/></label> <input type="text" value="<c:out value='${characteristics.sizes}' />"
                                                    class="form-control" name="sizes">
                    </fieldset>
                    <fieldset class="form-group">
                        <label><fmt:message key="label.weight"/></label> <input type="text" value="<c:out value='${characteristics.weight}' />"
                                                     class="form-control" name="weight">
                    </fieldset>
                <button type="submit" class="btn btn-success"><fmt:message key="label.edit"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
