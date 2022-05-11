package controllers;

import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet({"/Plan/index", "/Plan/remove", "/Plan/create", "/Plan/store", "/Plan/edit", "/Plan/update", "/Plan/delete", "/Plan/show", "/Plan/confirm", "/Plan/cancel", "/Plan/showDetail", "/Plan/updateSL"})
public class PlanServlet extends HttpServlet {
    private drugDao drugDao;
    private planDao planDao;
    private detailPlanDao detailPlanDao;
    private ShopDao shopDao;
    List<DetailPlan> listDetailPlanTam;

    public PlanServlet() {
        this.detailPlanDao = new detailPlanDao();
        this.drugDao = new drugDao();
        this.shopDao = new ShopDao();
        this.planDao = new planDao();
        this.listDetailPlanTam = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        request.setAttribute("uri",4);
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        } else if (uri.contains("remove")) {
            this.deleteHD(request, response);
        } else if (uri.contains("show")) {
            this.show(request, response);
        } else if (uri.contains("confirm")) {
            this.confirm(request, response);
        } else if (uri.contains("cancel")) {
            this.cancel(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("uri",4);
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        } else if (uri.contains("delete")) {
            delete(request, response);
        } else if (uri.contains("showDetail")) {
            showDetail(request, response);
        }else if (uri.contains("updateSL")) {
            this.updateSL(request, response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listAll(request, response);
        request.setAttribute("view", "/views/plan/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Drug> drugList = this.drugDao.findAll();
        request.setAttribute("drugList", drugList);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");
        if (user.getIsAdmin() == 1) {
            Shop shop = this.shopDao.findByIDchuCH(user.getId());
            List<Plan> listPlan = this.planDao.findByPlanCH(shop.getId());
            request.setAttribute("dsPlan", listPlan);
        } else if (user.getIsAdmin() == 2) {
            Shop shop = this.shopDao.findByIDchuCH(user.getUserAdd());
            List<Plan> listPlan = this.planDao.findByPlanCH(shop.getId());
            request.setAttribute("dsPlan", listPlan);
        }
        request.setAttribute("listDetailPlanTam", this.listDetailPlanTam);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check = 0;
        if (listDetailPlanTam.size() > 0) {
            for (DetailPlan ct : this.listDetailPlanTam) {
                if (ct.getIdDrug().getId() == id) {
                    check++;
                    ct.setQuantity(ct.getQuantity() + 1);
                }
            }
        }
        if (check == 0) {
            DetailPlan detailPlan = new DetailPlan();
            Drug drug = this.drugDao.findById(id);
            detailPlan.setQuantity(1);
            detailPlan.setIdDrug(drug);
            this.listDetailPlanTam.add(detailPlan);
        }
        response.sendRedirect("/Plan/index");
    }

    private void deleteHD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        if (listDetailPlanTam.size() > 0) {
            for (DetailPlan ct : this.listDetailPlanTam) {
                if (ct.getIdDrug().getId() == id) {
                    listDetailPlanTam.remove(ct);
                    break;
                }
            }
        }
        if (listDetailPlanTam.isEmpty()) {
            listDetailPlanTam.clear();
        }
        response.sendRedirect("/Plan/index");
    }


    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            Plan plan = new Plan();
            User chuShop = (User) session.getAttribute("sessionUser");
            Shop shop = this.shopDao.findByIDchuCH(chuShop.getId());
            BeanUtils.populate(plan, request.getParameterMap());
            plan.setIdCuaHang(shop);
            plan.setStatus(1);
            Plan soHD = this.planDao.create(plan);
            for (DetailPlan o : this.listDetailPlanTam) {
                o.setIdPlan(soHD);
                this.detailPlanDao.create(o);
            }
            session.setAttribute("message", "Thêm Mới Thành Công");
            this.listDetailPlanTam.clear();
            response.sendRedirect("/Plan/index");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Plan/index");
            e.printStackTrace();
        }
    }


    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            Plan entity = this.planDao.findById(id);
            List<DetailPlan> listCT = entity.getEntityList();
            this.detailPlanDao.deleteList(listCT);
            this.planDao.delete(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Plan/index");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Plan/index");
            e.printStackTrace();
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double tong = 0;
        List<Plan> planList = this.planDao.findAll();
        for (Plan plan : planList) {
            List<DetailPlan> detailPlans = this.detailPlanDao.findByIDPlan(plan.getId());
            for (DetailPlan detail : detailPlans) {
                tong += detail.getQuantity() * detail.getIdDrug().getPrice();
            }
        }
        request.setAttribute("tong", tong);
        request.setAttribute("planList", planList);
        request.setAttribute("view", "/views/plan/show.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<DetailPlan> detailPlans = this.detailPlanDao.findByIDPlan(id);
        request.setAttribute("detailPlans", detailPlans);
        request.setAttribute("view", "/views/plan/detail.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void updateSL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("soLuong");
        int id = Integer.parseInt(request.getParameter("id"));
        DetailPlan planDetail = this.detailPlanDao.findById(id);
        planDetail.setQuantity(Integer.parseInt(s));
        this.detailPlanDao.update(planDetail);
        response.sendRedirect("/Plan/showDetail");
    }

    protected void confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Plan plan = planDao.findById(id);
        plan.setStatus(2);
        try {
            this.planDao.update(plan);
            session.setAttribute("message", "Xác Nhận " + plan.getName() + " " + plan.getIdCuaHang().getName() + "Thành Công");
        } catch (Exception e) {
            session.setAttribute("error", "Xác Nhận " + plan.getName() + " " + plan.getIdCuaHang().getName() + "Thất Bại");
            e.printStackTrace();
        }
        response.sendRedirect("/Plan/show");
    }

    protected void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Plan plan = planDao.findById(id);
        plan.setStatus(3);
        try {
            this.planDao.update(plan);
            session.setAttribute("message", "Hủy " + plan.getName() + " " + plan.getIdCuaHang().getName() + "Thành Công");
        } catch (Exception e) {
            session.setAttribute("error", "Hủy " + plan.getName() + " " + plan.getIdCuaHang().getName() + "Thất Bại");
            e.printStackTrace();
        }
        response.sendRedirect("/Plan/show");
    }
}
