package simpleweb.controller;

import simpleweb.entity.Student;
import simpleweb.model.StudentModel;
import simpleweb.util.ApplicationConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class UpdateProfileController extends HttpServlet {

    private static StudentModel studentModel = new StudentModel();
    private static final Logger LOGGER = Logger.getLogger(UpdateProfileController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute(ApplicationConstant.LOGGED_IN_USER);
        Student existStudent = studentModel.findByUsernameAndStatus(student.getUsername(), Student.Status.ACTIVE);
        if (existStudent == null) {
            req.setAttribute("code", HttpServletResponse.SC_NOT_FOUND);
            req.setAttribute("message", "Account is not exist or has been deleted!");
            req.setAttribute("content", "Bla bla...!");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("student", existStudent);
        req.setAttribute("roles", Student.Role.values());
        req.getRequestDispatcher("/student/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute(ApplicationConstant.LOGGED_IN_USER);
        if (student == null) {
            req.setAttribute("code", HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("message", "Please login to continue!");
            req.setAttribute("content", "Bla bla...!");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        Student existStudent = studentModel.findByUsernameAndStatus(student.getUsername(), Student.Status.ACTIVE);
        if (existStudent == null) {
            req.setAttribute("code", HttpServletResponse.SC_NOT_FOUND);
            req.setAttribute("message", "Account is not exist or has been deleted!");
            req.setAttribute("content", "Bla bla...!");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

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
        
        existStudent.setFullName(fullName);
        existStudent.setEmail(email);
        existStudent.setAddress(address);
        existStudent.setRole(Student.Role.findByValue(role));
        existStudent.setPhone(phone);
        if (studentModel.update(existStudent)) {
            resp.sendRedirect("/member/profile");
        } else {
            req.setAttribute("student", student);
            req.setAttribute("roles", Student.Role.values());
            req.getRequestDispatcher("/student/form.jsp").forward(req, resp);
        }
    }
}
