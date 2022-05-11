package controllers;

import Dao.drugDao;
import Dao.formdrugDao;
import Dao.typedrugDao;
import entitys.Drug;
import entitys.FormDrug;
import entitys.TypeDrug;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet({"/Drug/store", "/Drug/edit", "/Drug/update", "/Drug/delete", "/Drug/index"})
public class DrugServlet extends HttpServlet {
    private drugDao dao;
    private typedrugDao typedrugDao;
    private formdrugDao formdrugDao;

    public DrugServlet() {
        this.dao = new drugDao();
        this.typedrugDao = new typedrugDao();
        this.formdrugDao = new formdrugDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        request.setAttribute("uri",6);
        String uri = request.getRequestURI();
        if (uri.contains("/Drug/index")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        request.setAttribute("uri",6);
        if (uri.contains("/Drug/store")) {
            this.store(request, response);
        } else if (uri.contains("/Drug/update")) {
            this.update(request, response);
        } else if (uri.contains("/Drug/edit")) {
            this.edit(request, response);
        } else if (uri.contains("/Drug/delete")) {
            this.delete(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listALL(request, response);
        request.setAttribute("view", "/views/drug/create.jsp");
        request.setAttribute("view1", "/views/drug/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    protected void listALL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeDrug> dsTypeDrug=this.typedrugDao.findAll();
        request.setAttribute("dsTypeDrug",dsTypeDrug);
        List<FormDrug> dsFormDrug=this.formdrugDao.findAll();
        request.setAttribute("dsFormDrug",dsFormDrug);
        List<Drug> list = this.dao.findAll();
        request.setAttribute("ds", list);
    }


    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idType= Integer.parseInt(request.getParameter("typeDrugID"));
        int idForm= Integer.parseInt(request.getParameter("formDrugID"));
        String s = request.getParameter("id");
        try {
            TypeDrug typeDrug=this.typedrugDao.findById(idType);
            FormDrug formDrug=this.formdrugDao.findById(idForm);
            int id = Integer.parseInt(s);
            Drug before = this.dao.findById(id);
            Drug entity = new Drug();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(before.getStatus());
            entity.setIdType(typeDrug);
            entity.setIdForm(formDrug);
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Drug/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
            response.sendRedirect("/Drug/index");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        try {
            int id = Integer.parseInt(s);
            Drug entity = this.dao.findById(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(0);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Drug/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Drug/index");
        }
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idType= Integer.parseInt(request.getParameter("typeDrugID"));
        int idForm= Integer.parseInt(request.getParameter("formDrugID"));
        Drug entity = new Drug();
        List<Drug> list = new ArrayList<>();
        try {
            TypeDrug typeDrug=this.typedrugDao.findById(idType);
            FormDrug formDrug=this.formdrugDao.findById(idForm);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdType(typeDrug);
            entity.setIdForm(formDrug);
            entity.setStatus(1);
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("ds", list);
            List<Drug> all = this.dao.findAll();
            request.setAttribute("ds", all);
            response.sendRedirect("/Drug/index");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }
    }
    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listALL(request, response);
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        Drug entity = this.dao.findById(id);
        request.setAttribute("drug", entity);
        List<Drug> list = this.dao.findAll();
        request.setAttribute("ds", list);
        request.setAttribute("view", "/views/drug/edit.jsp");
        request.setAttribute("view1", "/views/drug/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
