package de.fahid.java;

import de.fahid.java.datenbank.MySqlConnector;
import de.fahid.java.gui.GuiPcVerwaltung;
import de.fahid.java.pc_verwaltung.DbAdapter;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        //Locale.setDefault(Locale.US);
        //Locale.setDefault(Locale.GERMANY);

        System.out.println(LanguageManager.Language.getString("HelloWorld"));
        DbAdapter dbAdapter = new DbAdapter(new MySqlConnector());
        GuiPcVerwaltung gui = new GuiPcVerwaltung();
        gui.showGui();
    }
}
