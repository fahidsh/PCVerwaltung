package de.fahid.java.gui;

import de.fahid.java.LanguageManager;
import de.fahid.java.datenbank.MySqlConnector;
import de.fahid.java.pc_verwaltung.DbAdapter;
import de.fahid.java.pc_verwaltung.PC;
import de.fahid.java.pc_verwaltung.PCListe;
import de.fahid.java.pc_verwaltung.PCVerwaltungException;

import javax.swing.*;
import java.awt.*;

public class GuiPcVerwaltung {
    private GuiPcVerwaltungEvenHandlers evenHandlers = new GuiPcVerwaltungEvenHandlers(this);
    protected JFrame frame;
    private DbAdapter dbAdapter = new DbAdapter(new MySqlConnector());
    protected PCListe pcs = null;
    protected PC activePC = null;
    protected int activePCIndex = -1;

    protected JPanel pnlMain;
    private JPanel pnlHeader;
    private JPanel pnlStatus;
    private JPanel pnlSrollbar;
    private JPanel pnlNavigation;
    private JPanel pnlCommandButtons;
    private JPanel pnlPcProperties;
    private JPanel PnlPcSummary;
    private JLabel lblAppName;
    private JLabel lblAppVersion;
    private JPanel pnlHeaderSpacer;
    protected JLabel lblStatus;
    protected JScrollBar sbNavigation;
    protected JButton btnFirst;
    protected JButton btnPrevious;
    protected JButton btnNext;
    protected JButton btnLast;
    protected JButton btnAdd;
    protected JButton btnSave;
    protected JButton btnExit;
    protected JTextField txtCpu;
    protected JTextField txtRam;
    protected JTextField txtHdd;
    protected JTextArea taPcSummary;
    protected JLabel lblNavigation;

    public GuiPcVerwaltung() {
        changeFont(pnlMain, new Font("Courier New", Font.PLAIN, 18));
        getAllePcs();
    }

    public void showGui() {
        frame = new JFrame(LanguageManager.getString("AppName"));
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void getAllePcs() {
        try {
            PCListe newPcListe = dbAdapter.getAllePcs();
            if (newPcListe != null) {
                pcs = newPcListe;
            }

            setActivePC(0);
            updateGui();
        } catch (PCVerwaltungException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setActivePC(int index) {
        if (pcs != null) {
            if (index >= 0 && index < pcs.getAnzahl()) {
                activePC = pcs.getPC(index);
                activePCIndex = index;
            }
        }
    }

    public void updateGui() {
        PC activePC = pcs.getPC(activePCIndex);
        if (activePC != null) {
            txtCpu.setText(String.valueOf(activePC.getTaktfrequenz()));
            txtRam.setText(String.valueOf(activePC.getRam()));
            txtHdd.setText(String.valueOf(activePC.getHdd()));
            taPcSummary.setText(activePC.getAusgabeString());

            updateButtons();
            new ScrollBarNavigationController(this).updateScrollbar();
            updateNavigationLabel();
        }
    }

    public void updateButtons() {
        int anzahlPcs = pcs.getAnzahl();
        btnFirst.setEnabled(activePCIndex > 0);
        btnPrevious.setEnabled(activePCIndex > 0);
        btnNext.setEnabled(activePCIndex < anzahlPcs - 1);
        btnLast.setEnabled(activePCIndex < anzahlPcs - 1);
    }

    public void updateNavigationLabel() {
        int anzahlPcs = pcs.getAnzahl();
        lblNavigation.setText(String.format("%d / %d", activePCIndex + 1, anzahlPcs));
    }
    public static void changeFont ( Component component, Font font ){
        component.setFont ( font );
        if ( component instanceof Container ) {
            for ( Component child : ( ( Container ) component ).getComponents () ) {
                if(child.getName() != null){
                    if(child.getName().equals("lblAppName")){continue;}
                }
                //System.out.println(child);
                changeFont ( child, font );
                if(child.getClass().getName().contains("JButton")){
                    child.setFont(child.getFont().deriveFont(Font.BOLD));
                }
            }
        }
    }
}
