package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonLastController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonLastController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        gui.sbNavigation.setValue(gui.pcs.getAnzahl() - 1);
        //gui.activePCIndex = gui.pcs.getAnzahl() - 1;
        //gui.updateGui();
        //System.out.println("ButtonLastController");
    }
}
