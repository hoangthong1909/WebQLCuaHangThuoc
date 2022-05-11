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
            <p class="category">Danh sánh Kế Hoạch Chi Tiết</p>
        </div>
        <div class="content table-responsive table-full-width">
            <table class="table table-hover">
                <thead>
                <th>STT</th>
                <th>Cửa Hàng</th>
                <th>Tên Kế Hoạch</th>
                <th>Loại Thuốc</th>
                <th>Tên Thuốc</th>
                <th>Dạng</th>
                <th>Số Lượng</th>
                <th>Đơn Giá</th>
                <th>Tổng Tiền</th>
                </thead>
                <tbody>
                <c:forEach items="${detailPlans}" var="ct" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${ct.idPlan.idCuaHang.name}</td>
                        <td>${ct.idPlan.name}</td>
                        <td>
                                ${ct.idDrug.idType.name}
                        </td>
                        <td>
                                ${ct.idDrug.name}
                        </td>
                        <td>
                                ${ct.idDrug.idForm.name}
                        </td>
                        <td>
                                ${ct.quantity}
                        </td>
                        <td><fmt:formatNumber value="${ct.idDrug.price}" pattern="#,###"/> VND</td>
                        <td><p style="color: red"><fmt:formatNumber value="${ct.idDrug.price*ct.quantity}" pattern="#,###"/> VND</p></td>
                        <td><button class="btn btn-primary" data-toggle="modal" data-target="#ec${ct.id}">Cập Nhât</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

