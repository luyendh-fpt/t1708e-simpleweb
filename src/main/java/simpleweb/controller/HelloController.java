package simpleweb.controller;

import simpleweb.entity.Student;
import simpleweb.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class HelloController extends HttpServlet {

    private static StudentModel model = new StudentModel();
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = (Student) request.getSession().getAttribute("currentLoggedIn");
        if (student != null) {
            LOGGER.info("Logged in with username: " + student.getUsername());
        } else {
            LOGGER.info("Not logged in yet!");
        }
        request.setAttribute("list", model.findAllByStatus(Student.Status.ACTIVE));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
