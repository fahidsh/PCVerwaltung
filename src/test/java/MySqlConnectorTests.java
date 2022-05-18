import de.fahid.java.datenbank.DbConnectorException;
import de.fahid.java.datenbank.MySqlConnector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

public class MySqlConnectorTests {

    @Test
    public void testConnection() {
        MySqlConnector mySqlConnector = new MySqlConnector();
        try {
            Connection connection = mySqlConnector.connect();
        } catch (DbConnectorException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testDisconnect() {
        MySqlConnector mySqlConnector = new MySqlConnector();
        try {
            Connection connection = mySqlConnector.connect();
            mySqlConnector.disconnect();
        } catch (DbConnectorException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    /**
     * Testet, ob die Methode getData() einen ResultSet zurückgibt.
     * der Methode ist in die Anwendung nicht verwendet worden, ist aber trotz getestet.
     * @throws DbConnectorException
     */
    public void testGetData() {
        MySqlConnector mySqlConnector = new MySqlConnector();
        try {
            Connection connection = mySqlConnector.connect();
            String sql = "SELECT id FROM tblpcs LIMIT 1;";
            ResultSet resultSet = mySqlConnector.getData(sql);
        } catch (DbConnectorException ex) {
            Assertions.fail(ex.getMessage());
        }
    }
}
