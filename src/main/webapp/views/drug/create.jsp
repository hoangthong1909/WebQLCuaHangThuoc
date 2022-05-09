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
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Thuốc</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="/Drug/store" method="post">
    <div class="mb-3 col-12">
        <label class="form-label fw-bold">Tên Thuốc</label>
        <input type="text" class="form-control" name="name">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Loại Thuốc</label>
        <select class="form-select" name="typeDrugID" >
            <c:forEach items="${ dsTypeDrug }" var="type">
                <option value="${ type.id }">
                        ${ type.name }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Đơn Giá</label>
        <input type="text" class="form-control" name="price">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Dạng</label>
        <select class="form-select" name="formDrugID" >
            <c:forEach items="${ dsFormDrug }" var="form">
                <option value="${ form.id }">
                        ${ form.name }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Hạn Sử Dụng</label>
        <input type="tel" class="form-control" name="term">
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-primary">Làm Mới</button>
    </div>
</form>
<br>



