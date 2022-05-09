<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Receipt</h1>
</div>
<div class="row">
    <div class="col-sm-6">
        <form class=" row mt-3 ms-0 pe-0" action="/receipt/store" method="post" >
            <div class="mb-3 col-6">
                <label class="form-label fw-bold">Đại Lý</label>
                <select class="form-select" name="DaiLy" >
                    <c:forEach items="${ dsDL }" var="o">
                        <option  value="${ o.maDaiLy }">
                                ${ o.tenDaiLy }
                        </option>
                    </c:forEach>
                </select>
            </div>
            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">Tên Sách</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Ghi chú</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHDTam}" var="o">
                    <tr>
                        <td>${o.maSach.tenSach}</td>
                        <td>
                            <a class="cart_quantity_up" href="/receipt/edit?id=${o.maSach.maSach}"> + </a>
                                <input class="cart_quantity_input" type="text" name="soLuong" value="${o.soLuong}" autocomplete="off" size="2">
                            <a class="cart_quantity_down" href="/receipt/sup?id=${o.maSach.maSach}"> - </a>
                        </td>
                        <td>
                            <div class="mb-3 col-6">
                                <textarea  class="form-control" name="ghiChu" rows="2">${o.ghiChu}</textarea>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="mt-3">
                <button  class="btn btn-success">Create Invoice</button>
            </div>
        </form>
    </div>
    <div class="col-sm-6">
        <h1 class="h3 mb-0 text-gray-800 text-center pb-5">List Sách </h1>
        <table class="table table-success table-striped">
            <thead>
            <tr>
                <th scope="col">Tên Sách</th>
                <th scope="col">Tên Tác Giả</th>
                <th scope="col">Giá Bán</th>
                <th scope="col">Giá Bán Cho Đại Lý</th>
                <th scope="col">Nhà Xuất Bản</th>
                <th scope="col">Số Trang</th>
                <th colspan="2">Manipulation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="o">
                <tr>
                    <td>${o.tenSach}</td>
                    <td>${o.tenTacGia}</td>
                    <td><fmt:formatNumber value="${o.giaBan}" pattern="#,###"/> VND</td>
                    <td><fmt:formatNumber value="${o.giaBanChoDaiLy}" pattern="#,###"/> VND</td>
                    <td>${o.maNhaXuatBan.tenNhaXuatBan}</td>
                    <td>${o.soTrang}</td>
                    <td><a href="/receipt/edit?id=${o.maSach}" class="btn btn-danger">Add</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<br>


