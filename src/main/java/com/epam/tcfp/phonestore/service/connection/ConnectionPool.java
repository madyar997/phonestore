package com.epam.tcfp.phonestore.service.connection;
import com.epam.tcfp.phonestore.constants.Constants;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectionPool {
    private static final Logger log = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance = null;
    private List<Connection> availableConnections = new ArrayList<Connection>();
    private List<Connection>usedConnections = new ArrayList<Connection>();
    private final String driverName = "com.mysql.jdbc.Driver" ;
    private final String URL = "jdbc:mysql://localhost:3306/phonestore?useUnicode=true&amp;characterEncoding=utf8;autoReconnect=true&useSSL=false";
    private final String USERID = "admin";
    private final String PASSWORD = "mypass";


    private ConnectionPool() throws SQLException {
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
            e.printStackTrace();
        }
        return DriverManager.getConnection(this.URL, this.USERID, this.PASSWORD);
    }

    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con =
                    availableConnections.remove(
                            availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }

    public boolean releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }

    public int getFreeConnectionCount() {
        return availableConnections.size();
    }
}