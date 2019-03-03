package dao;

import entity.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/school_db_newnew?useTimezone=true&serverTimezone=GMT";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String GET_ALL_STUDENTS_QUERY =
            "SELECT * FROM student;";

    private static final String INSERT_STUDENT_QUERY =
            "INSERT INTO student (name, surname) VALUES(?,?);";

    public static final String DELETE_STUDENT_QUERY = "DELETE FROM student WHERE id = ?;";


    private Connection conn;

    public DatabaseDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
		}
    }

    public ArrayList<Student> readAllStudentsFromDB() {
        ArrayList<Student> result = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(GET_ALL_STUDENTS_QUERY)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("name");
                String lastName = rs.getString("surname");
                String patron = rs.getString("patron");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                Student student = new Student(firstName, lastName, patron, birthday);
                student.setId(id);
                result.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void insertStudentIntoDB(String name, String surname) {
        try (PreparedStatement stmt =
                     conn.prepareStatement(INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, name);
            stmt.setString(2, surname);

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudentFromDB(int id) {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE_STUDENT_QUERY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}