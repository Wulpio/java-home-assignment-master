package com.wulp.assignment.repository;

import com.wulp.assignment.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String URL = "jdbc:h2:mem:assignment";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa";

    public Employee findEmployeeById(Integer id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT E.*, P.NAME AS PERSON_NAME, P.AGE, D.NAME AS DEPARTMENT_NAME " +
                    "FROM EMPLOYEE E " +
                    "JOIN PERSON P ON E.PERSON_ID = P.ID " +
                    "JOIN DEPARTMENT D ON E.DEPARTMENT_ID = D.ID " +
                    "WHERE E.PERSON_ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Instant startDate = resultSet.getTimestamp("START_DATE").toInstant();
                        Instant endDate = resultSet.getTimestamp("END_DATE") != null ?
                                resultSet.getTimestamp("END_DATE").toInstant() : null;
                        String personName = resultSet.getString("NAME");
                        int personAge = resultSet.getInt("AGE");
                        String departmentName = resultSet.getString("DEPARTMENT_NAME");

                        return new Employee(id, personName, personAge, departmentName, startDate, endDate);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Employee> findActiveEmployees() {
        List<Employee> activeEmployees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT E.*, P.NAME AS PERSON_NAME, P.AGE, D.NAME AS DEPARTMENT_NAME " +
                    "FROM EMPLOYEE E " +
                    "JOIN PERSON P ON E.PERSON_ID = P.ID " +
                    "JOIN DEPARTMENT D ON E.DEPARTMENT_ID = D.ID " +
                    "WHERE E.END_DATE IS NULL";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                mapperDB(activeEmployees, preparedStatement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.copyOf(activeEmployees);
    }

    public List<Employee> findActiveEmployeesByDepartment(String departmentName) {
        List<Employee> activeEmployees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT E.*, P.NAME AS PERSON_NAME, P.AGE, D.NAME AS DEPARTMENT_NAME " +
                    "FROM EMPLOYEE E " +
                    "JOIN PERSON P ON E.PERSON_ID = P.ID " +
                    "JOIN DEPARTMENT D ON E.DEPARTMENT_ID = D.ID " +
                    "WHERE E.END_DATE IS NULL " +
                    "AND D.NAME = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, departmentName);

                mapperDB(activeEmployees, preparedStatement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.copyOf(activeEmployees);
    }

    private void mapperDB(List<Employee> activeEmployees, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("PERSON_ID");
                Instant startDate = resultSet.getTimestamp("START_DATE").toInstant();
                Instant endDate = resultSet.getTimestamp("END_DATE") != null ?
                        resultSet.getTimestamp("END_DATE").toInstant() : null;
                String personName = resultSet.getString("PERSON_NAME");
                int personAge = resultSet.getInt("AGE");
                String departmentNameResult = resultSet.getString("DEPARTMENT_NAME");

                Employee employee = new Employee(id, personName, personAge, departmentNameResult, startDate, endDate);
                activeEmployees.add(employee);
            }
        }
    }


}
