//package controllers;
//
//import Dao.*;
//import entitys.*;
//import org.apache.commons.beanutils.BeanUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@WebServlet({"/Warehouse/index", "/Warehouse/sup", "/Warehouse/create", "/Warehouse/store", "/Warehouse/edit", "/Warehouse/update", "/Warehouse/delete",})
//public class WarehouseServlet extends HttpServlet {
//    private drugDao drugDao;
//    private planDao planDao;
//    private detailPlanDao detailPlanDao;
//    private ShopDao shopDao;
//    List<DetailPlan> listDetailPlanTam;
//
//    public WarehouseServlet() {
//        this.detailPlanDao = new detailPlanDao();
//        this.drugDao = new drugDao();
//        this.shopDao = new ShopDao();
//        this.planDao = new planDao();
//        this.listDetailPlanTam = new ArrayList<>();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String uri = request.getRequestURI();
//        if (uri.contains("index")) {
//            index(request, response);
//        } else if (uri.contains("create")) {
//            create(request, response);
//        } else if (uri.contains("sup")) {
//            sup(request, response);
//        } else if (uri.contains("edit")) {
//            edit(request, response);
//        } else {
//            //404
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        String uri = request.getRequestURI();
//        if (uri.contains("store")) {
//            store(request, response);
//        } else if (uri.contains("update")) {
//            update(request, response);
//        } else if (uri.contains("delete")) {
//            delete(request, response);
//        }
//    }
//
//    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Drug> drugList = this.drugDao.findAll();
//        List<Shop> shopList = this.shopDao.findAll();
//        List<Plan> listPlan = this.planDao.findAll();
//        request.setAttribute("dsShop", shopList);
//        request.setAttribute("drugList", drugList);
//        request.setAttribute("dsPlan", listPlan);
//        request.setAttribute("listDetailPlanTam", this.listDetailPlanTam);
//        request.setAttribute("view", "/views/plan/create.jsp");
//        request.setAttribute("view1", "/views/plan/table.jsp");
//        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
//    }
//
//    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        request.setAttribute("view", "/Views/user/create.jsp");
//        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
//    }
//
//    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String strid = request.getParameter("id");
//        int id = Integer.parseInt(strid);
//        int check = 0;
//        if (listDetailPlanTam.size() > 0) {
//            for (DetailPlan ct : this.listDetailPlanTam) {
//                if (ct.getIdDrug().getId() == id) {
//                    check++;
//                }
//            }
//        }
//        if (check == 0) {
//            DetailPlan detailPlan = new DetailPlan();
//            Drug drug = this.drugDao.findById(id);
//            detailPlan.setQuantity(1);
//            detailPlan.setIdDrug(drug);
//            this.listDetailPlanTam.add(detailPlan);
//        }
//        response.sendRedirect("/Plan/index");
//    }
//
//
//    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        try {
//            List<String> ghichu = Arrays.asList(request.getParameterValues("ghiChu"));
//            for (DetailPlan o : this.listDetailPlanTam) {
//                for (String g : ghichu) {
//                    if (this.listDetailPlanTam.indexOf(o) == ghichu.indexOf(g)) {
//                        o.setIdPlan();
//                    }
//                }
//            }
//            Plan plan = new Plan();
//            String maShop = request.getParameter("shop");
//            Shop shop = this.shopDao.findById(Integer.parseInt(maShop));
//            plan.setIdCuaHang(shop);
//            plan.setName();
//            THoaDonEntity soHD = this.hoaDonDao.create(hoaDon);
//            for (TChiTienHoaDonEntity o : this.listHDTam) {
//                o.setSoHoaDon(soHD);
//                this.chiTietHoaDonDao.create(o);
//            }
//            session.setAttribute("message", "Thêm Mới Thành Công");
//            this.listHDTam.clear();
//            response.sendRedirect("/receipt/index");
//        } catch (Exception e) {
//            session.setAttribute("error", "Thêm Mới Thất Bại");
//            response.sendRedirect("/receipt/index");
//            e.printStackTrace();
//        }
//    }
//
//    private void sup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String strid = request.getParameter("id");
//        int id = Integer.parseInt(strid);
//        int check = 0;
//        TChiTienHoaDonEntity entity = new TChiTienHoaDonEntity();
//        if (listHDTam.size() > 0) {
//            for (TChiTienHoaDonEntity ct : this.listHDTam) {
//                if (ct.getMaSach().getMaSach() == id) {
//                    ct.setSoLuong(ct.getSoLuong() - 1);
//                    if (ct.getSoLuong() <= 0) {
//                        check++;
//                        entity = ct;
//                    }
//                }
//            }
//        }
//        if (check != 0) {
//            this.listHDTam.remove(entity);
//        }
//        response.sendRedirect("/receipt/index");
//    }
//
//    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String s = request.getParameter("id");
//        int id = Integer.parseInt(s);
//        try {
//            THoaDonEntity entity = this.hoaDonDao.findByID(id);
//            List<TChiTienHoaDonEntity> listCT = entity.getEntityList();
//            this.chiTietHoaDonDao.deleteList(listCT);
//            this.hoaDonDao.delete(entity);
//            session.setAttribute("message", "Xóa Thành Công");
//            response.sendRedirect("/receipt/index");
//        } catch (Exception e) {
//            session.setAttribute("error", "Xóa Thất Bại");
//            response.sendRedirect("/receipt/index");
//            e.printStackTrace();
//        }
//    }
//}
