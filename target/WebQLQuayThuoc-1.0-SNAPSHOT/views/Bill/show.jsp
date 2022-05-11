<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 5/11/22
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="col-md-12">
    <div class="card card-plain">
        <div class="header">
            <p class="category">Danh sánh Bill Chi Tiết</p>
        </div>
        <div class="content table-responsive table-full-width">
            <table class="table table-hover">
                <thead>
                <th>STT</th>
                <th>Cửa Hàng</th>
                <th>Người tạo</th>
                <th>Loại Thuốc</th>
                <th>Tên Thuốc</th>
                <th>Dạng</th>
                <th>Số Lượng</th>
                <th>Đơn Giá</th>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="ct" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${ct.idBill.idShop.name}</td>
                        <td>${ct.idBill.idUser.name}</td>
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
                        <td><fmt:formatNumber value="${ct.idDrug.price}" pattern="#,###"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>