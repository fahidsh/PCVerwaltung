package de.fahid.java.gui;

import de.fahid.java.LanguageManager;

import javax.swing.*;

public class GuiPcVerwaltung {
    private JPanel pnlMain;
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
    private JLabel lblStatus;
    private JScrollBar sbNavigation;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnLast;
    private JButton btnAdd;
    private JButton btnSave;
    private JButton btnExit;
    private JTextField txtRam;
    private JTextField txtCpu;
    private JTextField txtHdd;
    private JTextArea taPcSummary;

    public GuiPcVerwaltung() {
    }

    public void showGui() {
        JFrame frame = new JFrame(LanguageManager.getString("AppName"));
        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
