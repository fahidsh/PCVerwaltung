package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFirstController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonFirstController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        gui.sbNavigation.setValue(0);
        //gui.activePCIndex = 0;
        //gui.updateGui();
        //System.out.println("ButtonFirstController");
    }
}
