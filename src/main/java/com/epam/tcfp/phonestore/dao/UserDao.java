package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.User;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(UserDao.class.getName());
    private static final String INSERT_USER =
            "INSERT INTO user (first_name, second_name, email,phone_number, address, password)" +
                    " VALUES (?, ?, ?, ?, ?, ?);";
    private static final String GET_USER_BY_EMAIL =
            "SELECT * from user WHERE email = ?;";

    private static final String SELECT_USER =
            "SELECT * FROM user WHERE id = ?;";
    public static final String SELECT_ALL_USERS =
            "SELECT * FROM user;";
    private static final String INSERT_USER_WITH_ROLE =
            "INSERT INTO user(first_name, second_name, email,phone_number, role, address, password)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_USER =
            "DELETE FROM user WHERE id=?;";
    private static final String UPDATE_USER =
            "UPDATE user SET first_name = ?,second_name = ?, email= ?, phone_number =?, role = ?, " +
                    "address = ?, password = ? where id = ?;";

//TODO убрать логику result >=1 если execute update > 0 то тру иначе фолз
    public boolean registerUser(User user) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPassword());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::registerUser. full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::registerUser in finally block. full stack trace follows:", e);

            }
        }
        return result;
    }

    public boolean isRegisteredCheck(String email) {
        boolean isRegistered = true;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_USER_BY_EMAIL);
            statement.setString(1, email);
            rs = statement.executeQuery();
            if(rs == null){
                    isRegistered = false;
            }
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::isRegisteredCheck. full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::isRegisteredCheck in finally block . full stack trace follows:", e);
            }
        }
        return isRegistered;
    }

    public User getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_USER_BY_EMAIL);
            statement.setString(1, email);
            rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(Constants.ID));
                user.setFirstName(rs.getString(Constants.FIRST_NAME));
                user.setSecondName(rs.getString(Constants.SECOND_NAME));
                user.setEmail(rs.getString(Constants.EMAIL));
                user.setPassword(rs.getString(Constants.PASSWORD));
                user.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                user.setRole(rs.getInt(Constants.ROLE));
                user.setAddress(rs.getString(Constants.ADDRESS));
            }
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::getUserByEmail . full stack trace follows:", e);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::getUserByEmail in finally block . full stack trace follows:", e);

            }
        }
        return user;
    }

    public void insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_WITH_ROLE);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::insertUser . full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::insertUser in finally block. full stack trace follows:", e);

            }
        }
    }

    public void deleteUser(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::deleteUser . full stack trace follows:", e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::deleteUser in finally block . full stack trace follows:", e);

            }
        }
    }
    public void updateUser(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setInt(8, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::updateUser . full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::updateUser in finally block . full stack trace follows:", e);
            }
        }
    }

    public User selectUser(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_USER);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(Constants.ID));
                user.setFirstName(rs.getString(Constants.FIRST_NAME));
                user.setSecondName(rs.getString(Constants.SECOND_NAME));
                user.setEmail(rs.getString(Constants.EMAIL));
                user.setPassword(rs.getString(Constants.PASSWORD));
                user.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                user.setRole(rs.getInt(Constants.ROLE));
                user.setAddress(rs.getString(Constants.ADDRESS));
            }
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::selectUser . full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::selectUser in finally block. full stack trace follows:", e);
            }
        }
        return user;
    }

    public List<User> selectAllUsers(){
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_USERS);
            rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(Constants.ID));
                user.setFirstName(rs.getString(Constants.FIRST_NAME));
                user.setSecondName(rs.getString(Constants.SECOND_NAME));
                user.setEmail(rs.getString(Constants.EMAIL));
                user.setPassword(rs.getString(Constants.PASSWORD));
                user.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                user.setRole(rs.getInt(Constants.ROLE));
                user.setAddress(rs.getString(Constants.ADDRESS));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("SQL exception in UserDao::selectAllUsers . full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in UserDao::selectAllUsers in finally block. full stack trace follows:", e);
            }
        }
        return users;
    }
}
