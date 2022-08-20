<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--div container contain all content--%>
<div class="container hero">

    <%--div row design interface to 2 part--%>
    <div class="row">

        <%--Content part--%>
        <div class="col-12 col-lg-6 col-xl-5 offset-xl-1">
            <h1>Welcome to<br>Bake Shop Building</h1>
            <p>We made bake for our happy!<br></p>
            <a href="controller?action=loginPage">
                <button class="btn btn-light btn-lg action-button" type="button">Sign in now<i
                        class="fa fa-long-arrow-right ml-2"></i></button>
            </a>
        </div>

        <%--Logo part--%>
        <div class="col-md-5 col-lg-5 offset-lg-1 offset-xl-0 d-none d-lg-block phone-holder">
            <div class="iphone-mockup">
                <img class="device" src="https://i.imgur.com/ghCcfcn.png">
            </div>
        </div>
    </div>
</div>