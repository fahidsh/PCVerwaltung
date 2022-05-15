package de.fahid.java.pc_verwaltung;

import de.fahid.java.LanguageManager;
import de.fahid.java.datenbank.DbConnector;
import de.fahid.java.datenbank.DbConnectorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class DbAdapter {
    private Connection dbConnection = null;
    private DbConnector dbConnector = null;
    private static final String DATABASE_NAME = "pcverwaltung";
    public static final String CPU_SCHLUSSEL = "takt";
    public static final String RAM_SCHLUSSEL = "ram";
    public static final String HDD_SCHLUSSEL = "hdd";
    public static final String ID_SCHLUSSEL = "id";

    private NumberFormat EnglishNumber = NumberFormat.getInstance(Locale.US);

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
                pc.setId(resultSet.getInt(ID_SCHLUSSEL));
                pcs.addPC(pc);
            }

            resultSet.close();
        } catch (SQLException ex) {
            throw new PCVerwaltungException(LanguageManager.getString("DatabaseQueryError"), ex);
        }
        return pcs;
    }

    public boolean updatePc(PC pc) throws PCVerwaltungException {
        boolean isUpdated = false;
        try {
            String sql = String.format("update tblpcs set takt=%s, ram=%s, hdd=%s where id=%d;",
                    EnglishNumber.format( pc.getTaktfrequenz() ),
                    EnglishNumber.format( pc.getRam() ),
                    EnglishNumber.format( pc.getHdd() ),
                    pc.getId());
            System.out.println(sql);
            dbConnector.execute(sql);
            isUpdated = true;
        } catch (DbConnectorException ex) {
            throw new PCVerwaltungException(ex);
        }
        return isUpdated;
    }

    public boolean addPc(PC pc) throws PCVerwaltungException {
        boolean isInserted = false;
        try {
            String sql = String.format("insert into tblpcs(takt, ram, hdd) values(%s, %s, %s);",
                    EnglishNumber.format( pc.getTaktfrequenz() ),
                    EnglishNumber.format( pc.getRam() ),
                    EnglishNumber.format( pc.getHdd() ));

            dbConnector.execute(sql);
            isInserted = true;
        } catch (DbConnectorException ex) {
            throw new PCVerwaltungException(ex);
        }
        return isInserted;
    }
}
