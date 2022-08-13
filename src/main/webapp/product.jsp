<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bake Shop Building</title>
    <link rel="icon" href="https://png.pngtree.com/png-vector/20191029/ourmid/pngtree-bakery-icon-for-your-project-png-image_1897748.jpg">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>

<%--div header-blue chứa toàn bộ nội dung trang--%>
<div class="header-blue">

    <%--Nội dung trang được phân nhỏ thành 3 file jsp như dưới đây--%>
    <jsp:include page="index-header.jsp"/>
    <jsp:include page="product-container.jsp"/>
    <jsp:include page="index-footer.jsp"/>
</div>
</body>
</html>