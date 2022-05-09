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
                    <th>Loại Thuốc</th>
                    <th>Tên Thuốc</th>
                    <th>Đơn Giá</th>
                    <th>Dạng</th>
                    <th>Hạn Sử Dụng</th>
                    <th></th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${ds}" var="drug">
                    <tr>
                        <td>${drug.idType.name}</td>
                        <td>${drug.name}</td>
                        <td><fmt:formatNumber value="${drug.price}" pattern="#,###"/> VND</td>
                        <td>${drug.idForm.name}</td>
                        <td>${drug.term} Ngày</td>
                        <td>
                            <form action="/Drug/edit" method="post">
                                <input type="hidden" value="${drug.id}" name="id">
                                <button class="btn btn-primary">Cập Nhật</button>
                            </form>
                        </td>
                        <td>
                            <button class="btn btn-danger" data-toggle="modal" data-target="#b${drug.id}">Xóa</button>
                        </td>
                        <div id="b${drug.id}" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h3 class="modal-title">Xác nhận</h3>
                                        <button type="button" class="btn-close" data-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h5>Bạn muốn xóa ${drug.name} ?</h5>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="/Drug/delete" method="post">
                                            <input type="hidden" value="${drug.id}" name="id">
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

            </div>
        </div>
    </div>
</div>