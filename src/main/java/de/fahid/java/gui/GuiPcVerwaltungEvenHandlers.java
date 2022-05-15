package de.fahid.java.gui;

public class GuiPcVerwaltungEvenHandlers {
    GuiPcVerwaltung gui = null;
    public GuiPcVerwaltungEvenHandlers() {}
    public GuiPcVerwaltungEvenHandlers(GuiPcVerwaltung gui) { setGui(gui); }
    public void setGui(GuiPcVerwaltung gui) {
        this.gui = gui;
        registerButtonEvents();
        registerScrollBarEvents();
    }

    private void registerButtonEvents() {
        gui.btnFirst.addActionListener(new ButtonFirstController(gui));
        gui.btnPrevious.addActionListener(new ButtonPreviousController(gui));
        gui.btnNext.addActionListener(new ButtonNextController(gui));
        gui.btnLast.addActionListener(new ButtonLastController(gui));
        gui.btnAdd.addActionListener(new ButtonAddController(gui));
        gui.btnSave.addActionListener(new ButtonSaveController(gui));
        gui.btnExit.addActionListener(new ButtonExitController(gui));
    }
    private void registerScrollBarEvents() {
        gui.sbNavigation.addAdjustmentListener(new ScrollBarNavigationController(gui));
    }
    private void registerTextBoxEvents() {
        gui.txtCpu.addActionListener(new TextBoxCommonController(gui));
        gui.txtRam.addActionListener(new TextBoxCommonController(gui));
        gui.txtHdd.addActionListener(new TextBoxCommonController(gui));
    }
}
