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

public class ProfileController extends HttpServlet {

    private static StudentModel studentModel = new StudentModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.setAttribute("student", existStudent);
        req.getRequestDispatcher("/student/detail.jsp").forward(req, resp);
    }
}
