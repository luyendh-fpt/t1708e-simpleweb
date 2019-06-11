package simpleweb.controller;

import simpleweb.entity.Student;
import simpleweb.model.StudentModel;
import simpleweb.util.ApplicationConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AdminController extends HttpServlet {

    private static StudentModel model = new StudentModel();
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("Admin area!");
    }
}
