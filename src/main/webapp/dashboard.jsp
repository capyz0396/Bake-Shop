<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboad</title>
    <link rel="icon" href="https://png.pngtree.com/png-vector/20191029/ourmid/pngtree-bakery-icon-for-your-project-png-image_1897748.jpg">
    <link rel="stylesheet" href="css/chartist.min.css">
    <link rel="stylesheet" href="css/style.min.css">
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>

<%--Get bean từ session để đối chiếu--%>
<jsp:useBean id="account" class="com.example.test.Account" scope="session"/>

<%--Nếu đây là trang đầu tiên được tài khoản administrator truy cập thì hiện thông báo welcome--%>
<% if (account.isLogged() && account.getUsername().matches("administrator") && account.getTime() == 1) { %>
<script>
    alert('Welcome to home, admin');
</script>
<% }

/*Ngược lại điều hướng đến trang chủ*/
else if (!account.isLogged() || !account.getUsername().matches("administrator")){ %>
    <% response.sendRedirect("./");
} %>

<%--div main-wrapper chứa toàn bộ nội dung trang--%>
<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full" data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">

    <%--Nội dung trang được phân nhỏ thành 3 file jsp như dưới đây--%>
    <jsp:include page="dashboard-header.jsp"/>
    <jsp:include page="dashboard-aside.jsp"/>
    <jsp:include page="dashboard-page-wrapper.jsp"/>
</div>
</body>
</html>