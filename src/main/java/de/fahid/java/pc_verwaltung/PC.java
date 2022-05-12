package de.fahid.java.pc_verwaltung;

import de.fahid.java.LanguageManager;

public class PC {
    private double taktfrequenz=0;
    private double ram=0;
    private double hdd=0;

    public double getTaktfrequenz() {
        return taktfrequenz;
    }

    public void setTaktfrequenz(double taktfrequenz) {
        this.taktfrequenz = taktfrequenz;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public double getHdd() {
        return hdd;
    }

    public void setHdd(double hdd) {
        this.hdd = hdd;
    }

    public double getLeistungsindex() {
        return taktfrequenz * ram * hdd;
    }

    public String getAusgabeString(){

//        String ausgabe="------------------------\n"+
//                "Prozessorgeschwindigkeit: "+taktfrequenz+" Ghz\n"+
//                "Arbeitsspeicher: "+ram+" GB\n"+
//                "Festplattenkapazität: "+hdd+" GB\n"+
//                "Leistungsindex: "+getLeistungsindex();

        String value = String.format("%s\n%-27s: %.1f Ghz\n%-27s: %.1f GB\n%-27s: %.1f GB\n%-27s: %.1f",
                "--------------------------------------",
                LanguageManager.getString("CPUSpeed"),
                taktfrequenz,
                LanguageManager.getString("RAM"),
                ram,
                LanguageManager.getString("HddCapacity"),
                hdd,
                LanguageManager.getString("PowerIndex"),
                getLeistungsindex());

        return value;
    }

}
