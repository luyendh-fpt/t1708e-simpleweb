package simpleweb.model;

import simpleweb.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudentModel {

    private static final Logger LOGGER = Logger.getLogger(StudentModel.class.getName());

    public Student findByUsernameAndStatus(String username, Student.Status status) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            String sql = "select * from students where status = ? and username = ?";
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, status.getValue());
            preparedStatement.setString(2, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");
                String rsEmail = rs.getString("email");
                String rsSalt = rs.getString("salt");
                String rsAddress = rs.getString("address");
                String rsPhone = rs.getString("phone");
                int rsRole = rs.getInt("role");
                int rsStatus = rs.getInt("status");
                Student student = new Student();
                student.setUsername(rsUsername);
                student.setPassword(rsPassword);
                student.setAddress(rsAddress);
                student.setEmail(rsEmail);
                student.setPhone(rsPhone);
                student.setSalt(rsSalt);
                Student.Role rl = Student.Role.findByValue(rsRole);
                student.setRole(rl);
                Student.Status stt = Student.Status.findByValue(rsStatus);
                student.setStatus(stt);
                return student;
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return null;
    }

    public ArrayList<Student> findAllByStatus(Student.Status status) {
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            Connection cnn = ConnectionHelper.getConnection();
            String sql = "select * from students where status = ?";
            PreparedStatement preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setInt(1, status.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");
                String rsEmail = rs.getString("email");
                String rsAddress = rs.getString("address");
                String rsPhone = rs.getString("phone");
                int rsRole = rs.getInt("role");
                int rsStatus = rs.getInt("status");
                Student student = new Student();
                student.setUsername(rsUsername);
                student.setPassword(rsPassword);
                student.setAddress(rsAddress);
                student.setEmail(rsEmail);
                student.setPhone(rsPhone);
                Student.Role rl = Student.Role.findByValue(rsRole);
                student.setRole(rl);
                Student.Status stt = Student.Status.findByValue(rsStatus);
                student.setStatus(stt);
                list.add(student);
            }
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return list;
    }

    public boolean save(Student student) {
        try {
            Connection cnn = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = cnn.prepareStatement("insert into students " +
                    "(username, password, salt, email, fullName, address, phone, role, status) " +
                    "values (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getSalt());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getFullName());
            preparedStatement.setString(6, student.getAddress());
            preparedStatement.setString(7, student.getPhone());
            preparedStatement.setInt(8, student.getRole());
            preparedStatement.setInt(9, student.getStatus());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        StudentModel model = new StudentModel();
        Student st = model.findByUsernameAndStatus("xuanhung1234567", Student.Status.ACTIVE);
        System.out.println(st.getUsername());
    }
}
