package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonNextController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonNextController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        if(gui.activePCIndex +1 <= gui.pcs.getAnzahl()) {
            gui.activePCIndex++;
        }
        gui.updateGui();
        //System.out.println("ButtonNextController");
    }
}
