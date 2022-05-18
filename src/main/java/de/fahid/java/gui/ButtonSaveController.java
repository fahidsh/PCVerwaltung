package de.fahid.java.gui;

import de.fahid.java.LanguageManager;
import de.fahid.java.pc_verwaltung.PC;
import de.fahid.java.pc_verwaltung.PCVerwaltungException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSaveController implements ActionListener {
    private GuiPcVerwaltung gui = null;
    public ButtonSaveController(GuiPcVerwaltung gui) { this.gui = gui; }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(gui == null) { return; }

        try {
            double takt = Double.parseDouble(gui.txtCpu.getText());
            double ram = Double.parseDouble(gui.txtRam.getText());
            double hdd = Double.parseDouble(gui.txtHdd.getText());

            PC pc = new PC();
            pc.setTaktfrequenz(takt);
            pc.setRam(ram);
            pc.setHdd(hdd);

            if (gui.activePCIndex == gui.pcs.getAnzahl()) {
                gui.pcs.addPC(pc);
                System.out.println("new PC added");
            } else {
                pc.setId(gui.pcs.getPC(gui.activePCIndex).getId());
                gui.pcs.updatePC(gui.activePCIndex, pc);
                System.out.println("PC updated");
            }
            gui.updateGui();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    gui.frame,
                    LanguageManager.getString("InvalidNumberException"),
                    LanguageManager.getString("Error"),
                    JOptionPane.ERROR_MESSAGE);

        } catch (PCVerwaltungException ex) {
            System.out.println(ex.getMessage());
        }

        //System.out.println("ButtonSaveController");
    }
}
