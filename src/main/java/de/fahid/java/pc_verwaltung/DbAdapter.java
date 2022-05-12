package de.fahid.java.pc_verwaltung;

import de.fahid.java.LanguageManager;
import de.fahid.java.datenbank.DbConnector;
import de.fahid.java.datenbank.DbConnectorException;
import de.fahid.java.datenbank.MySqlConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbAdapter {
    private Connection dbConnection = null;
    private DbConnector dbConnector = null;
    private static final String DATABASE_NAME = "pcverwaltung";
    public static final String CPU_SCHLUSSEL = "takt";
    public static final String RAM_SCHLUSSEL = "ram";
    public static final String HDD_SCHLUSSEL = "hdd";

    public DbAdapter(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public Connection getDbConnection() {
        if(dbConnection != null) {
            return dbConnection;
        }
        try {
            this.dbConnection = dbConnector.connect();
        } catch (DbConnectorException e) {
            throw new RuntimeException(e);
        }
        return dbConnection;
    }

    public PCListe getAllePcs() throws PCVerwaltungException{
        PCListe pcs = new PCListe();
        try{
            String sql = "SELECT * FROM tblpcs;";
            ResultSet resultSet = getDbConnection().createStatement().executeQuery(sql);
            //ResultSet resultSet = dbConnector.getData(sql);
            while(resultSet.next()){
                PC pc = new PC();
                pc.setTaktfrequenz(resultSet.getDouble(CPU_SCHLUSSEL));
                pc.setRam(resultSet.getDouble(RAM_SCHLUSSEL));
                pc.setHdd(resultSet.getDouble(HDD_SCHLUSSEL));
                pcs.addPC(pc);
            }
        } catch (SQLException e) {
            throw new PCVerwaltungException(LanguageManager.getString("DatabaseQueryError"), e);
        }
        return pcs;
    }

}
