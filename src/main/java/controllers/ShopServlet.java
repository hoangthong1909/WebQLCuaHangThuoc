package controllers;

import Dao.ShopDao;
import Dao.UserDao;
import entitys.Shop;
import utils.EncryptUtil;
import entitys.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet({"/Shop/store", "/Shop/edit", "/Shop/update", "/Shop/delete", "/Shop/index"})
public class ShopServlet extends HttpServlet {
    private ShopDao shopDao;
    private UserDao userDao;

    public ShopServlet() {
        this.shopDao = new ShopDao();
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        request.setAttribute("uri",2);
        if (uri.contains("/Shop/index")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        request.setAttribute("uri",2);
        String uri = request.getRequestURI();
        if (uri.contains("/Shop/store")) {
            this.store(request, response);
        } else if (uri.contains("/Shop/update")) {
            this.update(request, response);
        } else if (uri.contains("/Shop/edit")) {
            this.edit(request, response);
        } else if (uri.contains("/Shop/delete")) {
            this.delete(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listALl(request, response);
        request.setAttribute("view", "/views/shop/create.jsp");
        request.setAttribute("view1", "/views/shop/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void listALl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        List<User> dsChuCH = this.userDao.findChuCH();
        List<Shop> list = this.shopDao.findAll();
        User t=new User();
        for (Shop s : list) {
            for (User u : dsChuCH) {
                if (s.getIdChuCH().getId()==u.getId()) {
                    t=u;
                }
            }
            dsChuCH.remove(t);
        }
        if (dsChuCH.isEmpty()){
            session.setAttribute("error","Vui Lòng Thêm Mới Chủ Cửa Hàng");
        }
        request.setAttribute("dsChuCH", dsChuCH);
        request.setAttribute("ds", list);
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int idChuCH = Integer.parseInt(request.getParameter("idchu"));
        try {
            User chuCH = this.userDao.findById(idChuCH);
            int id = Integer.parseInt(s);
            Shop before = this.shopDao.findById(id);
            Shop entity = new Shop();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdChuCH(chuCH);
            entity.setStatus(before.getStatus());
            this.shopDao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Shop/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
            response.sendRedirect("/Shop/index");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            Shop entity = this.shopDao.findById(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(0);
            this.shopDao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Shop/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Shop/index");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idChuCH = Integer.parseInt(request.getParameter("idchu"));
        Shop entity = new Shop();
        try {
            User chuCH = this.userDao.findById(idChuCH);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdChuCH(chuCH);
            entity.setStatus(1);
            this.shopDao.create(entity);
            listALl(request, response);
            session.setAttribute("message", "Thêm Mới Thành Công");
            response.sendRedirect("/Shop/index");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Shop/index");
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> dsChuCH = this.userDao.findChuCH();
        request.setAttribute("dsChuCH", dsChuCH);
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        Shop entity = this.shopDao.findById(id);
        request.setAttribute("shop", entity);
        List<Shop> list = this.shopDao.findAll();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/shop/edit.jsp");
        request.setAttribute("view1", "/views/shop/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
