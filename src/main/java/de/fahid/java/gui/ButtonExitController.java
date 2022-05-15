package de.fahid.java.gui;

import de.fahid.java.LanguageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonExitController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonExitController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        // frage nach Bestätigung und beende Programm
        int result = JOptionPane.showConfirmDialog(
                null,
                LanguageManager.getString("ExitConfirmation"),
                LanguageManager.getString("Confirmation"),
                JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        //System.out.println("ButtonExitController");
    }
}
