<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--div content-container contain all product detail information--%>
<div class="content-container">

    <%--Title--%>
    <div class="title">${productDetail.productName}"</div>

    <%--Image detail--%>
    <div class="product-img">
        <img class="img-detail" src="${sessionScope.productDetail.productIMGURL}">
    </div>

    <%--Product information--%>
    <div class="product-detail">

        <%--Product name, cost, detail--%>
        _ Tên sản phẩm: <b><c:out value="${productDetail.productName}"/></b><br>
        _ Giá: <b><c:out value="${productDetail.productPrice}"/> đồng </b><br>
        _ Thông tin chi tiết: <i><c:out value="${productDetail.productDetail}"/></i><br>

        <%--Use form sent request to server--%>
        <form class="form-detail" action="controller">

            <%--Product ID--%>
            <input style="display: none" name="selected" value="${sessionScope.productDetail.productID}"/>

            <%--Product quantity--%>
            Số lượng:
            <label style="margin-bottom: 15px">
                <select name="droplist">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
            </label><br>

            <%--When click button, sent all request to server--%>
            <input name="action" value="cart" type="hidden">
            <input class="button-add-to-cart" type="submit" value="Add to card">
        </form>
    </div>
</div>