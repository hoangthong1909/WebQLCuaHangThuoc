<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
    <div class="col-md-6">
        <form action="/Plan/store" method="post">
            <div class="card">
                <div class="header">
                    <h4 class="title">Temporary invoice table</h4>
                    <p class="category">Danh sánh sản phẩm được thêm vào kế hoạch</p>
                </div>
                <div class="content table-responsive table-full-width mb-4">
                    <table class="table table-hover ">
                        <thead>
                        <th>STT</th>
                        <th>Loại Thuốc</th>
                        <th>Tên Thuốc</th>
                        <th>Dạng</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${listDetailPlanTam}" var="ctPlan" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${ctPlan.idDrug.idType.name}</td>
                                <td>${ctPlan.idDrug.name}</td>
                                <td>${ctPlan.idDrug.idForm.name}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="mb-3 col-12">
                <lable>Tên Kế Hoạch</lable>
                <input type="text" class="form-control" name="name" >
                <lable>Ghi Chú</lable>
                <textarea class="form-control" name="content" rows="2"></textarea>
            </div>
            <button type="submit" class="btn btn-info btn-fill ">Thêm Kế Hoạch</button>
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
                    <th>STT</th>
                    <th>Loại Thuốc</th>
                    <th>Tên Thuốc</th>
                    <th>Đơn Giá</th>
                    <th>Dạng</th>
                    <th>Hạn Sử Dụng</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${drugList}" var="drug" varStatus="status">
                        <tr>
                            <td>#${status.count}</td>
                            <td>${drug.idType.name}</td>
                            <td>${drug.name}</td>
                            <td><fmt:formatNumber value="${drug.price}" pattern="#,###"/> VND</td>
                            <td>${drug.idForm.name}</td>
                            <td>${drug.term} Ngày</td>
                            <td><a href="/Plan/edit?id=${drug.id}" class="btn btn-success">Thêm</a></td>
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
                <h4 class="title">Table on Plan</h4>
                <p class="category">Danh sánh kế hoạch đã được tạo</p>
            </div>
            <div class="content table-responsive table-full-width">
                <table class="table table-hover">
                    <thead>
                    <th>STT</th>
                    <th>Cửa Hàng</th>
                    <th>Tên Kế Hoạch</th>
                    <th>Thuốc</th>
                    <th>Ghi Chú</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dsPlan}" var="plan" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${plan.idCuaHang.name}</td>
                        <td>${plan.name}</td>
                        <td>
                            <c:forEach items="${plan.entityList}" var="ct">
                                ${ct.idDrug.name},
                            </c:forEach>
                        </td>
                        <td>${plan.content}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>