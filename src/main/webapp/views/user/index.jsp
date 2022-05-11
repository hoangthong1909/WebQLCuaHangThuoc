<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 5/8/22
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <div class="content table-responsive table-full-width">
                <c:if test="${empty ds}">
                    <p class="alert alert-warning">
                        Vui Lòng Thêm Mới Dữ Liệu
                    </p>
                </c:if>
                <c:if test="${!empty sessionScope.error}">
                    <div class="alert alert-danger">
                            ${sessionScope.error}
                    </div>
                    <c:remove var="error" scope="session"/>
                </c:if>
                <c:if test="${!empty sessionScope.message}">
                    <div class="alert alert-success">
                            ${sessionScope.message}
                    </div>
                    <c:remove var="message" scope="session"/>
                </c:if>
                <table class="table table-hover table-striped">
                    <thead>
                    <th>Họ Tên</th>
                    <th>Điện Thoại</th>
                    <th>Email</th>
                    <th>Ngày Sinh</th>
                    <th>Giới Tính</th>
                    <c:if test="${sessionScope.sessionUser.isAdmin==0}">
                    <th>Quyền</th>
                    </c:if>
                    <th></th>
                    <th></th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${ds}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.sdt}</td>
                        <td>${user.email}</td>
                        <td><fmt:formatDate value="${user.birthday}" pattern="dd/MM/yyyy"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.sex==true}">Nam</c:when>
                                <c:when test="${user.sex==false}">Nữ</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <c:if test="${sessionScope.sessionUser.isAdmin==0}">
                        <td>
                            <c:choose>
                                <c:when test="${user.isAdmin==0}">Super Admin</c:when>
                                <c:when test="${user.isAdmin==1}">Admin</c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        </c:if>
                        <td>
                            <form action="/User/edit" method="post">
                                <input type="hidden" value="${user.id}" name="id">
                                <button class="btn btn-primary">Cập Nhật</button>
                            </form>
                        </td>
                        <td>
                            <button class="btn btn-danger" data-toggle="modal" data-target="#a${user.id}">Xóa</button>
                        </td>
                        <c:if test="${user.status==1 && user.isAdmin==1 && sessionScope.sessionUser.isAdmin ==0}">
                        <td>
                            <form action="/User/lock" method="post">
                                <input type="hidden" value="${user.id}" name="id">
                                <button class="btn btn-primary">Khóa</button>
                            </form>
                        </td>
                        </c:if>
                        <c:if test="${user.status==2 && user.isAdmin==1  && sessionScope.sessionUser.isAdmin ==0}">
                        <td>
                            <form action="/User/unlock" method="post">
                                <input type="hidden" value="${user.id}" name="id">
                                <button class="btn btn-primary">Mở Khóa</button>
                            </form>
                        </td>
                        </c:if>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>