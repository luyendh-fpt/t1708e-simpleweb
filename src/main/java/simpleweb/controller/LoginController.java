package simpleweb.controller;

import simpleweb.entity.Student;
import simpleweb.model.StudentModel;
import simpleweb.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class LoginController extends HttpServlet {

    private static StudentModel studentModel = new StudentModel();
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/student/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        Student student = studentModel.findByUsernameAndStatus(username, Student.Status.ACTIVE);
        if (student == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("Not found");
            return;
        }
        if (StringUtil.comparePasswordWithSalt(password, student.getSalt(), student.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("currentLoggedIn", student);
            resp.sendRedirect("/hello");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.getWriter().println("Unauthorized!");
        return;
    }
}
