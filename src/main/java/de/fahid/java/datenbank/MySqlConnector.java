package de.fahid.java.datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnector implements DbConnector {
    //private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String dbBridge = "jdbc:mysql";
    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbName = "pcverwaltung";
    private String dbUser = "root";
    private String dbPass = "";

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    private Connection connection = null;

    private String getDbUrl() {
        return dbBridge +"://" + dbHost + ":" + dbPort + "/" + dbName;
    }

    @Override
    public Connection connect() throws DbConnectorException {
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(getDbUrl(), dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            throw new DbConnectorException(e);
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
        return connection;
    }

    @Override
    public void disconnect() throws DbConnectorException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbConnectorException(e);
        }
    }

    @Override
    public ResultSet getData(String sql) {
        return null;
    }

    @Override
    public void execute(String sql) {

    }
}
