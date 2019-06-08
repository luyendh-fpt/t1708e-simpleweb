package simpleweb.controller;

import simpleweb.entity.Student;
import simpleweb.model.StudentModel;
import simpleweb.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class RegisterController extends HttpServlet {

    private StudentModel model = new StudentModel();
    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Student.Role.values());
        req.getRequestDispatcher("/student/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        int role = Student.Role.USER.getValue();
        try {
            role = Integer.parseInt(req.getParameter("role"));
        } catch (NumberFormatException e) {
            LOGGER.severe(e.getMessage());
            return;
        }
        Student student = new Student();
        student.setUsername(username);
        student.hashPassword(password);
        student.setFullName(fullName);
        student.setEmail(email);
        student.setAddress(address);
        student.setRole(Student.Role.findByValue(role));
        student.setPhone(phone);
        model.save(student);
        resp.sendRedirect("/hello");
    }
}
