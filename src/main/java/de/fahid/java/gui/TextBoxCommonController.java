package de.fahid.java.gui;

import de.fahid.java.LanguageManager;
import de.fahid.java.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxCommonController implements ActionListener {
    GuiPcVerwaltung gui = null;
    public TextBoxCommonController(GuiPcVerwaltung gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField txt = (JTextField) e.getSource();
        if(txt.getName() == null) {
            return;
        }
        if(!Utilities.isDouble(txt.getText())) {
            JOptionPane.showMessageDialog(
                    gui.frame,
                    LanguageManager.getString("InvalidNumberException"),
                    LanguageManager.getString("Error"),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (txt.getName()) {
            case "txtCpu":
                gui.txtRam.requestFocus();
                break;
            case "txtRam":
                gui.txtHdd.requestFocus();
                break;
            case "txtHdd":
                gui.btnSave.doClick();
                break;
        }
    }
}
