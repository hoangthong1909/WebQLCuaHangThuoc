package controllers;

import Dao.UserDao;
import entitys.User;
import org.apache.commons.beanutils.BeanUtils;
import utils.EncryptUtil;
import utils.XCookie;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "loginServlet", value = {"/login","/register", "/signout", "/forgot", "/send"})
public class LoginServlet extends HttpServlet {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~=+%^*/()[]{}/!@#$?|";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    private static final String ALL = alpha + alphaUpperCase + digits + specials;
    private UserDao userDao;
    private static Random generator = new Random();
    String username = "vanthanhtvph15016@gmail.com";
    String messgare = "";

    public LoginServlet() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println((User) request.getSession().getAttribute("user"));
        if (request.getRequestURI().contains("signout")) {
            System.out.println("logout");
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("sessionUser");
            if (u != null) {
                XCookie.add(response, "sessionUser", "0", 0);
                request.getSession().removeAttribute("sessionUser");
                request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
            } else {
                response.getWriter().print("error");
                request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
            }
        } else if (request.getRequestURI().contains("forgot")){

            request.getRequestDispatcher("/views/account/forgotPassword.jsp").forward(request, response);
        }else {
            System.out.println("login");
            String remmeber = XCookie.get(request, "user_remmeber", null);
            if (remmeber != null && remmeber != "0") {
                request.setAttribute("sessionUser", userDao.findById(Integer.valueOf(remmeber)));
                request.setAttribute("sessionUser", (User) request.getSession().getAttribute("user_remmeber"));
            }
            request.setAttribute("pageTitle", "Login");
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String uri = request.getRequestURI();
        if (uri.contains("register")){
            User user = new User();
            try {
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (user == null) {
                request.setAttribute("result", "false");
                request.setAttribute("message", "Đăng ký thất bại! Code: -1");
                request.setAttribute("user", user);
            } else {
                System.out.println(user.getEmail());
                User u = userDao.findByEmail(user.getEmail());
                if (u != null) {
                    request.setAttribute("user", user);
                    request.setAttribute("result", "false");
                    if (u.getEmail().equalsIgnoreCase(user.getEmail()))
                        request.setAttribute("message", "Tài khoản đã tồn tại!");
                } else {
                    user.setIsAdmin(0);
                    try {
                        user.setPassword(EncryptUtil.encrypt(user.getPassword()));
                        user = userDao.create(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (user == null) {
                        request.setAttribute("result", "false");
                        request.setAttribute("message", "Đăng ký thất bại! Code: -1");
                        request.setAttribute("user", user);
                    } else {
                        request.setAttribute("message", "Đăng ký thành công, bạn hiện có thể đăng nhập ngay bây giờ ");
                    }
                }
            }
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
        }else if (uri.contains("send")){
            String email = request.getParameter("email");
            int numberOfCharactor = 5;
            String pass = randomAlphaNumeric(numberOfCharactor);
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            try {
                //Thông số để kết nối Smtp Server
                Properties props = new Properties();
                props.setProperty("mail.smtp.auth","true");
                props.setProperty("mail.smtp.starttls.enable","true");
                props.setProperty("mail.smtp.host","smtp.gmail.com");
                props.put("mail.smtp.ssl.protocols", "TLSv1.2");
                props.setProperty("mail.smtp.port","587");
                //kết nối với Smtp Server
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String username = "anhthong645@gmail.com";
                        String password = "19092002thong";
                        return new PasswordAuthentication(username,password);
                    }
                });
                //Tạo messgare

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(this.username));
                message.setRecipients(Message.RecipientType.TO,request.getParameter("email"));
                message.setSubject("Email to reset password","utf-8");
                message.setText("Mật khẩu mới của bạn là :"+pass,"utf-8","html");
                message.setReplyTo(message.getFrom());
                //Gưởi message
                Transport.send(message);
                this.messgare="Gưởi thành công";
            } catch (MessagingException e) {
                this.messgare="lỗi rồi check lại đi";
                e.printStackTrace();
            }
            request.setAttribute("messgare",this.messgare);
            response.sendRedirect("/forgot");
        }
        else {
            User user = new User();
            try {
                BeanUtils.populate(user, request.getParameterMap());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            User u = userDao.findByEmail(user.getEmail());
            if (u == null || !EncryptUtil.check(user.getPassword(), u.getPassword())) {
                request.setAttribute("result", "error");
                request.setAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
                request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
            } else {
                if (u.getStatus()==1){
                    if (request.getParameter("remember") != null) {
                        XCookie.add(response, "user_remmeber",String.valueOf(u.getId()), 600);
                    }
                    request.getServletContext().setAttribute("sessionUser", u);
                    request.getSession().setAttribute("sessionUser", u);
                    request.setAttribute("result", "success");
                    request.setAttribute("message", "Đăng nhập thành công, bạn sẽ được di chuyển về trang chủ!");
                    request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
                }else {
                    request.setAttribute("result", "error");
                    request.setAttribute("message", "Tài khoản này đã bị xóa hoặc khóa rồi!");
                }

            }
        }
    }
    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
}