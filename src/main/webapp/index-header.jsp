<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="account" class="com.example.test.Account" scope="session"/>

<%--Header sẽ được đặt trong thẻ nav vì có dạng menu--%>
<nav class="navbar navbar-light navbar-expand-md navigation-clean-search">

    <div class="container-fluid"><a class="navbar-brand" href="./">BAKE SHOP BUILDING</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navcol-1">

            <%--Menu options--%>
            <ul class="nav navbar-nav">
                <li class="nav-item" role="presentation"><a class="nav-link" href="./">Home</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="controller?action=productPage">Product</a></li>
            </ul>

            <%--form chứa search bar để gửi thông tin đến server--%>
            <form name="form" class="form-inline mr-auto" action="controller" method="get">
                <div class="form-group">
                    <label for="search-field"><i class="fa fa-search text-white"></i></label>
                    <input class="form-control search-field" type="search" id="search-field" name="search" placeholder="Keyword..." value="${keyword}">
                </div>
            </form>

            <%--Trường hợp người dùng đã đăng nhập--%>
            <% if (account.isLogged()) { %>
                <a id="btnCart" class="btn btn-light action-button" role="button" href="cart">My cart</a>
                <a id="btnLogin" class="btn btn-light action-button" role="button" href="profile"><%= account.getUsername() %></a>
                <a id="btnSignup" class="btn btn-light action-button" role="button" href="controller?action=delete">Sign out</a>
            <% }

            /*Trường hợp ngược lại*/
            else { %>
                <a id="btnLogin" class="btn btn-light action-button" role="button" href="controller?action=loginPage">Log in</a>
                <a id="btnSignup" class="btn btn-light action-button" role="button" href="#">Sign up</a>
            <% } %>
        </div>
    </div>
</nav>