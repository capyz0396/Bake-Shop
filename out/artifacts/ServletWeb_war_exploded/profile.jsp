<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="icon" href="https://png.pngtree.com/png-vector/20191029/ourmid/pngtree-bakery-icon-for-your-project-png-image_1897748.jpg">
    <link rel="stylesheet" href="css/chartist.min.css">
    <link rel="stylesheet" href="css/style.min.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

<%--Get bean from session--%>
<jsp:useBean id="account" class="com.example.test.model.Account" scope="session"/>

<%--If user account isn't admin, redirect to index.jsp--%>
<% if (!account.isLogged() || !account.getUsername().matches("administrator")) {
response.sendRedirect("./");
} %>

<%--div main-wrapper contain all design form--%>
<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full" data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">

    <%--Slit content to 3 file, include it by this code--%>
    <jsp:include page="dashboard-header.jsp"/>
    <jsp:include page="dashboard-aside.jsp"/>
    <jsp:include page="profile-page-wrapper.jsp"/>
</div>
</body>
</html>