package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAddController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonAddController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        gui.isNewPc = true;
        gui.txtCpu.setText("");
        gui.txtRam.setText("");
        gui.txtHdd.setText("");
        //System.out.println("ButtonAddController");
    }
}
