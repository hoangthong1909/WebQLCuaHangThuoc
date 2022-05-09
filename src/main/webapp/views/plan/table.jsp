<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<c:if test="${empty list}">
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
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">Số Hóa Đơn</th>
        <th scope="col">Ngày Tạo Hóa Đơn</th>
        <th scope="col">Đại Lý</th>
        <th scope="col">Sách</th>
        <th colspan="2">Manipulation</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${hd}" var="o">
        <tr>
            <td>${o.soHoaDon}</td>
            <td><fmt:formatDate value="${o.ngayLapHoaDon}" pattern="dd/MM/yyyy"/></td>
            <td>${o.maDaiLy.tenDaiLy}</td>
            <td>
                <c:forEach items="${o.entityList}" var="ct">
                    ${ct.maSach.tenSach},
                </c:forEach>
            </td>
            <td>
                <button data-toggle="modal" data-target="#b${o.soHoaDon}" class="btn btn-danger">Delete</button>
            </td>
            <div id="b${o.soHoaDon}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa hóa đơn ${o.soHoaDon} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/receipt/delete?id=${o.soHoaDon}" method="post">
                                <input type="hidden" value="${o.soHoaDon}" name="id">
                                <button class="btn btn-danger">Xóa</button>
                            </form>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    aria-label="Close">Hủy
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </tr>
    </c:forEach>
    </tbody>
</table>


