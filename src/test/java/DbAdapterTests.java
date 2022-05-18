import de.fahid.java.datenbank.DbConnectorException;
import de.fahid.java.datenbank.MySqlConnector;
import de.fahid.java.pc_verwaltung.DbAdapter;
import de.fahid.java.pc_verwaltung.PC;
import de.fahid.java.pc_verwaltung.PCVerwaltungException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DbAdapterTests {
    private MySqlConnector dbConnector = new MySqlConnector();
    private DbAdapter dbAdapter;

    @BeforeEach
    public void setUp() {
        dbAdapter = null;
        dbAdapter = new DbAdapter(dbConnector);
    }

    @Test
    public void testGetAllePcs() {
        try {
            System.out.println("PC Fetched: " + dbAdapter.getAllePcs().getAnzahl());
        } catch (PCVerwaltungException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testGetAlleIds() {
        try {
            System.out.println("IDs Fetched: " + dbAdapter.getAlleIds().size());
        } catch (PCVerwaltungException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testGetPcById() {
        try {
            System.out.println("PC Fetched: " + dbAdapter.getPcById(1).getAusgabeString());
        } catch (PCVerwaltungException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testGetPcById_NotFound() {
        try {
            dbAdapter.getPcById(9999).getId();
        } catch (PCVerwaltungException ex) {
            System.out.println(ex.getMessage());
            Assertions.assertEquals(ex.getMessage(), "Kein PC mit der ID 9999 gefunden!");
        } catch (NullPointerException ex) {
            Assertions.assertEquals(ex.getMessage(), null);
        }
    }

    @Test
    public void testUpdatePC() {
        try {
            PC pc = dbAdapter.getPcById(1);
            double originalTakt = pc.getTaktfrequenz();
            double newTakt = 123.45;
            pc.setTaktfrequenz(newTakt);
            dbAdapter.updatePc(pc);
            assertEquals(newTakt, dbAdapter.getPcById(1).getTaktfrequenz());
            pc.setTaktfrequenz(originalTakt);
            dbAdapter.updatePc(pc);
            assertEquals(originalTakt, dbAdapter.getPcById(1).getTaktfrequenz());
        } catch (PCVerwaltungException ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    public void testInsertPC() {
        try{
            PC pc = new PC();
            pc.setHdd(500);
            pc.setRam(16);
            pc.setTaktfrequenz(2.5);
            int anzahlPcs = dbAdapter.getAlleIds().size();
            dbAdapter.insertPC(pc);
            assertEquals(anzahlPcs + 1, dbAdapter.getAlleIds().size());
        } catch (PCVerwaltungException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testDeletePC() {
        try {
            int anzahlPcs = dbAdapter.getAlleIds().size();
            List<Integer> ids = dbAdapter.getAlleIds();
            int lastId = ids.get(ids.size() - 1);
            dbAdapter.deletePC(lastId);
            assertEquals(anzahlPcs - 1, dbAdapter.getAlleIds().size());
        } catch (PCVerwaltungException ex) {
            fail(ex.getMessage());
        }
    }
}
