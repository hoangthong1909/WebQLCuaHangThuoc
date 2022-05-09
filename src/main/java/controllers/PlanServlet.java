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

@WebServlet({"/Plan/index", "/Plan/sup", "/Plan/create", "/Plan/store", "/Plan/edit", "/Plan/update", "/Plan/delete",})
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
        if (uri.contains("index")) {
            index(request, response);
        } else if (uri.contains("sup")) {
            sup(request, response);
        } else if (uri.contains("edit")) {
            edit(request, response);
        } else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            store(request, response);
        }  else if (uri.contains("delete")) {
            delete(request, response);
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        List<Drug> drugList = this.drugDao.findAll();
//        User chuShop= (User) session.getAttribute("sessionUser");
//        request.setAttribute("chuShop",chuShop);
        List<Shop> shopList = this.shopDao.findAll();
        List<Plan> listPlan = this.planDao.findAll();
        request.setAttribute("dsShop", shopList);
        request.setAttribute("drugList", drugList);
        request.setAttribute("dsPlan", listPlan);
        request.setAttribute("listDetailPlanTam", this.listDetailPlanTam);
        request.setAttribute("view1", "/views/plan/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check = 0;
        if (listDetailPlanTam.size() > 0) {
            for (DetailPlan ct : this.listDetailPlanTam) {
                if (ct.getIdDrug().getId() == id) {
                    check++;
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


    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            Plan plan = new Plan();
            User chuShop= (User) session.getAttribute("sessionUser");
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

    private void sup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strid = request.getParameter("id");
        int id = Integer.parseInt(strid);
        int check = 0;
        DetailPlan entity = new DetailPlan();
        if (listDetailPlanTam.size() > 0) {
            for (DetailPlan ct : this.listDetailPlanTam) {
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
            this.listDetailPlanTam.remove(entity);
        }
        response.sendRedirect("/Plan/index");
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
}