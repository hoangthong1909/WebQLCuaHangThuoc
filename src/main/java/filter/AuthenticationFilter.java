package filter;


import entitys.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName="filter2",urlPatterns = {"/User/*"})
public class AuthenticationFilter implements Filter {

    public AuthenticationFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        User user = (User) session.getAttribute("sessionUser");
        if (user == null ) {
            httpRes.sendRedirect("/login");
            return;
        }else {
            if (user.getIsAdmin() == 2) {
                httpRes.sendRedirect("/Bill/index");
                return;
            }
        }
        chain.doFilter(request, response);
    }
    public void init(FilterConfig fConfig) throws ServletException {
    }

}