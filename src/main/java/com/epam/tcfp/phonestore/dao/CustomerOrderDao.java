package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.CustomerOrder;
import com.epam.tcfp.phonestore.entity.Phone;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerOrderDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(CustomerOrderDao.class.getName());
    private static final String INSERT_ORDER = "INSERT INTO customerOrder(customer_id, date, status, shipping_address, full_name, phone_number, city, zip, details, total_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM customerOrder WHERE customer_id = ?;";
    private static final String SELECT_ORDERS = "SELECT * FROM customerOrder;";
    private static final String SELECT_PRODUCTLIST_BY_ORDER_ID = "SELECT orderphone.phone_id, brand.brand_name, phone.model, phone.color, orderphone.quantity, phone.price FROM orderphone \n" +
            "JOIN customerorder on orderphone.order_id = customerorder.id\n" +
            "JOIN phone on orderphone.phone_id = phone.id\n" +
            "JOIN brand ON phone.brand_id = brand.id\n" +
            "where customerorder.id = ?;";
    private static final String DELETE_ORDER = "DELETE FROM customerorder WHERE id = ?;";
    private static final String SELECT_ORDER_BY_ID =
            "SELECT * FROM customerorder WHERE id = ?;";
    private static final String UPDATE_ORDER = "UPDATE customerorder SET full_name = ?, phone_number = ?, " +
            "shipping_address = ?, city = ?, zip = ?, total_cost = ?, date = ?, customer_id = ?, status = ?, details = ? WHERE id = ?;";

    public void insertOrder(CustomerOrder order){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setString(2, order.getOrderDateTime());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setString(4, order.getAddress());
            preparedStatement.setString(5, order.getFullName());
            preparedStatement.setString(6, order.getPhoneNumber());
            preparedStatement.setString(7, order.getCity());
            preparedStatement.setString(8, order.getZip());
            preparedStatement.setString(9, order.getDetails());
            preparedStatement.setFloat(10, order.getTotalCost());


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId((int) generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            log.error("SQL exception in CustomerOrderDao::insertOrder. full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in CustomerOrderDao::insertOrder in finally block. full stack trace follows:", e);

            }
        }
    }

    public Map<CustomerOrder, Map<Phone, Integer>> selectAllOrdersByUserId(int userId){
        Map<CustomerOrder, Map<Phone, Integer>> customerOrders = new HashMap<>();
        Map<Phone, Integer> productList = new HashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        CustomerOrder customerOrder = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID);
            statement.setInt(1, userId);
            rs = statement.executeQuery();
            while (rs.next()) {
                customerOrder = new CustomerOrder();
                customerOrder.setId(rs.getInt(Constants.ID));
                customerOrder.setCustomerId(rs.getInt(Constants.CUSTOMER_ID));
                customerOrder.setOrderDateTime(rs.getString(Constants.DATE));
                customerOrder.setStatus(rs.getString(Constants.STATUS));
                customerOrder.setAddress(rs.getString(Constants.SHIPPING_ADDRESS));
                customerOrder.setFullName(rs.getString(Constants.FULL_NAME));
                customerOrder.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                customerOrder.setCity(rs.getString(Constants.CITY));
                customerOrder.setZip(rs.getString(Constants.ZIP));
                customerOrder.setDetails(rs.getString(Constants.DETAILS));
                customerOrder.setTotalCost(rs.getFloat(Constants.TOTAL_COST));
                customerOrders.put(customerOrder, getProductListByOrderId(customerOrder.getId()));
            }
        } catch (SQLException e) {
            log.error("SQL exception in CustomerOrderDao::selectAllOrdersByUserId. full stack trace follows:", e);
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
                log.error("SQL exception in CustomerOrderDao::selectAllOrdersByUserId in finally block. full stack trace follows:", e);
            }
        }
        return customerOrders;
    }


    private Map<Phone, Integer> getProductListByOrderId(int orderId){
        Map<Phone, Integer> productList = new LinkedHashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_PRODUCTLIST_BY_ORDER_ID);
            statement.setInt(1, orderId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt(Constants.PHONE_ID));
                phone.setBrand(rs.getString(Constants.BRAND_NAME));
                phone.setModel(rs.getString(Constants.MODEL));
                phone.setColor(rs.getString(Constants.COLOR));
                phone.setPrice(rs.getFloat(Constants.PRICE));
                productList.put(phone, rs.getInt(Constants.QUANTITY));
            }
        } catch (SQLException e) {
            log.error("SQL exception in CustomerOrderDao::getProductListByOrderId. full stack trace follows:", e);
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
                log.error("SQL exception in CustomerOrderDao::getProductListByOrderId in finally block. full stack trace follows:", e);
            }
        }
        return productList;
    }

    public Map<CustomerOrder, Map<Phone, Integer>> selectAllOrders(){
        Map<CustomerOrder, Map<Phone, Integer>> customerOrders = new LinkedHashMap<>();
        Map<Phone, Integer> productList = new LinkedHashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        CustomerOrder customerOrder = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ORDERS);
            rs = statement.executeQuery();
            while (rs.next()) {
                customerOrder = new CustomerOrder();
                customerOrder.setId(rs.getInt(Constants.ID));
                customerOrder.setCustomerId(rs.getInt(Constants.CUSTOMER_ID));
                customerOrder.setOrderDateTime(rs.getString(Constants.DATE));
                customerOrder.setStatus(rs.getString(Constants.STATUS));
                customerOrder.setAddress(rs.getString(Constants.SHIPPING_ADDRESS));
                customerOrder.setFullName(rs.getString(Constants.FULL_NAME));
                customerOrder.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                customerOrder.setCity(rs.getString(Constants.CITY));
                customerOrder.setZip(rs.getString(Constants.ZIP));
                customerOrder.setDetails(rs.getString(Constants.DETAILS));
                customerOrder.setTotalCost(rs.getFloat(Constants.TOTAL_COST));
                customerOrders.put(customerOrder, getProductListByOrderId(customerOrder.getId()));
            }
        } catch (SQLException e) {
            log.error("SQL exception in CustomerOrderDao::selectAllOrdersByUserId. full stack trace follows:", e);
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
                log.error("SQL exception in CustomerOrderDao::selectAllOrdersByUserId in finally block. full stack trace follows:", e);
            }
        }
        return customerOrders;
    }
    public void deleteOrderById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ORDER);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in OrderDao::deleteOrder . full stack trace follows:", e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in OrderDao::deleteOrder in finally block . full stack trace follows:", e);

            }
        }
    }

    public CustomerOrder selectOrderById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        CustomerOrder order = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                order = new CustomerOrder();
                order.setId(rs.getInt(Constants.ID));
                order.setCustomerId(rs.getInt(Constants.CUSTOMER_ID));
                order.setOrderDateTime(rs.getString(Constants.DATE));
                order.setStatus(rs.getString(Constants.STATUS));
                order.setAddress(rs.getString(Constants.SHIPPING_ADDRESS));
                order.setFullName(rs.getString(Constants.FULL_NAME));
                order.setPhoneNumber(rs.getString(Constants.PHONE_NUMBER));
                order.setCity(rs.getString(Constants.CITY));
                order.setZip(rs.getString(Constants.ZIP));
                order.setDetails(rs.getString(Constants.DETAILS));
                order.setTotalCost(rs.getFloat(Constants.TOTAL_COST));
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
        return order;
    }

    public void updateOrder(CustomerOrder order){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setString(1, order.getFullName());
            preparedStatement.setString(2, order.getPhoneNumber());
            preparedStatement.setString(3, order.getAddress());
            preparedStatement.setString(4, order.getCity());
            preparedStatement.setString(5, order.getZip());
            preparedStatement.setFloat(6, order.getTotalCost());
            preparedStatement.setString(7, order.getOrderDateTime());
            preparedStatement.setInt(8, order.getCustomerId());
            preparedStatement.setString(9, order.getStatus());
            preparedStatement.setString(10, order.getDetails());
            preparedStatement.setInt(11, order.getId());
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
}
