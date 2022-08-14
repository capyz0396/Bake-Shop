<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--div box-2 chứa toàn bộ nội dung--%>
<div class=" box-2 d-flex flex-column h-100">

    <%--Vì cần xác nhận thông tin với server nên sử dụng thẻ form--%>
    <form action="controller" method="post">

        <%--Tiêu đề--%>
        <p class="lblTitle" style>Log in</p>

        <%--Set thông báo lỗi bằng rỗng--%>
        <script>document.getElementById("noti").innerText = ""</script>

        <%--Xử lý nếu người dùng có lưu cookie--%>
        <%
            Cookie[] cookies = request.getCookies();
            String username = null;
            String password = null;

            /*Nếu cookies không rỗng thì tiến hành*/
            if (cookies != null && cookies.length > 0) {

                /*Sử dụng foreach để duyệt từng cookie*/
                for (Cookie cookie : cookies) {

                    /*Nếu match thì lưu lại*/
                    if (cookie.getName().matches("username")) {
                        username = cookie.getValue();
                    }

                    /*Nếu match thì lưu lại*/
                    if (cookie.getName().matches("password")) {
                        password = cookie.getValue();
                    }
                }

                /*Sau khi duyệt xong, nếu cả 2 khác rỗng*/
                if (username != null && password != null) {
        %>

        <%--Gán vào input username--%>
        <p class="lblUaP">Username:</p>
        <label class="lblUaP">
            <input type="text" id="username" class="inputUaP" name="username" placeholder="..." value="<%= username %>">
        </label>

        <%--Gán vào input password--%>
        <p class="lblUaP">Password:</p>
        <label class="lblUaP">
            <input type="password" id="password" class="inputUaP" name="password" placeholder="..."
                   value="<%= password %>">
        </label>

        <%--Checkbox để server quyết định có tạo cookie cho người dùng hay không--%>
        <input type="checkbox" class="checkbox" name="checkbox" value="remember">
        <label>Remember me</label><br>

        <%--Ngược lại không gán--%>
        <% } else { %>

        <%--Username rỗng--%>
        <p class="lblUaP">Username:</p>
        <label class="lblUaP">
            <input type="text" id="username" class="inputUaP" name="username" placeholder="...">
        </label>

        <%--Password rỗng--%>
        <p class="lblUaP">Password:</p>
        <label class="lblUaP">
            <input type="password" id="password" class="inputUaP" name="password" placeholder="...">
        </label>

        <%--Checkbox để server quyết định có tạo cookie cho người dùng hay không--%>
        <input type="checkbox" class="checkbox" name="checkbox" value="remember">
        <label>Remember me</label><br>
        <% }
        } %>

        <%--button để submit thông tin lên server--%>
        <button class="btnlogin" type="submit" name="action" value="login">Log in</button>

        <%--Get lỗi từ hệ thống nếu có--%>
        <% String error = (String) request.getAttribute("error");
            if (error != null) { %>
        <p id="noti"><%=error%></p>
        <% } %>
    </form>
</div>