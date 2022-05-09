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
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Cửa Hàng</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="/Shop/update?id=${shop.id}" method="post">
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Chủ Cửa Hàng</label>
        <select class="form-select" name="idchu" >
            <c:forEach items="${ dsChuCH }" var="chu">
                <option ${shop.idChuCH.id==chu.id ? "selected" : ""} value="${ chu.id }">
                        ${ chu.name }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Tên Cửa Hàng</label>
        <input type="text" class="form-control" name="name" value="${shop.name}">
    </div>
    <div class="mb-3 col-12">
        <label class="form-label fw-bold">Địa Chỉ</label>
        <input type="text" class="form-control" name="address" value="${shop.address}">
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">Cập Nhật</button>
        <button type="reset" class="btn btn-primary">Làm Mới</button>
    </div>
</form>
<br>



