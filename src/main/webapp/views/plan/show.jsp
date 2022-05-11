<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 5/10/22
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="col-md-12">
    <div class="card card-plain">
        <div class="header">
            <p class="category">Danh sánh Kế Hoạch</p>
        </div>
        <div class="content table-responsive table-full-width">
            <table class="table table-hover">
                <thead>
                <th>STT</th>
                <th>Cửa Hàng</th>
                <th>Tên Kế Hoạch</th>
                <th>Thuốc</th>
                <th>Ghi Chú</th>
                <th>Tổng Tiền</th>
                <th></th>
                <th></th>
                </thead>
                <tbody>
                <c:forEach items="${planList}" var="planList" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${planList.idCuaHang.name}</td>
                        <td>CH${planList.name}</td>
                        <td>
                            <c:forEach items="${planList.entityList}" var="ct">
                                ${ct.idDrug.name},
                            </c:forEach>
                        </td>
                        <td>
                                ${planList.content}
                        </td>
                        <td> <p style="color:red;"><fmt:formatNumber value="${tong}" pattern="#,###"/> VND</p></td>
                        <td>
                            <c:choose>
                                <c:when test="${planList.status==1}">
                                    <button data-toggle="modal" data-target="#ea${planList.id}" class="btn btn-success">Xác Nhận</button>
                                    <button data-toggle="modal" data-target="#eb${planList.id}" class="btn btn-danger">Hủy</button>
                                </c:when>
                                <c:when test="${planList.status==2}"><p style="color:#0c4128"></p>Đã Xác Nhận</c:when>
                                <c:when test="${planList.status==3}"><p style="color: red">Đã Hủy</p></c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <form action="/Plan/showDetail" method="post">
                                <input type="hidden" value="${planList.id}" name="id">
                                <button class="btn btn-warning">Xem chi tiết</button>
                            </form>
                        </td>
                        <!--  End Modal -->
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

