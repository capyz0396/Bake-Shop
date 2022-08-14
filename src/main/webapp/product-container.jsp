<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--div title-product chứa lời chào chọn sản phẩm--%>
<div class="title-product">CHOOSE FOR YOUR SAVOUR ٩(^‿^)۶</div>

<%--div form-product chứa toàn bộ sản phẩm--%>
<div class="form-product">
    <div class="row">

        <%--Nếu jsp này nhận được attribute notfound thì báo lỗi--%>
        <c:if test="${notfound}">
            <script>alert("Cannot found products you search!")</script>
        </c:if>

        <%--Sau khi báo lỗi hoặc không báo thì đều set mới bằng false--%>
        <c:set var="notfound" value="${false}" scope="session"/>

        <%--Sử dụng vòng lặp foreach để duyệt từng sản phẩm--%>
        <c:forEach items="${products}" var="product">

            <%--Mỗi sản phẩm đều được tạo 1 div riêng chứa hình ảnh và giá--%>
            <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                <div class="bg-white rounded shadow-sm">

                    <%--Mỗi sản phẩm đều có form riêng để khi người dùng ấn sẽ gửi lựa chọn đến server điều hướng--%>
                    <form action="controller" method="get">

                        <%--Hình ảnh--%>
                        <input type="image" src="${product.productIMGURL}" alt="Submit" class="img-fluid card-img-top">

                        <%--Tên và giá--%>
                        <div class="p-4">
                            <input name="action" value="detail" type="hidden">
                            <input style="display: none" name="product" value="${product.productID}">
                            <h5>${product.productName}</h5>
                            <p class="small text-muted mb-0">Giá: ${product.productPrice}đ</p>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>