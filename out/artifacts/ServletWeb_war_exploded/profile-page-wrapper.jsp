<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--div page-wrapper contain all content--%>
<div class="page-wrapper">

    <%--Title--%>
    <div class="page-breadcrumb">
        <div class="col-5">
            <h4 class="page-title">Profile Page</h4>
        </div>
    </div>

    <%--Information of user account--%>
    <div class="container-fluid">
        <div class="row">

            <%--Name and position--%>
            <div class="col-lg-4 col-xlg-3 col-md-5">
                <div class="card">
                    <div class="card-body">
                        <div class="m-t-30" style="text-align: center;">
                            <img src="https://scontent-nrt1-1.xx.fbcdn.net/v/t1.6435-9/56547398_2264037760370134_1300808541551984640_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=WNzZ-YXORHgAX-_ojAz&_nc_ht=scontent-nrt1-1.xx&oh=00_AT9QwnWdJL6c9QU6gJ37yyt5O_rCAJsJVtBRAJ8i3M4u9Q&oe=62ECF2A0"
                                 class="rounded-circle" width="150" alt=""/>
                            <h4 class="card-title m-t-10">Lê Hoàng Long</h4>
                            <h6 class="card-subtitle">Admin of Bake Shop Building</h6>
                        </div>
                    </div>

                    <div>
                        <hr>
                    </div>

                    <div class="card-body"><small class="text-muted">Email address </small>
                        <h6>longlhfx02906@funix.edu.vn</h6> <small class="text-muted p-t-30 db">Phone</small>
                        <h6>+81 50 6873 3179</h6> <small class="text-muted p-t-30 db">Address</small>
                        <h6>Tokyo, Japan</h6>
                    </div>
                </div>
            </div>

            <%--Form--%>
            <div class="col-lg-8 col-xlg-9 col-md-7">
                <div class="card">
                    <div class="card-body">

                        <%--Update information by form--%>
                        <form class="form-horizontal form-material mx-2">

                            <%--Full name--%>
                            <div class="form-group">
                                <label class="col-md-12">Full Name</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Lê Hoàng Long"
                                           class="form-control form-control-line">
                                </div>
                            </div>

                            <%--Email--%>
                            <div class="form-group">
                                <label for="example-email" class="col-md-12">Email</label>
                                <div class="col-md-12">
                                    <input type="email" placeholder="longlhfx02906@funix.edu.vn"
                                           class="form-control form-control-line" name="example-email"
                                           id="example-email">
                                </div>
                            </div>

                            <%--Password--%>
                            <div class="form-group">
                                <label class="col-md-12">Password</label>
                                <div class="col-md-12">
                                    <input type="password" value="123456" class="form-control form-control-line">
                                </div>
                            </div>

                            <%--Phone number--%>
                            <div class="form-group">
                                <label class="col-md-12">Phone No</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="+81 50 6873 3179"
                                           class="form-control form-control-line">
                                </div>
                            </div>

                            <%--Message--%>
                            <div class="form-group">
                                <label class="col-md-12">Message</label>
                                <div class="col-md-12">
                                    <textarea rows="5" class="form-control form-control-line"></textarea>
                                </div>
                            </div>

                            <%--Country--%>
                            <div class="form-group">
                                <label class="col-sm-12">Select Country</label>
                                <div class="col-sm-12">
                                    <label>
                                        <select class="form-select shadow-none form-control-line">
                                            <option>Japan</option>
                                            <option>London</option>
                                            <option>India</option>
                                            <option>Usa</option>
                                            <option>Canada</option>
                                            <option>Thailand</option>
                                        </select>
                                    </label>
                                </div>
                            </div>

                            <%--Update button--%>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button class="btn btn-success text-white">Update Profile</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>