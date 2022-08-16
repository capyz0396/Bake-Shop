<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<html>
<head>
    <title>Bake Shop Building</title>
    <link rel="icon" href="https://png.pngtree.com/png-vector/20191029/ourmid/pngtree-bakery-icon-for-your-project-png-image_1897748.jpg">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>

<%--Get account from session and check it. If first time login, welcome alert is show--%>
<c:set var="account" value='${sessionScope["account"]}' />
<c:if test="${account.username != admin && account.time == 1}">
    <script>
        alert('Welcome to home, ${account.username}')
    </script>
</c:if>

<%--div header-blue contain all design form--%>
<div class="header-blue">

    <%--Slit content to 3 file, include it by this code--%>
    <jsp:include page="index-header.jsp"/>
    <jsp:include page="index-container.jsp"/>
    <jsp:include page="index-footer.jsp"/>
</div>
</body>
</html>