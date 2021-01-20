package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.Brand;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(BrandDao.class.getName());
    private static final String SHOW_BRANDS = "SELECT id,brand_name FROM brand";
    private static final String SELECT_ID_BY_BRAND_NAME = "SELECT id FROM brand WHERE brand_name = ?;";
    List<Brand> brands = new ArrayList<>();

    public List<Brand> getBrandNames() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SHOW_BRANDS);
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt(Constants.ID));
                brand.setBrandName(rs.getString(Constants.BRAND_NAME));
                brands.add(brand);
            }
        } catch (SQLException e) {
            log.error("SQL exception in BrandDao::getBrandNames. full stack trace follows:", e);
        }finally {
            try {
                if (rs != null) {rs.close();}
                if (statement != null) {statement.close();}
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in BrandDao::getBrandNames in finally block. full stack trace follows:", e);
            }
        }

        return brands;
    }

    public int parseIdbyBrandName(String brand){
        int id = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ID_BY_BRAND_NAME);
            statement.setString(1, brand);
            rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(Constants.ID);
            }
        } catch (SQLException e) {
            log.error("SQL exception in BrandDao::parseIdbyBrandName. full stack trace follows:", e);
        }finally {
            try {
                if (rs != null) {rs.close();}
                if (statement != null) {statement.close();}
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in BrandDao::parseIdbyBrandName in finally block. full stack trace follows:", e);
            }
        }

        return id;
    }
}
