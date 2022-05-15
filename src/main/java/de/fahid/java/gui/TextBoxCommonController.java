package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextBoxCommonController implements ActionListener {
    GuiPcVerwaltung gui = null;
    public TextBoxCommonController(GuiPcVerwaltung gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
