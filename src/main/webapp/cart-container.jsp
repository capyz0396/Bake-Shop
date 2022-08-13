<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--div title-product chứa tiêu đề trang--%>
<div class="title-product">PRODUCTS IN CART</div>

<%--div content-container chứa sản phẩm được add--%>
<div class="content-container">

    <%--Set 1 biến totalNum để lưu trữ và tính tổng giá tiền--%>
    <fmt:parseNumber var="totalNum" integerOnly="true" type="number" value="0" scope="page"/>

   <%--Sử dụng vòng lặp để duyệt toàn bộ list--%>
    <c:forEach var="i" items="${originalProducts}">

        <%--Nếu sản phẩm được add có giá trị hơn 0 thì hiển thị--%>
        <c:if test="${i.productQuantity > 0}">

            <%--div product chứa toàn bộ thông tin hình ảnh, tổng giá sản phẩm--%>
            <div class="product">

                <%--div image chứa hình ảnh sản phẩm--%>
                <div class="image">
                    <img src="${i.productIMGURL}" class="image-detail">
                </div>

                <%--div product-name chứa tên sản phẩm--%>
                <div class="product-name">
                        ${i.productName} x ${i.productQuantity}
                </div>

                <%--div product-price chứa tổng giá theo sản phẩm--%>
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

<%--div product chứa thông tin tổng giá giỏ hàng--%>
<div class="product">

    <%--div product-total chứa nhãn--%>
    <div class="product-total">Total cart:</div>

    <%--div product-price-total chứa giá thành--%>
    <div class="product-price-total">${totalNum} đồng</div>

    <%--div product-order chứa button (khi nhấn có thông báo hiển thị)--%>
    <div class="product-order">
        <a href="controller?action=order" onclick="alert('Order is received!')">Order</a>
    </div>
</div>