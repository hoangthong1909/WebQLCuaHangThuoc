<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 5/8/22
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="vi">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>forgot password</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="google-signin-client_id"
          content="1001498436975-46u7ibil1e3pest1b89a4ig4omlt4bgu.apps.googleusercontent.com">
    <script src="../forgot/platform.js" async="" defer="" gapi_processed="true"></script>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../forgot/all.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="../forgot/util.css">
    <link rel="stylesheet" type="text/css" href="../forgot/main.css">
    <!--===============================================================================================-->

<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url(&#39;../forgot/bg-01.jpg&#39;);padding-top: 10%;">
        <div class="text-center form-group" style="width: 100%">
        <span class="login100-form-title p-b-10">
          <img src="../forgot/logo.png">
        </span>
        </div>
        <div class="wrap-login100 p-l-110 p-r-110 p-t-20 p-b-33">
            ${messgare}
                <input id="form-token" type="hidden" name="_csrf"
                       value="OWFJell3VEhNWSgVaz4Se1BYOSsTQQJ8WhIwD3QABRkLEnk3Fy4mAw==">
                <div class="p-t-10 p-b-10 col-md-12" style=": center;color: #555">
                    <h3>PASSWORD RETRIEVAL</h3>
                </div>

                <div class="p-t-31 p-b-9">
            <span class="txt1">
                Enter the email address you registered with, we will
                help you recover your password
            </span>
                </div>
                <form method="POST" action="/send" >
                    <div class="wrap-input100 validate-input" data-validate="Requires Enter Your Email">
                        <input placeholder="Email address you registered" class="input100" type="text" name="email">
                        <span class="focus-input100"></span>
                    </div>
                    <div class="container-login100-form-btn m-t-17">
                        <button type="submit" class="login100-form-btn">
                            Send me password
                        </button>
                    </div>
                </form>
        </div>
    </div>
<c:if test="${result!= null }">
    <p
            style="text-align: center; background: antiquewhite; padding: 5px; display: block;">
            ${messgare }</p>

    <c:if test="${result =='success'}">
        <script type="text/javascript">
            setTimeout(()=>{
                window.location.replace("http://localhost:8080/login");
            }, 2000);
        </script>
    </c:if>
</c:if>
</div>

<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="../forgot/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="../forgot/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="../forgot/popper.js"></script>
<script src="../forgot/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="../forgot/select2.min.js"></script>
<!--===============================================================================================-->
<script src="../forgot/moment.min.js"></script>
<script src="../forgot/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="../forgot/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="../forgot/main.js"></script>


<div id="eJOY__extension_root" class="eJOY__extension_root_class" style="all: unset;"></div>
<iframe id="nr-ext-rsicon"
        style="position: absolute; display: none; width: 50px; height: 50px; z-index: 2147483647; border-style: none; background: transparent;"
        src="./f_files/saved_resource.html"></iframe>
</body>

</html>