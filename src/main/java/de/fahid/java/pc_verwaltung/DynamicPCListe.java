package de.fahid.java.pc_verwaltung;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DynamicPCListe {
    DbAdapter dbAdapter = null;
    public DynamicPCListe(DbAdapter dbAdapter) throws PCVerwaltungException {
        this.dbAdapter = dbAdapter;
        aktualisierePcIds();
        idListUpdater();
    }

    List<Integer> pcIds = new ArrayList<>();

    private void aktualisierePcIds() throws PCVerwaltungException {
        pcIds = dbAdapter.getAlleIds();
    }

    private void idListUpdater() throws PCVerwaltungException {
        Timer timer = new Timer();
        int delay = 60000; // 1 Minute = 60 Sekunden
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    aktualisierePcIds();
                } catch (PCVerwaltungException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }, 0, delay);
    }

    public PC getPC(int index) throws PCVerwaltungException {
        PC pc=null;
        if (index>=0 && index < pcIds.size()) {
            pc = dbAdapter.getPcById(pcIds.get(index));
        }
        return pc;
    }

    public void addPC(PC pc) throws PCVerwaltungException {
        if (pc!=null) {
            dbAdapter.insertPC(pc);
            aktualisierePcIds();
        }
    }

    public int getIndex(PC pc) {
        int index = -1;
        if (pc!=null && pcIds.contains(pc.getId())) {
            index = pcIds.indexOf(pc);
        }
        return index;
    }

    public void addPC(double hdd, double takt,double ram) throws PCVerwaltungException {
        PC neuPC = new PC();
        neuPC.setHdd(hdd);
        neuPC.setRam(ram);
        neuPC.setTaktfrequenz(takt);
        this.addPC(neuPC);
    }

    public void removePC(int index) throws PCVerwaltungException {
        if (index>=0 && index < pcIds.size()) {
            dbAdapter.deletePC(pcIds.get(index));
            aktualisierePcIds();
        }
    }

    public void updatePC(int id, PC newPC) throws PCVerwaltungException {
        if(newPC!=null && !isSamePC(newPC)) {
            dbAdapter.updatePc(newPC);
        }
        aktualisierePcIds();
    }

    public int getAnzahl(){
        return pcIds.size();
    }

    /**
     * Prüft, ob die übergebene PC-Instanz identisch ist, wie eine PC aus der Datenbank.
     * @param pc
     * @return boolean
     * @throws PCVerwaltungException
     */
    public boolean isSamePC(PC pc) throws PCVerwaltungException {
        boolean isSame = false;
        int index = pc.getId();
        if (index>=0) {
            PC oldPC = dbAdapter.getPcById(pc.getId());
            isSame = oldPC.getAusgabeString().equals(pc.getAusgabeString());
        }
        return isSame;
    }
}
