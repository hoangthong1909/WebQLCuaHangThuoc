<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="card">
  <c:if test="${sessionScope.sessionUser.isAdmin==0}">
  <div class="header">
    <h4 class="title">Quản Lý Tài Khoản</h4>
  </div>
  </c:if>
  <c:if test="${sessionScope.sessionUser.isAdmin==1}">
    <div class="header">
      <h4 class="title">Quản Lý Nhân Viên</h4>
    </div>
  </c:if>
  <div class="content">
    <form action="/User/store" method="post">
      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label>Họ Tên</label>
            <input type="text" class="form-control" placeholder="Họ Tên" name="name">
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" placeholder="Email" name="email">
          </div>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label>Điện Thoại</label>
            <input type="text" class="form-control" placeholder="Phone" name="sdt">
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group">
            <label>Password</label>
            <input type="password" class="form-control" placeholder="Password" name="password">
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6">
          <div class="form-group">
            <label>Ngày Sinh</label>
            <input type="date" class="form-control" name="birthday">
          </div>
        </div>
        <div class="col-md-6 mb-5 me-4">
          <label class="form-label fw-bold pe-4 me-3">Giới Tính</label>
          <input class="form-check-input  " type="radio" value="true" checked name="sex">
          <label class="form-check-label me-5">Nam</label>
          <input class="form-check-input" type="radio" value="false" name="sex">
          <label class="form-check-label me-3">Nữ</label>
        </div>
      </div>
      <c:if test="${sessionUser.isAdmin==0}">
      <div class="row">
        <div class=" p-3 mt-4 col-6">
          <label class="form-label fw-bold pe-4 me-3">Phân Quyền</label>
          <input class="form-check-input" type="radio" value="0" checked name="isAdmin">
          <label class="form-check-label me-5">Super Admin</label>
          <input class="form-check-input" type="radio" value="1" name="isAdmin">
          <label class="form-check-label me-3">Admin</label>
        </div>
      </div>
      </c:if>
      <button type="submit" class="btn btn-info btn-fill ">Thêm </button>
      <button type="reset" class="btn btn-info btn-fill ">Làm Mới</button>
      <div class="clearfix"></div>
    </form>
  </div>
</div>