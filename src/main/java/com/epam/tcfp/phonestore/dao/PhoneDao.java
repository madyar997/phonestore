package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.Phone;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class PhoneDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(PhoneDao.class.getName());
    private static final String SHOW_PHONES = "SELECT * FROM phone";
    private static final String SELECT_PHONE_BY_ID = "SELECT * FROM phone WHERE id = ?";
    private static final String SELECT_BRAND_BY_ID = "SELECT brand.brand_name FROM phone JOIN brand\n" +
            "ON phone.brand_id=brand.id where phone.id=?;";
    public static final String SELECT_CHARACTERISTICS_BY_PHONE_ID = "SELECT * FROM characteristics JOIN phone\n" +
            "ON phone.characteristics_id=characteristics.id WHERE phone.id=?;";
    public static final String UPDATE_PHONE  = "UPDATE phone SET brand_id = ?, model = ?, color = ?, model_year = ?, price =?," +
            "picture_path = ?, phone_description = ?, quantity = ? WHERE id = ?;";
    public static final String SELECT_CHARACTERISTICS_ID_BY_PHONE_ID = "SELECT characteristics_id FROM phone WHERE id = ?;";
    public static final String INSERT_PHONE =
            "INSERT INTO phone(brand_id, model, color,model_year, price, picture_path, phone_description, details, characteristics_id, quantity)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String DELETE_PHONE = "DELETE FROM phone WHERE id = ?";
    public static final String SELECT_PHONE_BY_BRAND_ID =
            "SELECT * FROM phone WHERE brand_id = ?;";
    public static final String SELECT_PHONE_BY_MODEL = "SELECT * FROM phone WHERE model = ?;";

    public Set<Phone> getPhones() {
        Set<Phone> phones = new LinkedHashSet<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SHOW_PHONES);
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt(Constants.ID));
                phone.setBrand(selectBrandById(rs.getInt(Constants.ID)));
                phone.setModel(rs.getString(Constants.MODEL));
                phone.setColor(rs.getString(Constants.COLOR));
                phone.setModelYear(rs.getInt(Constants.MODEL_YEAR));
                phone.setPrice(rs.getFloat(Constants.PRICE));
                phone.setPicture(rs.getString(Constants.PICTURE_PATH));
                phone.setCharacteristics(getCharacteristicsByPhoneId(rs.getInt(Constants.ID)));
                phone.setQuantity(rs.getInt(Constants.QUANTITY));
                //todo add description and details
                phones.add(phone);
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::getPhones. full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (statement != null) {statement.close();}
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::getPhones in finally block. full stack trace follows:", e);
            }
        }
        return phones;
    }

    public Phone selectPhoneById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Phone phone = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_PHONE_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                phone = new Phone();
                phone.setId(rs.getInt(Constants.ID));
                phone.setBrand(selectBrandById(rs.getInt(Constants.ID)));
                phone.setBrandId(rs.getInt(Constants.BRAND_ID));
                phone.setModel(rs.getString(Constants.MODEL));
                phone.setColor(rs.getString(Constants.COLOR));
                phone.setModelYear(rs.getInt(Constants.MODEL_YEAR));
                phone.setPrice(rs.getFloat(Constants.PRICE));
                phone.setPicture(rs.getString(Constants.PICTURE_PATH));
                phone.setDescription(rs.getString(Constants.PHONE_DESCRIPTION));
                phone.setDetails(rs.getString(Constants.DETAILS));
                phone.setCharacteristics(getCharacteristicsByPhoneId(rs.getInt(Constants.ID)));
                phone.setCharacteristicsId(rs.getInt(Constants.CHARACTERISTICS_ID));
                phone.setQuantity(rs.getInt(Constants.QUANTITY));
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::selectPhoneById. full stack trace follows:", e);

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
                log.error("SQL exception in PhoneDao::selectPhoneById in finally block. full stack trace follows:", e);
            }
        }
        return phone;
    }
    private String selectBrandById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String brand = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_BRAND_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                brand = rs.getString(Constants.BRAND_NAME);
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::selectBrandById. full stack trace follows:", e);

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
                log.error("SQL exception in PhoneDao::selectBrandById in finally block. full stack trace follows:", e);

            }
        }
        return brand;
    }

    private LinkedHashMap<String, String> getCharacteristicsByPhoneId(int id){
        LinkedHashMap<String, String> characteristics = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_CHARACTERISTICS_BY_PHONE_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                characteristics = new LinkedHashMap<>();
                characteristics.put("memory", rs.getString(Constants.MEMORY));
                characteristics.put("display", rs.getString(Constants.DISPLAY));
                characteristics.put("screenSize", rs.getString(Constants.SCREEN_SIZE));
                characteristics.put("camera", rs.getString(Constants.CAMERA));
                characteristics.put("frontCamera", rs.getString(Constants.FRONT_CAMERA));
                characteristics.put("ram", rs.getString(Constants.RAM));
                characteristics.put("processor", rs.getString(Constants.PROCESSOR));
                characteristics.put("battery", rs.getString(Constants.BATTERY));
                characteristics.put("sizes", rs.getString(Constants.SIZES));
                characteristics.put("weight", rs.getString(Constants.WEIGHT));
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::getCharacteristicsByPhoneId. full stack trace follows:", e);
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
                log.error("SQL exception in PhoneDao::getCharacteristicsByPhoneId in finally block. full stack trace follows:", e);
            }
        }
        return characteristics;
    }

    public void updatePhone(Phone phone){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PHONE);
            preparedStatement.setInt(1, phone.getBrandId());
            preparedStatement.setString(2, phone.getModel());
            preparedStatement.setString(3, phone.getColor());
            preparedStatement.setInt(4, phone.getModelYear());
            preparedStatement.setFloat(5, phone.getPrice());
            preparedStatement.setString(6, phone.getPicture());
            preparedStatement.setString(7, phone.getDescription());
            preparedStatement.setInt(8, phone.getQuantity());
            preparedStatement.setInt(9, phone.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::updatePhone . full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::updatePhone in finally block. full stack trace follows:", e);
            }
        }
    }

    public int getCharacteristicsIdByphoneId(int phoneId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        int id = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_CHARACTERISTICS_ID_BY_PHONE_ID);
            statement.setInt(1, phoneId);
            rs = statement.executeQuery();
            while (rs.next()) {
                id = rs.getInt(Constants.CHARACTERISTICS_ID);
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::getCharacteristicsIdByphoneId . full stack trace follows:", e);
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
                log.error("SQL exception in PhoneDao::getCharacteristicsIdByphoneId in finally block. full stack trace follows:", e);
            }
        }
        return id;
    }

    public void insertPhone(Phone phone){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PHONE);
            preparedStatement.setInt(1, phone.getBrandId());
            preparedStatement.setString(2, phone.getModel());
            preparedStatement.setString(3, phone.getColor());
            preparedStatement.setInt(4, phone.getModelYear());
            preparedStatement.setFloat(5, phone.getPrice());
            preparedStatement.setString(6, phone.getPicture());
            preparedStatement.setString(7, phone.getDescription());
            preparedStatement.setString(8, phone.getDetails());
            preparedStatement.setInt(9, phone.getCharacteristicsId());
            preparedStatement.setInt(10, phone.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::insertPhone . full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::insertPhone in finally block. full stack trace follows:", e);
            }
        }
    }

    public void deletePhoneById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_PHONE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::deletePhone . full stack trace follows:", e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::deletePhone in finally block . full stack trace follows:", e);
            }
        }
    }

    public Set<Phone> getPhonesByBrandId(int brandId){
        Set<Phone> phones = new LinkedHashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_PHONE_BY_BRAND_ID);
            statement.setInt(1, brandId);
            rs = statement.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt(Constants.ID));
                phone.setBrand(selectBrandById(rs.getInt(Constants.ID)));
                phone.setModel(rs.getString(Constants.MODEL));
                phone.setColor(rs.getString(Constants.COLOR));
                phone.setModelYear(rs.getInt(Constants.MODEL_YEAR));
                phone.setPrice(rs.getFloat(Constants.PRICE));
                phone.setPicture(rs.getString(Constants.PICTURE_PATH));
                phone.setCharacteristics(getCharacteristicsByPhoneId(rs.getInt(Constants.ID)));
                phone.setQuantity(rs.getInt(Constants.QUANTITY));
                //todo add description and details
                phones.add(phone);
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::getPhonesByBrandId . full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (statement != null) {statement.close();}
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::getPhonesByBrandId in finally block. full stack trace follows:", e);
            }
        }
        return phones;
    }

    public Set<Phone> getPhoneByModel(String model){
        Set<Phone> phones = new LinkedHashSet<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_PHONE_BY_MODEL);
            statement.setString(1, model);
            rs = statement.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt(Constants.ID));
                phone.setBrand(selectBrandById(rs.getInt(Constants.ID)));
                phone.setModel(rs.getString(Constants.MODEL));
                phone.setColor(rs.getString(Constants.COLOR));
                phone.setModelYear(rs.getInt(Constants.MODEL_YEAR));
                phone.setPrice(rs.getFloat(Constants.PRICE));
                phone.setPicture(rs.getString(Constants.PICTURE_PATH));
                phone.setCharacteristics(getCharacteristicsByPhoneId(rs.getInt(Constants.ID)));
                phone.setQuantity(rs.getInt(Constants.QUANTITY));
                //todo add description and details
                phones.add(phone);
            }
        } catch (SQLException e) {
            log.error("SQL exception in PhoneDao::getPhoneByModel . full stack trace follows:", e);
        } finally {
            try {
                if (rs != null) {rs.close();}
                if (statement != null) {statement.close();}
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in PhoneDao::getPhoneByModel in finally block . full stack trace follows:", e);
            }
        }
        return phones;
    }

}

