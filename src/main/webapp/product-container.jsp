<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--div title-product contain title--%>
<div class="title-product">CHOOSE FOR YOUR SAVOUR ٩(^‿^)۶</div>

<%--div form-product contain all product--%>
<div class="form-product">
    <div class="row">

        <%--If this page receive attribute notfound, alert will show--%>
        <c:if test="${notfound}">
            <script>alert("Cannot found products you search!")</script>
        </c:if>

        <%--Alert show later, set notfound to false--%>
        <c:set var="notfound" value="${false}" scope="session"/>

        <%--Get each object from product list--%>
        <c:forEach items="${products}" var="product">

            <%--Each product by design 1 div--%>
            <div class="col-xl-3 col-lg-4 col-md-6 mb-4">
                <div class="bg-white rounded shadow-sm">

                    <%--Form contain information, click will sent request to server--%>
                    <form action="controller" method="get">

                        <%--Image--%>
                        <input type="image" src="${product.productIMGURL}" alt="Submit" class="img-fluid card-img-top">

                        <%--Name and cost--%>
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