package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.entity.Phone;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class OrderPhoneDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(OrderPhoneDao.class.getName());
    private static final String INSERT_PRODUCT_LIST_TO_ORDER = "INSERT INTO orderphone (order_id, phone_id, quantity) VALUES (?, ?, ?);";


    public void insertProductListToOrder(Map<Phone, Integer> productList, int orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            for (Map.Entry<Phone, Integer> entry : productList.entrySet()) {
                Phone phone = entry.getKey();
                int quantity = entry.getValue();
                preparedStatement = connection.prepareStatement(INSERT_PRODUCT_LIST_TO_ORDER);
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, phone.getId());
                preparedStatement.setInt(3, quantity);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("SQL exception in OrderPhoneDao::insertProductListToOrder. full stack trace follows:", e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in OrderPhoneDao::insertProductListToOrder in finally block. full stack trace follows:", e);

            }
        }
    }
}
