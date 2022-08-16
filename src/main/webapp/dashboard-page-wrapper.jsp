<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Contents were contain in here--%>
<div class="page-wrapper">

    <%--Title dashboard--%>
    <div class="page-breadcrumb">
        <div class="col-5">
            <h4 class="page-title">Dashboard</h4>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">

            <%--Feeds--%>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Feeds</h4>
                        <div class="feed-widget">
                            <ul class="list-style-none feed-body m-0 p-b-20">

                                <%--Content time sort by asc--%>
                                <li class="feed-item">
                                    <div class="feed-icon bg-info"><i class="far fa-bell"></i></div>
                                    You have 4
                                    pending tasks. <span class="ms-auto font-12 text-muted">Just Now</span>
                                </li>

                                <%--Content time sort by asc--%>
                                <li class="feed-item">
                                    <div class="feed-icon bg-success"><i class="ti-server"></i></div>
                                    Server #1
                                    overloaded.<span class="ms-auto font-12 text-muted">2 Hours ago</span>
                                </li>

                                <%--Content time sort by asc--%>
                                <li class="feed-item">
                                    <div class="feed-icon bg-warning"><i class="ti-shopping-cart"></i></div>
                                    New
                                    order received.<span class="ms-auto font-12 text-muted">31 May</span>
                                </li>

                                <%--Content time sort by asc--%>
                                <li class="feed-item">
                                    <div class="feed-icon bg-danger"><i class="ti-user"></i></div>
                                    New user
                                    registered.<span class="ms-auto font-12 text-muted">30 May</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>