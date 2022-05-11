<%--
  Created by IntelliJ IDEA.
  User: thanhkali
  Date: 5/9/22
  Time: 4:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
    <div class="col-md-6">
        <div class="card">
            <div class="header">
                <h4 class="title">Temporary invoice table</h4>
                <p class="category">Danh sánh sản phẩm được thêm vào hóa đơn</p>
            </div>
            <div class="content table-responsive table-full-width mb-4">
                <table class="table table-hover ">
                    <thead>
                    <th>STT</th>
                    <th>Loại Thuốc</th>
                    <th>Tên Thuốc</th>
                    <th>Dạng</th>
                    <th>Số Lượng</th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${listTam}" var="o" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${o.idDrug.idType.name}</td>
                            <td>${o.idDrug.name}</td>
                            <td>${o.idDrug.idForm.name}</td>
                            <td>
                                <a  class="btn btn-success" href="/Bill/edit?id=${o.idDrug.id}"> + </a>
                                <input  type="text" name="quantity" value="${o.quantity}" autocomplete="off" size="2">
                                <a class="btn btn-danger" href="/Bill/sup?id=${o.idDrug.id}"> - </a>
                            </td>
                            <td><a class="btn btn-danger" href="/Bill/remove?id=${o.idDrug.id}">Xóa</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <form action="/Bill/store" method="post">
                <button type="submit" class="btn btn-info btn-fill pull-right">Create invoice</button>
        </form>
    </div>
    <div class="col-md-6">
        <div class="card">
            <div class="header">
                <h4 class="title">Table on Drug</h4>
                <p class="category">Danh sánh thuốc đang có trong cửa hàng</p>
            </div>
            <div class="content table-responsive table-full-width">
                <table class="table table-hover table-striped">
                    <thead>
                    <th>Loại Thuốc</th>
                    <th>Tên Thuốc</th>
                    <th>Số lượng</th>
                    <th>Đơn Giá</th>
                    <th>Dạng</th>
                    <th>Ngày thêm</th>
                    <th>Ngày hết</th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="drug">
                        <tr>
                            <td>${drug.idDrug.idType.name}</td>
                            <td>${drug.idDrug.name}</td>
                            <td>${drug.quantity}</td>
                            <td><fmt:formatNumber value="${drug.idDrug.price}" pattern="#,###"/>VND</td>
                            <td>${drug.idDrug.idForm.name}</td>
                            <td><fmt:formatDate value="${drug.dateAdded}" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatDate value="${drug.dateEnd}" pattern="dd/MM/yyyy"/></td>
                            <td><a href="/Bill/edit?id=${drug.id}" class="btn btn-info">Add</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="card card-plain">
            <div class="header">
                <h4 class="title">Table on Bill</h4>
                <p class="category">Danh sánh hóa đơn đã được tạo</p>
            </div>
            <div class="content table-responsive table-full-width">
                <table class="table table-hover">
                    <thead>
                    <th>STT</th>
                    <th>Name Shop</th>
                    <th>Name User add</th>
                    <th>Drug</th>
                    <th>Total</th>
                    <th></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listBill}" var="c" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${c.idShop.name}</td>
                                <td>${c.idUser.name}</td>
                                <td>
                                    <c:forEach items="${c.detailBills}" var="dc">
                                        ${dc.idDrug.name},
                                    </c:forEach>
                                </td>
                                <c:set var="tol" scope="session" value="0"></c:set>
                                <c:forEach items="${c.detailBills}" var="dc">
                                    <c:set var="tol" scope="session" value="${tol + dc.quantity*dc.idDrug.price}"></c:set>
                                </c:forEach>
                                <td><fmt:formatNumber value="${tol}" pattern="#,###"/>VND</td>
                                <td>
                                    <form action="/Bill/show?id=${c.id}" method="post">
                                        <button class="btn btn-warning">Xem chi tiết</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>