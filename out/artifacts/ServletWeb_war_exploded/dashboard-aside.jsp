<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Get bean from session--%>
<jsp:useBean id="account" class="com.example.test.model.Account" scope="session"/>
<aside class="left-sidebar" data-sidebarbg="skin6">
    <div class="scroll-sidebar">
        <nav class="sidebar-nav">
            <ul id="sidebarnav">

                <%--This lines contain user's information--%>
                <li>
                    <div class="user-profile d-flex no-block dropdown m-t-20">
                        <div class="user-pic"><img src="img/1.jpg" alt="users" class="rounded-circle" width="40" /></div>
                        <div class="user-content hide-menu m-l-10">
                            <a href="#" class="" id="Userdd" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <h5 class="m-b-0 user-name font-medium"><%= account.getUsername() %></h5>
                                <span class="op-5 user-email">longlhfx02906@funix.edu.vn</span>
                            </a>
                        </div>
                    </div>
                </li>

                <%--Dashboard option--%>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="dashboard" aria-expanded="false">
                        <i class="mdi mdi-view-dashboard"></i>
                        <span class="hide-menu">Dashboard</span>
                    </a>
                </li>

                <%--Profile option--%>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="profile" aria-expanded="false">
                        <i class="mdi mdi-account-network"></i><span class="hide-menu">Profile</span>
                    </a>
                </li>

                <%--Home option--%>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="./" aria-expanded="false">
                        <i class="mdi mdi-account-network"></i><span class="hide-menu">Home</span>
                    </a>
                </li>

                <%--Product option--%>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="controller?action=productPage" aria-expanded="false">
                        <i class="mdi mdi-account-network"></i><span class="hide-menu">Product</span>
                    </a>
                </li>

                <%--Sign out option--%>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="controller?action=delete" aria-expanded="false">
                        <i class="mdi mdi-account-network"></i><span class="hide-menu">Sign out</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</aside>