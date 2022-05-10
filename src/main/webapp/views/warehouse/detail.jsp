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
            <p class="category">Danh sánh Kho Chi Tiết</p>
        </div>
        <div class="content table-responsive table-full-width">
            <table class="table table-hover">
                <thead>
                <th>STT</th>
                <th>Cửa Hàng</th>
                <th>Mã Kho</th>
                <th>Loại Thuốc</th>
                <th>Tên Thuốc</th>
                <th>Dạng</th>
                <th>Số Lượng</th>
                <th>Đơn Giá</th>
                <th>Ngày Nhập Kho</th>
                <th>Ngày Hết Hạn</th>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="ct" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${ct.idWarehouse.idShop.name}</td>
                        <td>CH${ct.idWarehouse.id}</td>
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
                        <td><fmt:formatDate value="${ct.dateAdded}" pattern="dd/MM/yyyy"/></td>
                        <td><fmt:formatDate value="${ct.dateEnd}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

