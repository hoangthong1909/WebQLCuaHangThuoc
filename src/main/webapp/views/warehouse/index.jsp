<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
    <div class="col-md-6">
        <form action="/Warehouse/store" method="post">
            <div class="card">
                <div class="header">
                    <h4 class="title">Temporary invoice table</h4>
                    <p class="category">Danh sánh sản phẩm được thêm vào Kho</p>
                </div>
                <div class="content table-responsive table-full-width mb-4">
                    <table class="table table-hover ">
                        <thead>
                        <th>STT</th>
                        <th>Loại Thuốc</th>
                        <th>Tên Thuốc</th>
                        <th>Dạng</th>
                        <th>Số Lượng</th>
                        <th>Hạn Sử Dụng</th>
                        <th></th>
                        </thead>
                        <tbody>
                        <c:forEach items="${listDetailWarehouseTam}" var="ctWarehouse" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${ctWarehouse.idDrug.idType.name}</td>
                                <td>${ctWarehouse.idDrug.name}</td>
                                <td>${ctWarehouse.idDrug.idForm.name}</td>
                                <td>
                                    <a  class="btn btn-success" href="/Warehouse/edit?id=${ctWarehouse.idDrug.id}"> + </a>
                                    <input  type="text" name="quantity" value="${ctWarehouse.quantity}" autocomplete="off" size="2">
                                    <a class="btn btn-danger" href="/Warehouse/sup?id=${ctWarehouse.idDrug.id}"> - </a>
                                </td>
                                <td>${ctWarehouse.idDrug.term} Ngày</td>
                                <td><a class="btn btn-danger" href="/Warehouse/remove?id=${ctWarehouse.idDrug.id}">Xóa</a></td>
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
            <button type="submit" class="btn btn-info btn-fill ">Thêm Vào Kho</button>
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
                            <td><a href="/Warehouse/edit?id=${drug.id}" class="btn btn-success">Thêm</a></td>
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
                <h4 class="title">Table on Warehouse</h4>
                <p class="category">Danh sánh Kho đã được tạo</p>
            </div>
            <div class="content table-responsive table-full-width">
                <table class="table table-hover">
                    <thead>
                    <th>STT</th>
                    <th>Cửa Hàng</th>
                    <th>Mã Kho</th>
                    <th>Thuốc</th>
                    <th></th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${dsWarehouse}" var="warehouse" varStatus="status">
                    <tr>
                        <td>#${status.count}</td>
                        <td>${warehouse.idShop.name}</td>
                        <td>CH${warehouse.id}</td>
                        <td>
                            <c:forEach items="${warehouse.entityList}" var="ct">
                                ${ct.idDrug.name},
                            </c:forEach>
                        </td>
                        <td>
                            <form action="/Warehouse/show" method="post">
                                <input type="hidden" value="${warehouse.id}" name="id">
                                <button class="btn btn-warning">Xem chi tiết</button>
                            </form>
                        </td>
                        <td>
                            <button data-toggle="modal" data-target="#b${warehouse.id}" class="btn btn-danger">Xóa</button>
                        </td>
                        <div id="b${warehouse.id}" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h3 class="modal-title">Xác nhận</h3>
                                        <button type="button" class="btn-close" data-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h5>Bạn muốn xóa Kế Hoạch ${warehouse.id} ?</h5>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="/Warehouse/delete" method="post">
                                            <input type="hidden" value="${warehouse.id}" name="id">
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