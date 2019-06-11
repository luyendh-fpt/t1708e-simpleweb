package simpleweb.filter;

import simpleweb.entity.Student;
import simpleweb.util.ApplicationConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute(ApplicationConstant.LOGGED_IN_USER);
        boolean authenticated = false;
        if (student != null && student.isMember()) {
            authenticated = false;
        }
        if (authenticated) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.setAttribute("code", HttpServletResponse.SC_UNAUTHORIZED);
            request.setAttribute("message", "Please login to continue!");
            request.setAttribute("content", "Bla bla...!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
