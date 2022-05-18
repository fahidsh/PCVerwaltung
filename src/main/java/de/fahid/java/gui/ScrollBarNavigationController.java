package de.fahid.java.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ScrollBarNavigationController implements AdjustmentListener {
    private GuiPcVerwaltung gui = null;
    public ScrollBarNavigationController(GuiPcVerwaltung gui) {this.gui = gui;}

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if(gui == null) { return; }

        if(gui.isNewPc) {
            gui.isNewPc = false;
            gui.activePCIndex = gui.pcs.getAnzahl();
        }else {
            gui.activePCIndex = e.getValue();
        }

        gui.updateGui();
        //System.out.println("ScrollBarNavigationController: " + e.getValue());
    }

    public void updateScrollbar() {
        int anzahlPcs = gui.pcs.getAnzahl();
        gui.sbNavigation.setMaximum(anzahlPcs);
        gui.sbNavigation.setValue(gui.activePCIndex);
    }
}
