<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:MasterPage>
    <jsp:attribute name="titlePage">
        <h1>Motores</h1>
    </jsp:attribute>    
    <jsp:body>      
        <div class="link">Click on <a href="" id="show">Login</a></div> 
        <div id="dialog" title="Login">
            <form id="login">
                <label>User</label>
                <input type="text" name="userName">
                <label>Password</label>
                <input type="password" name="password">
                <button type="submit">Login</button>
            </form>
        </div>

        <script>
            $(function () {
                $("#dialog").dialog({
                    autoOpen: false,
                    width: 380
                });

                $("#show").click(function () {
                    $("#dialog").dialog("open");
                    return false;
                });

                $("#login").submit(function () {

                    var data = $(this).serializeObject();
                    $.ajax({
                        'type': 'POST',
                        'url': "/SpringSecurityAjax/alogin",
                        'contentType': 'application/json',
                        'data': JSON.stringify(data),
                        'dataType': 'json',
                        'success': function (result) {
                            if (result.loggedIn) {
                                $("#dialog").dialog("close");
                                location.href = "postLogin";
                            } else {
                                $(".error").remove();
                                $("#login").prepend("<div class='error'>Login Failed. 
                                        Username or Password is incorrect. < /div>");
                            }
                        }
                    });

                    return false;
                });
            });

            $.fn.serializeObject = function () {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function () {
                    if (o[this.name]) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
        </script>
    </jsp:body>
</t:MasterPage>
