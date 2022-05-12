package de.fahid.java.pc_verwaltung;

import java.util.ArrayList;

public class PCListe {
    private ArrayList<PC> allePCs = new ArrayList<>();

    public PC getPC(int index){
        PC einPc=null;
        if (index>=0 && index < allePCs.size()) {
            einPc = allePCs.get(index);
        }
        return einPc;
    }

    public void addPC(PC pc){
        if (pc!=null) {
            allePCs.add(pc);
        }
    }

    public int getIndex(PC pc) {
        int index = -1;
        if (pc!=null && allePCs.contains(pc)) {
            index = allePCs.indexOf(pc);
        }
        return index;
    }

    public void addPC(double hdd, double takt,double ram){
        //ein neues PC Objekt erstellen
        PC neuPC = new PC();

        //Parameter zuweisen
        neuPC.setHdd(hdd);
        neuPC.setRam(ram);
        neuPC.setTaktfrequenz(takt);

        //in Arraylist aufnehmen
        //"this"(diese) bezieht sich immer auf die eigene Klasse
        //this.allePCs.add(neuPC);
        this.addPC(neuPC);
    }

    public void removePC(int index){
        if (index>=0 && index < allePCs.size()) {
            allePCs.remove(index);
        }
    }

    public int getAnzahl(){
        return allePCs.size();
    }
}
