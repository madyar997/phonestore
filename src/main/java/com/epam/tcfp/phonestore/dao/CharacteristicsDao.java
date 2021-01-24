package com.epam.tcfp.phonestore.dao;

import com.epam.tcfp.phonestore.constants.Constants;
import com.epam.tcfp.phonestore.entity.Characteristics;
import com.epam.tcfp.phonestore.service.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacteristicsDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger log = Logger.getLogger(CharacteristicsDao.class.getName());
    private static final String SELECT_BY_ID = "SELECT * FROM characteristics WHERE id = ?;";
    private static final String UPDATE_CHARACTERISTICS = "UPDATE characteristics SET memory = ?, display = ?, screen_size = ?, camera = ?, front_camera =?," +
            "ram = ?, processor = ?, battery = ?, sizes = ?, weight = ? WHERE id = ?;";
    private static final String INSERT_CHARACTERISTICS =
            "INSERT INTO characteristics(memory, display, screen_size,camera, front_camera, ram, processor, battery, sizes, weight)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public Characteristics getCharacteristicsById(int characteristicId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Characteristics characteristics = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, characteristicId);
            rs = statement.executeQuery();
            while (rs.next()) {
                characteristics = new Characteristics();
                characteristics.setId(rs.getInt(Constants.ID));
                characteristics.setMemory(rs.getString(Constants.MEMORY));
                characteristics.setDisplay(rs.getString(Constants.DISPLAY));
                characteristics.setScreenSize(rs.getString(Constants.SCREEN_SIZE));
                characteristics.setCamera(rs.getString(Constants.CAMERA));
                characteristics.setFrontCamera(rs.getString(Constants.FRONT_CAMERA));
                characteristics.setRam(rs.getString(Constants.RAM));
                characteristics.setProcessor(rs.getString(Constants.PROCESSOR));
                characteristics.setBattery(rs.getString(Constants.BATTERY));
                characteristics.setSizes(rs.getString(Constants.SIZES));
                characteristics.setWeight(rs.getString(Constants.WEIGHT));
            }
        } catch (SQLException e) {
            log.error("SQL exception in CharacteristicsDao::getCharacteristicsById. full stack trace follows:", e);
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
                log.error("SQL exception in CharacteristicsDao::getCharacteristicsById in finally block. full stack trace follows:", e);
            }
        }
        return characteristics;
    }

    public void updateCharacteristics(Characteristics characteristics) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CHARACTERISTICS);
            preparedStatement.setString(1, characteristics.getMemory());
            preparedStatement.setString(2, characteristics.getDisplay());
            preparedStatement.setString(3, characteristics.getScreenSize());
            preparedStatement.setString(4, characteristics.getCamera());
            preparedStatement.setString(5, characteristics.getFrontCamera());
            preparedStatement.setString(6, characteristics.getRam());
            preparedStatement.setString(7, characteristics.getProcessor());
            preparedStatement.setString(8, characteristics.getBattery());
            preparedStatement.setString(9, characteristics.getSizes());
            preparedStatement.setString(10, characteristics.getWeight());
            preparedStatement.setInt(11, characteristics.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQL exception in CharacteristicsDao::updateCharacteristics. full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in CharacteristicsDao::updateCharacteristics in finally block. full stack trace follows:", e);
            }
        }
    }

    public void insertCharacteristics(Characteristics characteristics) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_CHARACTERISTICS, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, characteristics.getMemory());
            preparedStatement.setString(2, characteristics.getDisplay());
            preparedStatement.setString(3, characteristics.getScreenSize());
            preparedStatement.setString(4, characteristics.getCamera());
            preparedStatement.setString(5, characteristics.getFrontCamera());
            preparedStatement.setString(6, characteristics.getRam());
            preparedStatement.setString(7, characteristics.getProcessor());
            preparedStatement.setString(8, characteristics.getBattery());
            preparedStatement.setString(9, characteristics.getSizes());
            preparedStatement.setString(10, characteristics.getWeight());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    characteristics.setId((int) generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            log.error("SQL exception in CharacteristicsDao::insertCharacteristics. full stack trace follows:", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                connectionPool.releaseConnection(connection);
            } catch (SQLException e) {
                log.error("SQL exception in CharacteristicsDao::insertCharacteristics in finally clock. full stack trace follows:", e);
            }
        }
    }
}
