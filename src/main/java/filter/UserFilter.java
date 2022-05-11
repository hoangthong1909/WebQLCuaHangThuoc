package filter;

import entitys.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName="filter1",urlPatterns = {"/User/*", "/Drug/*", "/Bill/*", "/Plan/*", "/Shop/*","/Warehouse/*"})
public class UserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        User user = (User) session.getAttribute("sessionUser");
        if (user == null ) {
            httpRes.sendRedirect("/login");
            return;
        }else {

        }
        chain.doFilter(request, response);
    }
}
