<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--div title-product contain title page--%>
<div class="title-product">PRODUCTS IN CART</div>

<%--div content-container contain products (get from original product list)--%>
<div class="content-container">

    <%--Set 1 totalNum to int and use it calculate total cost--%>
    <fmt:parseNumber var="totalNum" integerOnly="true" type="number" value="0" scope="page"/>

   <%--Use foreach get object in list and get information--%>
    <c:forEach var="i" items="${originalProducts}">

        <%--If product have quantity more than 0, handle that product--%>
        <c:if test="${i.productQuantity > 0}">

            <%--div product contain all information of product--%>
            <div class="product">

                <%--div image contain image--%>
                <div class="image">
                    <img src="${i.productIMGURL}" class="image-detail">
                </div>

                <%--div product-name contain product name--%>
                <div class="product-name">
                        ${i.productName} x ${i.productQuantity}
                </div>

                <%--div product-price contain cost by quantity product--%>
                <div class="product-price">
                    <fmt:parseNumber var="cost" integerOnly="true" type="number"
                                     value="${i.productQuantity * i.productPrice}" scope="page"/>
                        ${cost} đồng
                    <fmt:parseNumber var="totalNum" integerOnly="true" type="number" value="${totalNum + cost}"
                                     scope="page"/>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<%--div product contain information of cart--%>
<div class="product">

    <%--div product-total contain label--%>
    <div class="product-total">Total cart:</div>

    <%--div product-price-total contain total cost of cart--%>
    <div class="product-price-total">${totalNum} đồng</div>

    <%--div product-order contain button, when click, it will show alert order success--%>
    <div class="product-order">
        <a href="controller?action=order" onclick="alert('Order is received!')">Order</a>
    </div>
</div>