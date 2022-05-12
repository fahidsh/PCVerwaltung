package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSaveController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonSaveController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        System.out.println("ButtonSaveController.actionPerformed()");
    }
}
