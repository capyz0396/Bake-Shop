<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--div content-container chứa toàn bộ nội dung chi tiết sản phẩm--%>
<div class="content-container">

    <%--Tiêu đề--%>
    <div class="title">${productDetail.productName}"</div>

    <%--Hình mô tả chi tiết--%>
    <div class="product-img">
        <img class="img-detail" src="${sessionScope.productDetail.productIMGURL}">
    </div>

    <%--Thông tin sản phẩm--%>
    <div class="product-detail">

        <%--Các nội dung được nằm ở đây--%>
        _ Tên sản phẩm: <b><c:out value="${productDetail.productName}"/></b><br>
        _ Giá: <b><c:out value="${productDetail.productPrice}"/> đồng </b><br>
        _ Thông tin chi tiết: <i><c:out value="${productDetail.productDetail}"/></i><br>

        <%--Thẻ form để gửi lựa chọn của khách hàng đến server--%>
        <form class="form-detail" action="controller">

            <%--ID của sản phẩm--%>
            <input style="display: none" name="selected" value="${sessionScope.productDetail.productID}"/>

            <%--Số lượng sản phẩm--%>
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

            <%--Button submit để tiến hành gửi form--%>
            <input name="action" value="cart" type="hidden">
            <input class="button-add-to-cart" type="submit" value="Add to card">
        </form>
    </div>
</div>