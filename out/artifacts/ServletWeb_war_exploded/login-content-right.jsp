<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Right content is form--%>
<div class=" box-2 d-flex flex-column h-100">

    <%--Form will sent request to controller--%>
    <form action="controller" method="post">

        <%--Title--%>
        <p class="lblTitle" style>Log in</p>

        <%--Set notification by null--%>
        <script>document.getElementById("noti").innerText = ""</script>

        <%--If cookie still exist, get it--%>
        <%
            Cookie[] cookies = request.getCookies();
            String username = null;
            String password = null;

            /*If cookie not emty*/
            if (cookies != null && cookies.length > 0) {

                /*Read each cookie*/
                for (Cookie cookie : cookies) {

                    /*Username cookie exist, set it to local variable*/
                    if (cookie.getName().matches("username")) {
                        username = cookie.getValue();
                    }

                    /*Password cookie exist, set it to local variable*/
                    if (cookie.getName().matches("password")) {
                        password = cookie.getValue();
                    }
                }

                /*Local variable not null, handle it*/
                if (username != null && password != null) {
        %>

        <%--Set username to input username--%>
        <p class="lblUaP">Username:</p>
        <label class="lblUaP">
            <input type="text" id="username" class="inputUaP" name="username" placeholder="..." value="<%= username %>">
        </label>

        <%--Set password to input password--%>
        <p class="lblUaP">Password:</p>
        <label class="lblUaP">
            <input type="password" id="password" class="inputUaP" name="password" placeholder="..."
                   value="<%= password %>">
        </label>

        <%--Checkbox remember--%>
        <input type="checkbox" class="checkbox" name="checkbox" value="remember">
        <label>Remember me</label><br>

        <%--Else, not set--%>
        <% } else { %>

        <%--Username is emty--%>
        <p class="lblUaP">Username:</p>
        <label class="lblUaP">
            <input type="text" id="username" class="inputUaP" name="username" placeholder="...">
        </label>

        <%--Password is emty--%>
        <p class="lblUaP">Password:</p>
        <label class="lblUaP">
            <input type="password" id="password" class="inputUaP" name="password" placeholder="...">
        </label>

        <%--Checkbox remember--%>
        <input type="checkbox" class="checkbox" name="checkbox" value="remember">
        <label>Remember me</label><br>
        <% }
        } %>

        <%--Button is clicked, sumit all information to controller--%>
        <button class="btnlogin" type="submit" name="action" value="login">Log in</button>

        <%--Receive error attribute from controller, error label will show--%>
        <% String error = (String) request.getAttribute("error");
            if (error != null) { %>
        <p id="noti"><%=error%></p>
        <% } %>
    </form>
</div>