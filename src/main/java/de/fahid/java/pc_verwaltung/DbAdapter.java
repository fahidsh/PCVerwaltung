package de.fahid.java.pc_verwaltung;

import de.fahid.java.LanguageManager;
import de.fahid.java.datenbank.DbConnector;
import de.fahid.java.datenbank.DbConnectorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public PCListe getAllePcs() throws PCVerwaltungException{
        PCListe pcs = new PCListe();
        try{
            String sql = "SELECT * FROM tblpcs;";
            Connection dbConnection = dbConnector.connect();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                PC pc = new PC();
                pc.setTaktfrequenz(resultSet.getDouble(CPU_SCHLUSSEL));
                pc.setRam(resultSet.getDouble(RAM_SCHLUSSEL));
                pc.setHdd(resultSet.getDouble(HDD_SCHLUSSEL));
                pc.setId(resultSet.getInt(ID_SCHLUSSEL));
                pcs.addPC(pc);
            }

            resultSet.close();
            preparedStatement.close();
            dbConnection.close();
        }  catch (SQLException | DbConnectorException ex) {
            throw new PCVerwaltungException(ex);
        }
        return pcs;
    }

    public boolean updatePc(PC pc) throws PCVerwaltungException {
        boolean isUpdated = false;
        try {
            String sql = "UPDATE tblpcs SET takt=?, ram=?, hdd=? where id=?;";
            Connection dbConnection = dbConnector.connect();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setDouble(1, pc.getTaktfrequenz());
            preparedStatement.setDouble(2, pc.getRam());
            preparedStatement.setDouble(3, pc.getHdd());
            preparedStatement.setInt(4, pc.getId());
            int result = preparedStatement.executeUpdate();
            System.out.println(preparedStatement.toString());
            preparedStatement.close();
            dbConnection.close();
            isUpdated = result > 0;
        } catch (SQLException | DbConnectorException ex) {
            throw new PCVerwaltungException(ex);
        }
        return isUpdated;
    }

    public boolean insertPC(PC pc) throws PCVerwaltungException {
        boolean isInserted = false;
        try {
            String sql = "INSERT INTO tblpcs(takt, ram, hdd) values(?, ?, ?);";
            Connection dbConnection = dbConnector.connect();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setDouble(1, pc.getTaktfrequenz());
            preparedStatement.setDouble(2, pc.getRam());
            preparedStatement.setDouble(3, pc.getHdd());
            int result = preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            preparedStatement.close();
            dbConnection.close();

            isInserted = result > 0;
        } catch (SQLException | DbConnectorException ex) {
            throw new PCVerwaltungException(ex);
        }
        return isInserted;
    }
}
