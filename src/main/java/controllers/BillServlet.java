package controllers;

import Dao.*;
import entitys.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BillServlet", value = {"/Bill/index",
        "/Bill/show","/Bill/sup",
        "/Bill/create",
        "/Bill/store",
        "/Bill/edit",
        "/Bill/update",
        "/Bill/delete",})
public class BillServlet extends HttpServlet {
    private billDao billDao;
    private detailBillDao detailBillDao;
    private detailedWarehouseDao detailedWarehouseDao;
    private warehouseDao warehouseDao;
    private ShopDao shopDao;
    private List<DetailBill> billListTam;
    private Dao.drugDao drugDao;
    public BillServlet() {
        this.billDao = new billDao();
        this.detailBillDao = new detailBillDao();
        this.detailedWarehouseDao = new detailedWarehouseDao();
        this.shopDao = new ShopDao();
        this.drugDao = new drugDao();
        this.warehouseDao = new warehouseDao();
        this.billListTam = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        request.setAttribute("uri",3);
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("create")) {
            create(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        }else if (uri.contains("sup")){
            this.sup(request, response);
        }else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("uri",3);
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        } else if (uri.contains("update")) {
            update(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else if (uri.contains("show")) {
            show(request, response);
        }
    }
    private void index(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        extracted(request);
        request.setAttribute("view", "/views/Bill/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void extracted(HttpServletRequest request) {
        HttpSession session= request.getSession();
        User tk = (User) session.getAttribute("sessionUser");
        int id = -1;
        if (tk.getIsAdmin()==1){
            Shop shop = shopDao.findByIDchuCH(tk.getId());
            id = shop.getId();
        }else if (tk.getIsAdmin()==2){
            Shop shop = this.shopDao.findByIDchuCH(tk.getUserAdd());
            id = shop.getId();
        }
        List<DetailedWarehouse> list = getDetailedWarehouses(request);
        List<Bill> billList = this.billDao.findShop(id);
        request.setAttribute("list",list);
        request.setAttribute("listBill",billList);
        request.setAttribute("listTam",this.billListTam);
    }

    private List<DetailedWarehouse> getDetailedWarehouses(HttpServletRequest request) {
        HttpSession session= request.getSession();
        User tk = (User) session.getAttribute("sessionUser");
        int id = -1;
        if (tk.getIsAdmin()==1){
            Shop shop = shopDao.findByIDchuCH(tk.getId());
            id = shop.getId();
        }else if (tk.getIsAdmin()==2){
            Shop shop = this.shopDao.findByIDchuCH(tk.getUserAdd());
            id = shop.getId();
        }
        List<DetailedWarehouse> list = this.detailedWarehouseDao.findShop(id);
        return list;
    }

    private void sup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        int check = 0;
        DetailBill entity = new DetailBill();
        if (this.billListTam.size() > 0) {
            for (DetailBill ct : this.billListTam) {
                if (ct.getIdDrug().getId() == id) {
                    ct.setQuantity(ct.getQuantity() - 1);
                    if (ct.getQuantity() <= 0) {
                        check++;
                        entity = ct;
                    }
                }
            }
        }
        if (check != 0) {
            this.billListTam.remove(entity);
        }
        response.sendRedirect("/Bill/index");
    }
    private void create(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<DetailedWarehouse> list = getDetailedWarehouses(request);
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check = 0;
        if (this.billListTam.size() > 0) {
            for (DetailBill ct : this.billListTam) {
                if (ct.getIdDrug().getId() == id) {
                    for (DetailedWarehouse o:list) {
                        if (ct.getIdDrug().getId()==o.getIdDrug().getId()){
                            check++;
                            if (ct.getQuantity()<o.getQuantity()){
                                ct.setQuantity(ct.getQuantity()+1);
                            }
                        }
                    }
                }
            }
        }
        if (check == 0) {
            DetailBill detailBill = new DetailBill();
            Drug drug = this.drugDao.findById(id);
            detailBill.setQuantity(1);
            detailBill.setIdDrug(drug);
            this.billListTam.add(detailBill);
        }
        request.setAttribute("list",list);
        request.setAttribute("listTam",this.billListTam);
        response.sendRedirect("/Bill/index");
    }

    private void update(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException, IOException {

        response.sendRedirect("/Bill/index");
    }

    private void store(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        List<DetailedWarehouse> list = getDetailedWarehouses(request);
        HttpSession session= request.getSession();
        User tk = (User) session.getAttribute("sessionUser");
        Shop shop = new Shop();
        if (tk.getIsAdmin()==1){
            shop = shopDao.findByIDchuCH(tk.getId());
        }else if (tk.getIsAdmin()==0){

        }
        Bill bill = new Bill();
        bill.setIdShop(shop);
        bill.setIdUser(tk);
        Bill newBill1 = this.billDao.create(bill);
        try {
            for (DetailBill o :this.billListTam) {
                o.setIdBill(newBill1);
                this.detailBillDao.create(o);
                for (DetailedWarehouse op:list) {
                    if (o.getIdDrug().getId()==op.getIdDrug().getId()){
                        op.setQuantity(op.getQuantity()-o.getQuantity());
                        if (op.getQuantity()<=0){
                            this.detailedWarehouseDao.delete(op);
                        }else {
                            this.detailedWarehouseDao.update(op);
                        }
                    }
                }
            }
            this.billListTam.clear();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Bill/index");
            session.setAttribute("error", "Thêm mới thất bại");
        }
        response.sendRedirect("/Bill/index");
    }

    private void show(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        List<DetailBill> detailBills = this.detailBillDao.findBill(id);
        request.setAttribute("list",detailBills);
        request.setAttribute("view","/views/Bill/show.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request,response);
    }

    private void delete(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            Bill entity = this.billDao.findById(id);
            List<DetailBill> listCT = entity.getDetailBills();
            this.billDao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Warehouse/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Warehouse/index");
            e.printStackTrace();
        }
        response.sendRedirect("/Bill/index");
    }
}
