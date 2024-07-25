package jm.task.core.jdbc.service.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = getConnection();


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS USER (" +
                    "NAME VARCHAR(60)," +
                    "LASTNAME VARCHAR(60)," +
                    "AGE INT)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable()  {
        String query = "DROP TABLE IF EXISTS USER";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO USER (NAME, LASTNAME, AGE) VALUES ( ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> user = new ArrayList<>();
        String query = "SELECT NAME, LASTNAME, AGE FROM USER";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                User users = new User();
                users.setName(resultSet.getString("NAME"));
                users.setLastName(resultSet.getString("LASTNAME"));
                users.setAge(resultSet.getByte("AGE"));
                user.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM USER";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
