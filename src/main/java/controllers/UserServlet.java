package controllers;

import Dao.UserDao;
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
@WebServlet({"/User/store", "/User/edit", "/User/update", "/User/delete", "/User/index", "/User/lock", "/User/unlock"})
public class UserServlet extends HttpServlet {
    private UserDao dao;

    public UserServlet() {
        this.dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        request.setAttribute("uri",1);
        String uri = request.getRequestURI();
        if (uri.contains("/User/index")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        request.setAttribute("uri",1);
        if (uri.contains("/User/store")) {
            this.store(request, response);
        } else if (uri.contains("/User/update")) {
            this.update(request, response);
        } else if (uri.contains("/User/edit")) {
            this.edit(request, response);
        } else if (uri.contains("/User/delete")) {
            this.delete(request, response);
        } else if (uri.contains("/User/lock")) {
            this.Lock(request, response);
        } else if (uri.contains("/User/unlock")) {
            this.UnLock(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listAlL(request, response);
        request.setAttribute("view", "/views/user/test.jsp");
        request.setAttribute("view1", "/views/user/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    protected void listAlL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("sessionUser");
        if (user.getIsAdmin() == 0) {
            List<User> list = this.dao.findAll();
            request.setAttribute("ds", list);
        } else if (user.getIsAdmin() == 1) {
            List<User> list = this.dao.findByUserLock(user.getId());
            request.setAttribute("ds", list);
        }
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            User before = this.dao.findById(id);
            User entity = new User();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setPassword(before.getPassword());
            entity.setStatus(before.getStatus());
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/User/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
            response.sendRedirect("/User/index");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            User entity = this.dao.findById(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(0);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/User/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/User/index");
        }
    }

    protected void Lock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            User entity = this.dao.findById(id);
            entity.setStatus(2);
            List<User> users = this.dao.findByUserLock(entity.getId());
            for (User u : users) {
                if (u.getUserAdd() == entity.getId()) {
                    u.setStatus(2);
                }
                this.dao.update(u);
            }
            this.dao.update(entity);
            session.setAttribute("message", "Khóa Tài Khoản Thành Công");
            response.sendRedirect("/User/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Khóa Tài Khoản Thất Bại");
            response.sendRedirect("/User/index");
        }
    }

    protected void UnLock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            User entity = this.dao.findById(id);
            entity.setStatus(1);
            List<User> users = this.dao.findByUserLock(entity.getId());
            for (User u : users) {
                if (u.getUserAdd() == entity.getId()) {
                    u.setStatus(1);
                }
                this.dao.update(u);
            }
            this.dao.update(entity);
            session.setAttribute("message", "Mở Khóa Tài Khoản Thành Công");
            response.sendRedirect("/User/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Mở Khóa Tài Khoản Thất Bại");
            response.sendRedirect("/User/index");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User entity = new User();
        List<User> list = new ArrayList<>();
        String phone = request.getParameter("sdt");
        User users = this.dao.findByPhone(phone);
        String email = request.getParameter("email");
        User users1 = this.dao.findByEmail(email);
        try {
            if (users != null) {
                session.setAttribute("error", "Số Điện Thoại Đã Tồn Tại");
                response.sendRedirect("/User/index");
                return;
            } else {
                if (users1 != null) {
                    session.setAttribute("error", "Email Đã Tồn Tại");
                    response.sendRedirect("/User/index");
                    return;
                } else {
                    BeanUtils.populate(entity, request.getParameterMap());
                    String encrypted = EncryptUtil.encrypt(request.getParameter("password"));
                    entity.setPassword(encrypted);
                    if (entity.getIsAdmin() == null) {
                        User userSession = (User) session.getAttribute("sessionUser");
                        entity.setIsAdmin(2);
                        entity.setUserAdd(userSession.getId());
                    }
                    entity.setStatus(1);
                    this.dao.create(entity);
                    session.setAttribute("message", "Thêm Mới Thành Công");
                    list.add(entity);
                    request.setAttribute("ds", list);
                    listAlL(request, response);
                    response.sendRedirect("/User/index");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        User entity = this.dao.findById(id);
        request.setAttribute("user", entity);
        listAlL(request, response);
        request.setAttribute("view", "/views/user/edit.jsp");
        request.setAttribute("view1", "/views/user/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
