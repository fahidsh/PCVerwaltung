package de.fahid.java;

import de.fahid.java.gui.GuiPcVerwaltung;

import java.util.Locale;

public class App {
    public static void main(String[] args) {
        //Locale.setDefault(Locale.US);
        //Locale.setDefault(Locale.GERMANY);

        System.out.println(LanguageManager.Language.getString("HelloWorld"));
        GuiPcVerwaltung gui = new GuiPcVerwaltung();
        gui.showGui();
    }
}
