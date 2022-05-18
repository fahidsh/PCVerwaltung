package de.fahid.java.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import de.fahid.java.LanguageManager;
import de.fahid.java.datenbank.MySqlConnector;
import de.fahid.java.pc_verwaltung.DbAdapter;
import de.fahid.java.pc_verwaltung.PC;
import de.fahid.java.pc_verwaltung.PCListe;
import de.fahid.java.pc_verwaltung.PCVerwaltungException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;

public class GuiPcVerwaltung {
    //private GuiPcVerwaltungEvenHandlers evenHandlers = new GuiPcVerwaltungEvenHandlers(this);
    protected JFrame frame;
    protected DbAdapter dbAdapter = new DbAdapter(new MySqlConnector());
    protected PCListe pcs = null;
    protected int activePCIndex = -1;

    protected boolean isNewPc = false;

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
        changeFont(pnlMain, new Font("Courier New", Font.PLAIN, 16));
        getAllePcs();
        registerEventHandlers();
    }


    private void registerEventHandlers() {
        // Butoons
        btnFirst.addActionListener(new ButtonFirstController(this));
        btnPrevious.addActionListener(new ButtonPreviousController(this));
        btnNext.addActionListener(new ButtonNextController(this));
        btnLast.addActionListener(new ButtonLastController(this));
        btnAdd.addActionListener(new ButtonAddController(this));
        btnSave.addActionListener(new ButtonSaveController(this));
        btnExit.addActionListener(new ButtonExitController(this));
        // ScrollBar
        sbNavigation.addAdjustmentListener(new ScrollBarNavigationController(this));
        // TextBoxes
        txtCpu.addActionListener(new TextBoxCommonController(this));
        txtRam.addActionListener(new TextBoxCommonController(this));
        txtHdd.addActionListener(new TextBoxCommonController(this));
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
            updateNavigationLabel();
            sbNavigation.setMaximum(pcs.getAnzahl());
            sbNavigation.setValue(activePCIndex);
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

    public static void changeFont(Component component, Font font) {
        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                if (child.getName() != null) {
                    if (child.getName().equals("lblAppName")) {
                        continue;
                    }
                }
                //System.out.println(child);
                changeFont(child, font);
                if (child.getClass().getName().contains("JButton")) {
                    child.setFont(child.getFont().deriveFont(Font.BOLD));
                }
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new GridLayoutManager(10, 3, new Insets(5, 5, 5, 5), -1, -1));
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlMain.add(pnlHeader, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pnlHeader.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lblAppName = new JLabel();
        Font lblAppNameFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 32, lblAppName.getFont());
        if (lblAppNameFont != null) lblAppName.setFont(lblAppNameFont);
        lblAppName.setForeground(new Color(-131072));
        lblAppName.setName("lblAppName");
        this.$$$loadLabelText$$$(lblAppName, this.$$$getMessageFromBundle$$$("language", "AppName"));
        pnlHeader.add(lblAppName);
        lblAppVersion = new JLabel();
        this.$$$loadLabelText$$$(lblAppVersion, this.$$$getMessageFromBundle$$$("language", "AppVersion"));
        pnlHeader.add(lblAppVersion);
        final Spacer spacer1 = new Spacer();
        pnlMain.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pnlStatus = new JPanel();
        pnlStatus.setLayout(new GridLayoutManager(1, 1, new Insets(1, 10, 1, 10), -1, -1));
        pnlMain.add(pnlStatus, new GridConstraints(9, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pnlStatus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lblStatus = new JLabel();
        this.$$$loadLabelText$$$(lblStatus, this.$$$getMessageFromBundle$$$("language", "LabelStatusbar"));
        pnlStatus.add(lblStatus, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlSrollbar = new JPanel();
        pnlSrollbar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlMain.add(pnlSrollbar, new GridConstraints(7, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        pnlSrollbar.add(spacer2);
        sbNavigation = new JScrollBar();
        sbNavigation.setAutoscrolls(false);
        sbNavigation.setMinimumSize(new Dimension(5, 40));
        sbNavigation.setOrientation(0);
        sbNavigation.setPreferredSize(new Dimension(800, 40));
        sbNavigation.setValueIsAdjusting(false);
        sbNavigation.setVisibleAmount(1);
        pnlSrollbar.add(sbNavigation);
        pnlNavigation = new JPanel();
        pnlNavigation.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlMain.add(pnlNavigation, new GridConstraints(6, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pnlNavigation.setBorder(BorderFactory.createTitledBorder(null, this.$$$getMessageFromBundle$$$("language", "LabelNaigationPanel"), TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        btnFirst = new JButton();
        this.$$$loadButtonText$$$(btnFirst, this.$$$getMessageFromBundle$$$("language", "ButtonFirst"));
        pnlNavigation.add(btnFirst);
        btnPrevious = new JButton();
        this.$$$loadButtonText$$$(btnPrevious, this.$$$getMessageFromBundle$$$("language", "ButtonPrevious"));
        pnlNavigation.add(btnPrevious);
        lblNavigation = new JLabel();
        lblNavigation.setHorizontalAlignment(0);
        lblNavigation.setHorizontalTextPosition(0);
        lblNavigation.setPreferredSize(new Dimension(120, 18));
        lblNavigation.setText("0");
        pnlNavigation.add(lblNavigation);
        btnNext = new JButton();
        this.$$$loadButtonText$$$(btnNext, this.$$$getMessageFromBundle$$$("language", "ButtonNext"));
        pnlNavigation.add(btnNext);
        btnLast = new JButton();
        this.$$$loadButtonText$$$(btnLast, this.$$$getMessageFromBundle$$$("language", "ButtonLast"));
        pnlNavigation.add(btnLast);
        btnAdd = new JButton();
        this.$$$loadButtonText$$$(btnAdd, this.$$$getMessageFromBundle$$$("language", "ButtonAdd"));
        pnlNavigation.add(btnAdd);
        final Spacer spacer3 = new Spacer();
        pnlMain.add(spacer3, new GridConstraints(5, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pnlCommandButtons = new JPanel();
        pnlCommandButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        pnlMain.add(pnlCommandButtons, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnSave = new JButton();
        this.$$$loadButtonText$$$(btnSave, this.$$$getMessageFromBundle$$$("language", "ButtonSave"));
        pnlCommandButtons.add(btnSave);
        btnExit = new JButton();
        this.$$$loadButtonText$$$(btnExit, this.$$$getMessageFromBundle$$$("language", "ButtonExit"));
        pnlCommandButtons.add(btnExit);
        pnlPcProperties = new JPanel();
        pnlPcProperties.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        pnlMain.add(pnlPcProperties, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        this.$$$loadLabelText$$$(label1, this.$$$getMessageFromBundle$$$("language", "LabelCpu"));
        pnlPcProperties.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(120, -1), null, 0, false));
        txtCpu = new JTextField();
        txtCpu.setName("txtCpu");
        pnlPcProperties.add(txtCpu, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        this.$$$loadLabelText$$$(label2, this.$$$getMessageFromBundle$$$("language", "RAM"));
        pnlPcProperties.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtRam = new JTextField();
        txtRam.setName("txtRam");
        pnlPcProperties.add(txtRam, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        this.$$$loadLabelText$$$(label3, this.$$$getMessageFromBundle$$$("language", "LabelHdd"));
        pnlPcProperties.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtHdd = new JTextField();
        txtHdd.setName("txtHdd");
        pnlPcProperties.add(txtHdd, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        pnlPcProperties.add(spacer4, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        pnlPcProperties.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 50), null, 0, false));
        PnlPcSummary = new JPanel();
        PnlPcSummary.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        pnlMain.add(PnlPcSummary, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        taPcSummary = new JTextArea();
        taPcSummary.setEditable(false);
        taPcSummary.setTabSize(2);
        taPcSummary.setText("");
        PnlPcSummary.add(taPcSummary, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer6 = new Spacer();
        PnlPcSummary.add(spacer6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pnlHeaderSpacer = new JPanel();
        pnlHeaderSpacer.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pnlMain.add(pnlHeaderSpacer, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        pnlMain.add(spacer7, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        pnlMain.add(spacer8, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    private static Method $$$cachedGetBundleMethod$$$ = null;

    private String $$$getMessageFromBundle$$$(String path, String key) {
        ResourceBundle bundle;
        try {
            Class<?> thisClass = this.getClass();
            if ($$$cachedGetBundleMethod$$$ == null) {
                Class<?> dynamicBundleClass = thisClass.getClassLoader().loadClass("com.intellij.DynamicBundle");
                $$$cachedGetBundleMethod$$$ = dynamicBundleClass.getMethod("getBundle", String.class, Class.class);
            }
            bundle = (ResourceBundle) $$$cachedGetBundleMethod$$$.invoke(null, path, thisClass);
        } catch (Exception e) {
            bundle = ResourceBundle.getBundle(path);
        }
        return bundle.getString(key);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }

}
