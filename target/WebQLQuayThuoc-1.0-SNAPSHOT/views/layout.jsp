<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 5/8/22
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Web cửa hàng thuốc</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <!-- Bootstrap core CSS     -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="../assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="../assets/css/light-bootstrap-dashboard.css?v=1.4.0" rel="stylesheet"/>
    <link href="../assets/css/light-bootstrap-dashboard.css?v=2.0.0 " rel="stylesheet" />


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="../assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="../assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

</head>
<body>
<c:forEach items="${ds}" var="user">
    <!-- Mini Modal -->
    <div class="modal fade modal-mini modal-primary" id="a${user.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <div class="modal-profile">
                        Xác Nhận
                    </div>
                </div>
                <div class="modal-body ">
                    <h5>Bạn muốn xóa người dùng ${user.name} ?</h5>
                </div>
                <div class="modal-footer">
                    <form action="/User/delete" method="post">
                        <input type="hidden" value="${user.id}" name="id">
                        <button class="btn btn-success">Xóa</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--  End Modal -->
</c:forEach>
<c:forEach items="${ds}" var="drug">
    <div id="b${drug.id}" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Xác nhận</h3>
                </div>
                <div class="modal-body">
                    <h5>Bạn muốn xóa ${drug.name} ?</h5>
                </div>
                <div class="modal-footer">
                    <form action="/Drug/delete" method="post">
                        <input type="hidden" value="${drug.id}" name="id">
                        <button class="btn btn-success">Xóa</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"
                                aria-label="Close">Hủy
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:forEach items="${ds}" var="shop">
    <div id="c${shop.id}" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Xác nhận</h3>
                    <button type="button" class="btn-close" data-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h5>Bạn muốn xóa ${shop.name} ?</h5>
                </div>
                <div class="modal-footer">
                    <form action="/Shop/delete" method="post">
                        <input type="hidden" value="${shop.id}" name="id">
                        <button class="btn btn-danger">Xóa</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                aria-label="Close">Hủy
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:forEach items="${dsWarehouse}" var="warehouse" varStatus="status">
    <div id="d${warehouse.id}" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Xác nhận</h3>
                </div>
                <div class="modal-body">
                    <h5>Bạn muốn xóa Mã Kho CH${warehouse.id} ?</h5>
                </div>
                <div class="modal-footer">
                    <form action="/Warehouse/delete" method="post">
                        <input type="hidden" value="${warehouse.id}" name="id">
                        <button class="btn btn-danger">Xóa</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                aria-label="Close">Hủy
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:forEach items="${dsPlan}" var="plan" varStatus="status">
    <div id="e${plan.id}" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Xác nhận</h3>
                    <button type="button" class="btn-close" data-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h5>Bạn muốn xóa Kế Hoạch ${plan.name} ?</h5>
                </div>
                <div class="modal-footer">
                    <form action="/Plan/delete" method="post">
                        <input type="hidden" value="${plan.id}" name="id">
                        <button class="btn btn-danger">Xóa</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                aria-label="Close">Hủy
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:forEach items="${planList}" var="planList" varStatus="status">
    <div class="modal fade modal-mini modal-primary" id="ea${planList.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <div class="modal-profile">
                        <i class="nc-icon nc-bulb-63"></i>
                    </div>
                </div>
                <div class="modal-body text-center">
                    <p>Xác Nhận ${planList.name} Của ${planList.idCuaHang.name} </p>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-success" href="/Plan/confirm?id=${planList.id}">Xác Nhận</a>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
                </div>
            </div>
        </div>
    </div>
    <!--  End Modal -->
    <!-- Mini Modal -->
    <div class="modal fade modal-mini modal-primary" id="eb${planList.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header justify-content-center">
                    <div class="modal-profile">
                        <i class="nc-icon nc-bulb-63"></i>
                    </div>
                </div>
                <div class="modal-body text-center">
                    <p>Xác Nhận Hủy ${planList.name} Của ${planList.idCuaHang.name} </p>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-success" href="/Plan/cancel?id=${planList.id}">Xác Nhận</a>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<c:forEach items="${detailPlans}" var="ct" varStatus="status">
    <div id="ec${ct.id}" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Xác nhận</h3>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <form action="/Plan/updateSL" method="post"
                    <lable>Nhập Số Lượng</lable>
                    <input type="text" name="soLuong">
                    <input type="hidden" value="${ct.id}" name="id">
                    <button class="btn btn-success">Cập Nhât</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            aria-label="Close">Hủy
                    </button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</c:forEach>

<div class="wrapper">
    <div class="sidebar" data-color="blue" data-image="assets/img/sidebar-5.jpg">
        <!--
            Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
            Tip 2: you can also add an image using data-image tag
        -->
        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="http://www.creative-tim.com" class="simple-text">
                    Drug store manager
                </a>
            </div>
            <ul class="nav">
                <c:if test="${sessionScope.sessionUser.isAdmin ==0}">
                    <li class="
                    <c:if test="${uri==1}">
                        active
                    </c:if>">
                        <a href="/User/index">
                            <i class="pe-7s-user"></i>
                            <p>Users</p>
                        </a>
                    </li>
                    <li class="
                    <c:if test="${uri==2}">
                        active
                    </c:if>">
                        <a href="/Shop/index">
                            <i class="pe-7s-graph"></i>
                            <p>Shop</p>
                        </a>
                    </li>
                    <li class="<c:if test="${uri==3}">
                        active
                    </c:if>">
                        <a href="/Plan/show">
                            <i class="pe-7s-news-paper"></i>
                            <p>Plan</p>
                        </a>
                    </li>
                    <li class="<c:if test="${uri==6}">
                        active
                    </c:if>">
                        <a href="/Drug/index">
                            <i class="pe-7s-drop"></i>
                            <p>Drug</p>
                        </a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.sessionUser.isAdmin ==1}">
                    <li class="<c:if test="${uri==1}">
                        active
                    </c:if>">
                        <a href="/User/index">
                            <i class="pe-7s-note2"></i>
                            <p>User</p>
                        </a>
                    </li>
                    <li class="<c:if test="${uri==4}">
                        active
                    </c:if>">
                        <a href="/Plan/index">
                            <i class="pe-7s-news-paper"></i>
                            <p>Plan</p>
                        </a>
                    </li>
                    <li class="<c:if test="${uri==5}">
                        active
                    </c:if>">
                        <a href="/Warehouse/index">
                            <i class="pe-7s-science"></i>
                            <p>Warehouse</p>
                        </a>
                    </li>
                    <li class="<c:if test="${uri==3}">
                        active
                    </c:if>">
                        <a href="/Bill/index">
                            <i class="pe-7s-drop"></i>
                            <p>Bill</p>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.sessionUser.isAdmin ==2}">
                    <li class="<c:if test="${uri==3}">
                        active
                    </c:if>">
                        <a href="/Bill/index">
                            <i class="pe-7s-drop"></i>
                            <p>Bill</p>
                        </a>
                    </li>
                </c:if>

            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="">
                                <p>Account</p>
                            </a>
                        </li>
                        <li>
                            <a href="/signout">
                                <p>Log out</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg"></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <jsp:include page="${view}"></jsp:include>
            <jsp:include page="${view1}"></jsp:include>
        </div>

        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </footer>

    </div>
</div>


</body>

<!--   Core JS Files   -->
<script src="../assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="../assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="../assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="../assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="../assets/js/demo.js"></script>

<script src="../assets/js/plugins/bootstrap-switch.js"></script>

</html>
