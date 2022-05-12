package de.fahid.java.datenbank;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DbConnector {
    public Connection connect() throws DbConnectorException;
    public void disconnect() throws DbConnectorException;
    public ResultSet getData(String sql) throws DbConnectorException;
    public void execute(String sql) throws DbConnectorException;
}