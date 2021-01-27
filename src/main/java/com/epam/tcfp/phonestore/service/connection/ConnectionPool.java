package com.epam.tcfp.phonestore.service.connection;
import com.epam.tcfp.phonestore.constants.Constants;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.*;


public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance = null;
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;
    private ResourceBundle properties = ResourceBundle.getBundle("connection");
    private final String driverName = "com.mysql.jdbc.Driver" ;
    private final String URL = "jdbc:mysql://localhost:3306/phonestore?useUnicode=true&amp;characterEncoding=utf8;autoReconnect=true&useSSL=false";
    private final String USERID = "madyar";
    private final String PASSWORD = "mdamdamda";

    private ConnectionPool() throws SQLException {
        availableConnections = new ArrayBlockingQueue<>(Constants.MAX_CONNECTIONS);
        usedConnections = new ArrayBlockingQueue<>(Constants.MAX_CONNECTIONS);

        for (int count = 0; count < Constants.MAX_CONNECTIONS; count++) {
            availableConnections.add(this.createConnection());
        }
    }

    public static ConnectionPool getInstance(){
        if(instance == null){
            synchronized (ConnectionPool.class){
                if(instance == null){
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException e) {
                        log.error("SQL exception in ConnectionPool::getInstance. full stack trace follows:", e);
                    }
                }
            }
        }
        return instance;
    }


    private Connection createConnection() throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            log.error("Class not found exception. full stack trace follows:", e);
        }
        return DriverManager.getConnection(this.URL, this.USERID, this.PASSWORD);
    }

    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            log.error("All connection in connection pool are used!");
            return null;
        } else {
            Connection con = null;
            try {
                con = availableConnections.take();
                usedConnections.add(con);
            } catch (InterruptedException e) {
                log.error("All connections in connection pool are used!");
            }
            return con;
        }
    }

    public void releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
        }
    }
}