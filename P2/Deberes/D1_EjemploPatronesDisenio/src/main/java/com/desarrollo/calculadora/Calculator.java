/*
 * Calculadora - JFrame
 */
package com.desarrollo.calculadora;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.text.*;
import java.util.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

/**
 *
 * @author Bryan Andagoya
 */
public final class Calculator extends JFrame {

    //Fields
    private Double[] operands = {null, null};
    private final DecimalFormat mainScreenFormatter = new DecimalFormat("###,###.####################");
    private final DecimalFormat secondScreenFormatter = new DecimalFormat("#.####################");
    private ArrayList<JButton> symbolButtons;
    private ArrayList<JButton> numberButtons;
    private CalculatorFactory componentsFactory = new LightFactory();
    private Point initialClick;

    private String operator = null;
    private boolean overwriteMainScreen = true;
    private boolean equalClicked = false;
    private Double result = null;
    private boolean firstTime = true;
    private boolean indeterminacy = false;
    private boolean divisionBy0 = false;
    private Double equalResult;
    private boolean overwriteSecondScreen = true;

    /**
     * Creates new form Calculadora
     */
    public Calculator() {
        configureWindow();
        initiateComponents();
    }

    /**
     * Method that configures the window (JFrame).
     */
    public void configureWindow() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setUndecorated(true);
        setResizable(false);
        setSize(350, 570);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new AbsoluteLayout());

        setIconImage(Toolkit.getDefaultToolkit()
                .getImage("src/main/java/com/desarrollo/images/icon.png"));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(
                        0, 0, getWidth(), getHeight(), 20, 20));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    public void initiateComponents() {
        symbolButtons = new ArrayList<JButton>();
        numberButtons = new ArrayList<JButton>();

        mainPanel = componentsFactory.createMainPanel();
        btn_division = componentsFactory.createDivisionButton();
        btn_multiplication = componentsFactory.createMultiplicationButton();
        btn_subtraction = componentsFactory.createSubtractionButton();
        btn_clean = componentsFactory.createClearButton();
        btn_7 = componentsFactory.createButton7();
        btn_8 = componentsFactory.createButton8();
        btn_9 = componentsFactory.createButton9();
        btn_sqrt = componentsFactory.createSqrtButton();
        btn_4 = componentsFactory.createButton4();
        btn_5 = componentsFactory.createButton5();
        btn_6 = componentsFactory.createButton6();
        btn_equal = componentsFactory.createEqualButton();
        btn_1 = componentsFactory.createButton1();
        btn_2 = componentsFactory.createButton2();
        btn_3 = componentsFactory.createButton3();
        btn_0 = componentsFactory.createButton0();
        btn_comma = componentsFactory.createCommaButton();
        btn_sum = componentsFactory.createSumButton();
        btn_pow = componentsFactory.createPowButton();
        pnl_TitleBar = componentsFactory.createTitleBarPanel();
        btn_close = new JButton();
        btn_minimize = new JButton();
        pnl_screen = componentsFactory.createScreenPanel();
        txt_mainScreen = componentsFactory.createMainScreen();
        txt_secondScreen = componentsFactory.createSecondScreen();
        btn_theme = componentsFactory.createThemeButton();

        btn_division.addActionListener(this::btn_divisionActionPerformed);
        mainPanel.add(btn_division, new AbsoluteConstraints(110, 160, 60, 60));

        btn_multiplication.addActionListener(this::btn_multiplicationActionPerformed);
        mainPanel.add(btn_multiplication, new AbsoluteConstraints(190, 160, 60, 60));

        btn_subtraction.addActionListener(this::btn_subtractionActionPerformed);
        mainPanel.add(btn_subtraction, new AbsoluteConstraints(270, 160, 60, 60));

        btn_clean.addActionListener(this::btn_cleanActionPerformed);
        mainPanel.add(btn_clean, new AbsoluteConstraints(30, 160, 60, 60));

        btn_7.addActionListener(this::btn_7ActionPerformed);
        mainPanel.add(btn_7, new AbsoluteConstraints(30, 240, 60, 60));

        btn_8.addActionListener(this::btn_8ActionPerformed);
        mainPanel.add(btn_8, new AbsoluteConstraints(110, 240, 60, 60));

        btn_9.addActionListener(this::btn_9ActionPerformed);
        mainPanel.add(btn_9, new AbsoluteConstraints(190, 240, 60, 60));

        btn_sqrt.addActionListener(this::btn_sqrtActionPerformed);
        mainPanel.add(btn_sqrt, new AbsoluteConstraints(270, 400, 60, 60));

        btn_4.addActionListener(this::btn_4ActionPerformed);
        mainPanel.add(btn_4, new AbsoluteConstraints(30, 320, 60, 60));

        btn_5.addActionListener(this::btn_5ActionPerformed);
        mainPanel.add(btn_5, new AbsoluteConstraints(110, 320, 60, 60));

        btn_6.addActionListener(this::btn_6ActionPerformed);
        mainPanel.add(btn_6, new AbsoluteConstraints(190, 320, 60, 60));

        btn_equal.addActionListener(this::btn_equalActionPerformed);
        mainPanel.add(btn_equal, new AbsoluteConstraints(270, 480, 60, 60));

        btn_1.addActionListener(this::btn_1ActionPerformed);
        mainPanel.add(btn_1, new AbsoluteConstraints(30, 400, 60, 60));

        btn_2.addActionListener(this::btn_2ActionPerformed);
        mainPanel.add(btn_2, new AbsoluteConstraints(110, 400, 60, 60));

        btn_3.addActionListener(this::btn_3ActionPerformed);
        mainPanel.add(btn_3, new AbsoluteConstraints(190, 400, 60, 60));

        btn_0.addActionListener(this::btn_0ActionPerformed);
        mainPanel.add(btn_0, new AbsoluteConstraints(30, 480, 60, 60));

        btn_comma.addActionListener(this::btn_commaActionPerformed);
        mainPanel.add(btn_comma, new AbsoluteConstraints(190, 480, 60, 60));

        btn_sum.addActionListener(this::btn_sumActionPerformed);
        mainPanel.add(btn_sum, new AbsoluteConstraints(270, 240, 60, 60));

        btn_pow.addActionListener(this::btn_powActionPerformed);
        mainPanel.add(btn_pow, new AbsoluteConstraints(270, 320, 60, 60));

        pnl_TitleBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                pnl_TitleBarMouseDragged(evt);
            }
        });
        pnl_TitleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                pnl_TitleBarMousePressed(evt);
            }
        });
        mainPanel.add(pnl_TitleBar, new AbsoluteConstraints(0, 0, 340, 30));

        btn_close.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/close.png"));
        btn_close.setBorderPainted(false);
        btn_close.setContentAreaFilled(false);
        btn_close.setFocusPainted(false);
        btn_close.setBorder(null);
        btn_close.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/closeRollover.png"));
        btn_close.addActionListener(this::btn_closeActionPerformed);
        pnl_TitleBar.add(btn_close, new AbsoluteConstraints(6, 6, 15, 15));

        btn_minimize.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/minimize.png"));
        btn_minimize.setBorderPainted(false);
        btn_minimize.setContentAreaFilled(false);
        btn_minimize.setFocusPainted(false);
        btn_minimize.setBorder(null);
        btn_minimize.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/minimizeRollover.png"));
        btn_minimize.addActionListener(this::btn_minimizeActionPerformed);
        pnl_TitleBar.add(btn_minimize, new AbsoluteConstraints(27, 6, 15, 15));

        btn_theme.addActionListener(this::btn_themeActionPerformed);
        pnl_TitleBar.add(btn_theme, new AbsoluteConstraints(300, 5, 40, 20));

        pnl_screen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        mainPanel.add(pnl_screen, new AbsoluteConstraints(0, 38, 350, 90));

        pnl_screen.add(txt_mainScreen, new AbsoluteConstraints(6, 45, 344, 43));

        pnl_screen.add(txt_secondScreen, new AbsoluteConstraints(6, 0, 338, 40));

        getContentPane().add(mainPanel, new AbsoluteConstraints(0, 0, 350, 570));

        fillSymbolButtonsList();
        fillNumberButtonsList();
        enableSymbolButtons(false);
        btn_subtraction.setEnabled(true);
        clean();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        btn_division = new javax.swing.JButton();
        btn_multiplication = new javax.swing.JButton();
        btn_subtraction = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();
        btn_7 = new javax.swing.JButton();
        btn_8 = new javax.swing.JButton();
        btn_9 = new javax.swing.JButton();
        btn_sqrt = new javax.swing.JButton();
        btn_4 = new javax.swing.JButton();
        btn_5 = new javax.swing.JButton();
        btn_6 = new javax.swing.JButton();
        btn_equal = new javax.swing.JButton();
        btn_1 = new javax.swing.JButton();
        btn_2 = new javax.swing.JButton();
        btn_3 = new javax.swing.JButton();
        btn_0 = new javax.swing.JButton();
        btn_comma = new javax.swing.JButton();
        btn_sum = new javax.swing.JButton();
        btn_pow = new javax.swing.JButton();
        pnl_TitleBar = new javax.swing.JPanel();
        btn_close = new javax.swing.JButton();
        btn_minimize = new javax.swing.JButton();
        btn_theme = new javax.swing.JButton();
        pnl_screen = new javax.swing.JPanel();
        txt_mainScreen = new javax.swing.JTextField();
        txt_secondScreen = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(350, 570));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(221, 253, 255));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_division.setBackground(new java.awt.Color(234, 254, 255));
        btn_division.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_division.setForeground(new java.awt.Color(15, 70, 82));
        btn_division.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_division.setText("÷");
        btn_division.setBorderPainted(false);
        btn_division.setContentAreaFilled(false);
        btn_division.setFocusPainted(false);
        btn_division.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_division.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_division.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_divisionActionPerformed(evt);
            }
        });
        mainPanel.add(btn_division, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 60, 60));

        btn_multiplication.setBackground(new java.awt.Color(234, 254, 255));
        btn_multiplication.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_multiplication.setForeground(new java.awt.Color(15, 70, 82));
        btn_multiplication.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_multiplication.setText("X");
        btn_multiplication.setBorderPainted(false);
        btn_multiplication.setContentAreaFilled(false);
        btn_multiplication.setFocusPainted(false);
        btn_multiplication.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_multiplication.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_multiplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_multiplicationActionPerformed(evt);
            }
        });
        mainPanel.add(btn_multiplication, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 60, 60));

        btn_subtraction.setBackground(new java.awt.Color(234, 254, 255));
        btn_subtraction.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_subtraction.setForeground(new java.awt.Color(15, 70, 82));
        btn_subtraction.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_subtraction.setText("-");
        btn_subtraction.setBorderPainted(false);
        btn_subtraction.setContentAreaFilled(false);
        btn_subtraction.setFocusPainted(false);
        btn_subtraction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_subtraction.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_subtraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_subtractionActionPerformed(evt);
            }
        });
        mainPanel.add(btn_subtraction, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 60, 60));

        btn_clean.setBackground(new java.awt.Color(234, 254, 255));
        btn_clean.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_clean.setForeground(new java.awt.Color(15, 70, 82));
        btn_clean.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonAC.png"));
        btn_clean.setText("AC");
        btn_clean.setBorderPainted(false);
        btn_clean.setContentAreaFilled(false);
        btn_clean.setFocusPainted(false);
        btn_clean.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_clean.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonAC2.png"));
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });
        mainPanel.add(btn_clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 60, 60));

        btn_7.setBackground(new java.awt.Color(234, 254, 255));
        btn_7.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_7.setForeground(new java.awt.Color(15, 70, 82));
        btn_7.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_7.setText("7");
        btn_7.setBorderPainted(false);
        btn_7.setContentAreaFilled(false);
        btn_7.setFocusPainted(false);
        btn_7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_7.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_7.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_7.setPreferredSize(new java.awt.Dimension(39, 39));
        btn_7.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_7.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_7ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 60, 60));

        btn_8.setBackground(new java.awt.Color(234, 254, 255));
        btn_8.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_8.setForeground(new java.awt.Color(15, 70, 82));
        btn_8.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_8.setText("8");
        btn_8.setBorderPainted(false);
        btn_8.setContentAreaFilled(false);
        btn_8.setFocusPainted(false);
        btn_8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_8.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_8.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_8.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_8.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_8ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 60, 60));

        btn_9.setBackground(new java.awt.Color(234, 254, 255));
        btn_9.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_9.setForeground(new java.awt.Color(15, 70, 82));
        btn_9.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_9.setText("9");
        btn_9.setBorderPainted(false);
        btn_9.setContentAreaFilled(false);
        btn_9.setFocusPainted(false);
        btn_9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_9.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_9.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_9.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_9.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_9ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 60, 60));

        btn_sqrt.setBackground(new java.awt.Color(234, 254, 255));
        btn_sqrt.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_sqrt.setForeground(new java.awt.Color(15, 70, 82));
        btn_sqrt.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_sqrt.setText("√ ");
        btn_sqrt.setBorderPainted(false);
        btn_sqrt.setContentAreaFilled(false);
        btn_sqrt.setFocusPainted(false);
        btn_sqrt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sqrt.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_sqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sqrtActionPerformed(evt);
            }
        });
        mainPanel.add(btn_sqrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 60, 60));

        btn_4.setBackground(new java.awt.Color(234, 254, 255));
        btn_4.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_4.setForeground(new java.awt.Color(15, 70, 82));
        btn_4.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_4.setText("4");
        btn_4.setBorderPainted(false);
        btn_4.setContentAreaFilled(false);
        btn_4.setFocusPainted(false);
        btn_4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_4.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_4.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_4.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_4.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 60, 60));

        btn_5.setBackground(new java.awt.Color(234, 254, 255));
        btn_5.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_5.setForeground(new java.awt.Color(15, 70, 82));
        btn_5.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_5.setText("5");
        btn_5.setBorderPainted(false);
        btn_5.setContentAreaFilled(false);
        btn_5.setFocusPainted(false);
        btn_5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_5.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_5.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_5.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_5.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 60, 60));

        btn_6.setBackground(new java.awt.Color(234, 254, 255));
        btn_6.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_6.setForeground(new java.awt.Color(15, 70, 82));
        btn_6.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_6.setText("6");
        btn_6.setBorderPainted(false);
        btn_6.setContentAreaFilled(false);
        btn_6.setFocusPainted(false);
        btn_6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_6.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_6.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_6.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_6.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_6ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 60, 60));

        btn_equal.setBackground(new java.awt.Color(234, 254, 255));
        btn_equal.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_equal.setForeground(new java.awt.Color(15, 70, 82));
        btn_equal.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_equal.setText("=");
        btn_equal.setBorderPainted(false);
        btn_equal.setContentAreaFilled(false);
        btn_equal.setFocusPainted(false);
        btn_equal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_equal.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_equalActionPerformed(evt);
            }
        });
        mainPanel.add(btn_equal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 60, 60));

        btn_1.setBackground(new java.awt.Color(234, 254, 255));
        btn_1.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_1.setForeground(new java.awt.Color(15, 70, 82));
        btn_1.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_1.setText("1");
        btn_1.setBorderPainted(false);
        btn_1.setContentAreaFilled(false);
        btn_1.setFocusPainted(false);
        btn_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_1.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_1.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_1.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_1.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 60, 60));

        btn_2.setBackground(new java.awt.Color(234, 254, 255));
        btn_2.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_2.setForeground(new java.awt.Color(15, 70, 82));
        btn_2.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_2.setText("2");
        btn_2.setBorderPainted(false);
        btn_2.setContentAreaFilled(false);
        btn_2.setFocusPainted(false);
        btn_2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_2.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_2.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_2.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_2.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 60, 60));

        btn_3.setBackground(new java.awt.Color(234, 254, 255));
        btn_3.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_3.setForeground(new java.awt.Color(15, 70, 82));
        btn_3.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_3.setText("3");
        btn_3.setBorderPainted(false);
        btn_3.setContentAreaFilled(false);
        btn_3.setFocusPainted(false);
        btn_3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_3.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_3.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_3.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_3.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 60, 60));

        btn_0.setBackground(new java.awt.Color(234, 254, 255));
        btn_0.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_0.setForeground(new java.awt.Color(15, 70, 82));
        btn_0.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_0.setText("0");
        btn_0.setBorderPainted(false);
        btn_0.setContentAreaFilled(false);
        btn_0.setFocusPainted(false);
        btn_0.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_0.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_0.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_0.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_0.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0ActionPerformed(evt);
            }
        });
        mainPanel.add(btn_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 60, 60));

        btn_comma.setBackground(new java.awt.Color(234, 254, 255));
        btn_comma.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_comma.setForeground(new java.awt.Color(15, 70, 82));
        btn_comma.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_comma.setText(",");
        btn_comma.setBorderPainted(false);
        btn_comma.setContentAreaFilled(false);
        btn_comma.setFocusPainted(false);
        btn_comma.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_comma.setMaximumSize(new java.awt.Dimension(39, 39));
        btn_comma.setMinimumSize(new java.awt.Dimension(39, 39));
        btn_comma.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        btn_comma.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_comma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_commaActionPerformed(evt);
            }
        });
        mainPanel.add(btn_comma, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 60, 60));

        btn_sum.setBackground(new java.awt.Color(234, 254, 255));
        btn_sum.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_sum.setForeground(new java.awt.Color(15, 70, 82));
        btn_sum.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_sum.setText("+");
        btn_sum.setBorderPainted(false);
        btn_sum.setContentAreaFilled(false);
        btn_sum.setFocusPainted(false);
        btn_sum.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_sum.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_sum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumActionPerformed(evt);
            }
        });
        mainPanel.add(btn_sum, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 60, 60));

        btn_pow.setBackground(new java.awt.Color(234, 254, 255));
        btn_pow.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        btn_pow.setForeground(new java.awt.Color(15, 70, 82));
        btn_pow.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        btn_pow.setText("^");
        btn_pow.setBorderPainted(false);
        btn_pow.setContentAreaFilled(false);
        btn_pow.setFocusPainted(false);
        btn_pow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_pow.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));
        btn_pow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_powActionPerformed(evt);
            }
        });
        mainPanel.add(btn_pow, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 60, 60));

        pnl_TitleBar.setBackground(new java.awt.Color(221, 253, 255));
        pnl_TitleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnl_TitleBarMouseDragged(evt);
            }
        });
        pnl_TitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnl_TitleBarMousePressed(evt);
            }
        });
        pnl_TitleBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_close.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/close.png"));
        btn_close.setBorderPainted(false);
        btn_close.setContentAreaFilled(false);
        btn_close.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_close.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/closeRollover.png"));
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });
        pnl_TitleBar.add(btn_close, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 15, 15));

        btn_minimize.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/minimize.png"));
        btn_minimize.setBorderPainted(false);
        btn_minimize.setContentAreaFilled(false);
        btn_minimize.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_minimize.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/minimizeRollover.png"));
        btn_minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizeActionPerformed(evt);
            }
        });
        pnl_TitleBar.add(btn_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 6, 15, 15));

        btn_theme.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonLight-Dark.png"));
        btn_theme.setBorder(null);
        btn_theme.setBorderPainted(false);
        btn_theme.setContentAreaFilled(false);
        btn_theme.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_theme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themeActionPerformed(evt);
            }
        });
        pnl_TitleBar.add(btn_theme, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 5, 40, 20));

        mainPanel.add(pnl_TitleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        pnl_screen.setBackground(new java.awt.Color(244, 254, 255));
        pnl_screen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_mainScreen.setEditable(false);
        txt_mainScreen.setBackground(new java.awt.Color(244, 254, 255));
        txt_mainScreen.setFont(new java.awt.Font("Cambria Math", 1, 28)); // NOI18N
        txt_mainScreen.setForeground(new java.awt.Color(35, 116, 140));
        txt_mainScreen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_mainScreen.setText("0");
        txt_mainScreen.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 15));
        txt_mainScreen.setMaximumSize(new java.awt.Dimension(244, 48));
        txt_mainScreen.setMinimumSize(new java.awt.Dimension(244, 48));
        txt_mainScreen.setPreferredSize(new java.awt.Dimension(244, 48));
        pnl_screen.add(txt_mainScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 45, 344, 43));

        txt_secondScreen.setEditable(false);
        txt_secondScreen.setBackground(new java.awt.Color(244, 254, 255));
        txt_secondScreen.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        txt_secondScreen.setForeground(new java.awt.Color(139, 193, 209));
        txt_secondScreen.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_secondScreen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 15));
        pnl_screen.add(txt_secondScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, 338, 40));

        mainPanel.add(pnl_screen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 38, 350, 90));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_multiplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_multiplicationActionPerformed
        performOperation(btn_multiplication);
    }//GEN-LAST:event_btn_multiplicationActionPerformed

    private void btn_subtractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_subtractionActionPerformed
        performOperation(btn_subtraction);
        btn_subtraction.setEnabled(false);
    }//GEN-LAST:event_btn_subtractionActionPerformed

    private void btn_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_9ActionPerformed
        writeOnScreen(btn_9);
    }//GEN-LAST:event_btn_9ActionPerformed

    private void btn_sqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sqrtActionPerformed
        calculateSqrt();
    }//GEN-LAST:event_btn_sqrtActionPerformed

    private void btn_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_6ActionPerformed
        writeOnScreen(btn_6);
    }//GEN-LAST:event_btn_6ActionPerformed

    private void btn_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_equalActionPerformed
        showResult();

        if (equalClicked) {
            if (equalResult != null) {

                txt_secondScreen.setText(secondScreenFormatter.format(equalResult)
                        + " "
                        + operator
                        + " "
                        + secondScreenFormatter.format(operands[1])
                        + " "
                        + btn_equal.getText());

            }

        } else {
            if (!txt_secondScreen.getText().contains("=")) {

                txt_secondScreen.setText(txt_secondScreen.getText()
                        + " "
                        + btn_equal.getText());

            }
        }

        equalResult = operands[0];
        overwriteSecondScreen = true;

        equalClicked = true;
    }//GEN-LAST:event_btn_equalActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed
        writeOnScreen(btn_3);
    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_7ActionPerformed
        writeOnScreen(btn_7);
    }//GEN-LAST:event_btn_7ActionPerformed

    private void btn_commaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_commaActionPerformed
        if (!txt_mainScreen.getText().contains(",") && !overwriteMainScreen) {
            txt_mainScreen.setText(txt_mainScreen.getText() + btn_comma.getText());

            txt_secondScreen.setText(txt_secondScreen.getText()
                    + btn_comma.getText());
        }
    }//GEN-LAST:event_btn_commaActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
        clean();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_8ActionPerformed
        writeOnScreen(btn_8);
    }//GEN-LAST:event_btn_8ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed
        writeOnScreen(btn_4);
    }//GEN-LAST:event_btn_4ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed
        writeOnScreen(btn_5);
    }//GEN-LAST:event_btn_5ActionPerformed

    private void btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1ActionPerformed
        writeOnScreen(btn_1);
    }//GEN-LAST:event_btn_1ActionPerformed

    private void btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2ActionPerformed
        writeOnScreen(btn_2);
    }//GEN-LAST:event_btn_2ActionPerformed

    private void btn_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0ActionPerformed
        writeOnScreen(btn_0);
    }//GEN-LAST:event_btn_0ActionPerformed

    private void btn_divisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_divisionActionPerformed
        performOperation(btn_division);
    }//GEN-LAST:event_btn_divisionActionPerformed

    private void btn_sumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumActionPerformed
        performOperation(btn_sum);
    }//GEN-LAST:event_btn_sumActionPerformed

    private void btn_powActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_powActionPerformed
        performOperation(btn_pow);
    }//GEN-LAST:event_btn_powActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizeActionPerformed
        this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btn_minimizeActionPerformed

    private void pnl_TitleBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_TitleBarMouseDragged
        // get location of Window
        int thisX = getLocation().x;
        int thisY = getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = evt.getX() - initialClick.x;
        int yMoved = evt.getY() - initialClick.y;

        // Move window to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;
        setLocation(X, Y);
    }//GEN-LAST:event_pnl_TitleBarMouseDragged

    private void pnl_TitleBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl_TitleBarMousePressed
        initialClick = evt.getPoint();
    }//GEN-LAST:event_pnl_TitleBarMousePressed

    private void btn_themeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themeActionPerformed
        if (componentsFactory instanceof LightFactory) {
            componentsFactory = new DarkFactory();
            getContentPane().removeAll();
            initiateComponents();
            validate();
        } else {
            componentsFactory = new LightFactory();
            getContentPane().removeAll();
            initiateComponents();
            validate();
        }
    }//GEN-LAST:event_btn_themeActionPerformed

    /**
     * Method that fills the symbol buttons list.
     */
    public void fillSymbolButtonsList() {
        symbolButtons.add(btn_sum);
        symbolButtons.add(btn_subtraction);
        symbolButtons.add(btn_multiplication);
        symbolButtons.add(btn_division);
        symbolButtons.add(btn_pow);
        symbolButtons.add(btn_sqrt);
        symbolButtons.add(btn_comma);
        symbolButtons.add(btn_equal);
    }

    /**
     * Method that fills the number buttons list.
     */
    public void fillNumberButtonsList() {
        numberButtons.add(btn_0);
        numberButtons.add(btn_1);
        numberButtons.add(btn_2);
        numberButtons.add(btn_3);
        numberButtons.add(btn_4);
        numberButtons.add(btn_5);
        numberButtons.add(btn_6);
        numberButtons.add(btn_7);
        numberButtons.add(btn_8);
        numberButtons.add(btn_9);
    }

    /**
     * Method that enebles or disables the symbol buttons.
     *
     * @param enable Set true for enable the buttons.
     */
    public void enableSymbolButtons(boolean enable) {
        symbolButtons.forEach(button -> {
            button.setEnabled(enable);
        });
    }

    /**
     * Method that enebles or disables the number buttons.
     *
     * @param enable Set true for enable the buttons.
     */
    public void enableNumberButtons(boolean enable) {
        numberButtons.forEach(button -> {
            button.setEnabled(enable);
        });
    }

    /**
     * Method that cleans the calculator.
     */
    public void clean() {
        if (btn_equal.isEnabled()) {
            enableSymbolButtons(false);
        }

        if (!btn_0.isEnabled()) {
            enableNumberButtons(true);
        }

        for (int i = 0; i < operands.length; i++) {
            operands[i] = null;
        }

        txt_mainScreen.setText("0");
        txt_secondScreen.setText("");
        operator = null;
        equalClicked = false;
        firstTime = true;
        indeterminacy = false;
        divisionBy0 = false;
        overwriteMainScreen = true;
        overwriteSecondScreen = true;
        btn_subtraction.setEnabled(true);
        equalResult = null;
        result = null;
    }

    /**
     * Method that formats the number.
     *
     * @param number The double number.
     * @return The formatted double number.
     */
    public String formatNumber(double number) {
        String aux = number + "";

        if (aux.endsWith(".0")) {
            return mainScreenFormatter.format((int) number);
        } else {
            return mainScreenFormatter.format(number);
        }
    }

    /**
     * Method that writes the numbers on the screen.
     *
     * @param button The number button.
     */
    public void writeOnScreen(JButton button) {
        double screenNumber;

        if (!(button.equals(btn_0) && txt_secondScreen.getText().equals("0"))) {
            if (overwriteSecondScreen) {
                txt_secondScreen.setText(button.getText());
                overwriteSecondScreen = false;
            } else {
                if (txt_secondScreen.getText().equals("0")) {
                    txt_secondScreen.setText(button.getText());
                } else {
                    txt_secondScreen.setText(txt_secondScreen.getText() + button
                            .getText());
                }
            }
        }

        try {
            if (overwriteMainScreen) {
                screenNumber = mainScreenFormatter.parse(button.getText())
                        .doubleValue();

                if (equalClicked) {
                    for (int i = 0; i < operands.length; i++) {
                        operands[i] = null;
                    }
                    equalClicked = false;
                }

                txt_mainScreen.setText(mainScreenFormatter.format(screenNumber));
                overwriteMainScreen = false;
            } else {
                if (txt_mainScreen.getText().contains(",")) {
                    txt_mainScreen.setText(txt_mainScreen.getText() + button.getText());
                } else {
                    screenNumber = mainScreenFormatter.parse(txt_mainScreen.getText()
                            + button.getText()).doubleValue();
                    txt_mainScreen.setText(mainScreenFormatter.format(screenNumber));
                }
            }

            if (!btn_equal.isEnabled()) {
                symbolButtons.forEach(b -> {
                    b.setEnabled(true);
                });
            }

        } catch (ParseException ex) {
            txt_mainScreen.setText("Error");
            overwriteMainScreen = true;
        }

    }

    /**
     * Method that shows the result on the screen.
     */
    public void showResult() {

        if (operands[1] == null) {
            try {
                operands[1] = mainScreenFormatter.parse(txt_mainScreen.getText())
                        .doubleValue();

            } catch (ParseException ex) {
                txt_mainScreen.setText("Error");
                overwriteMainScreen = true;
            }
        }

        if (operands[0] != null && operands[1] != null) {
            if (operator.equals(btn_sum.getText())) {
                result = operands[0] + operands[1];
            } else if (operator.equals(btn_subtraction.getText())) {
                result = operands[0] - operands[1];
            } else if (operator.equals(btn_multiplication.getText())) {
                result = operands[0] * operands[1];
            } else if (operator.equals(btn_division.getText())) {
                if (operands[0] == 0 && operands[1] == 0) {
                    indeterminacy = true;
                } else if (operands[1] == 0) {
                    divisionBy0 = true;
                } else {
                    result = operands[0] / operands[1];
                }
            } else if (operator.equals(btn_pow.getText())) {
                result = Math.pow(operands[0], operands[1]);
            }

            if (indeterminacy) {
                txt_mainScreen.setText("Indeterminación");

                for (int i = 0; i < operands.length; i++) {
                    operands[i] = null;
                }

                enableSymbolButtons(false);
                enableNumberButtons(false);
                indeterminacy = false;
                overwriteSecondScreen = true;
            } else if (divisionBy0) {
                txt_mainScreen.setText("No existe división para 0");

                for (int i = 0; i < operands.length; i++) {
                    operands[i] = null;
                }

                enableSymbolButtons(false);
                enableNumberButtons(false);
                divisionBy0 = false;
                overwriteSecondScreen = true;
            } else {
                txt_mainScreen.setText(formatNumber(result));
                operands[0] = result;
            }

            overwriteMainScreen = true;
        } else {
            try {
                result = mainScreenFormatter.parse(txt_mainScreen.getText())
                        .doubleValue();

            } catch (ParseException ex) {
                txt_mainScreen.setText("Error");
                overwriteMainScreen = true;
            }
        }

    }

    /**
     * Method that performs the operations.
     *
     * @param operatorButton The operator button.
     */
    public void performOperation(JButton operatorButton) {

        if (overwriteSecondScreen) {
            if (result == null) {
                txt_secondScreen.setText(operatorButton.getText());
            } else {
                txt_secondScreen.setText(secondScreenFormatter.format(result)
                        + " " + operatorButton.getText() + " ");
            }

            overwriteSecondScreen = false;
        } else {
            txt_secondScreen.setText(txt_secondScreen.getText()
                    + " " + operatorButton.getText() + " ");
        }

        if (firstTime) {
            operator = operatorButton.getText();
            firstTime = false;
        }

        if (equalClicked) {
            for (int i = 0; i < operands.length; i++) {
                operands[i] = null;
            }
            equalClicked = false;
        }

        symbolButtons.forEach(button -> {
            button.setEnabled(false);
        });

        try {
            if (operands[0] == null) {
                operands[0] = mainScreenFormatter.parse(txt_mainScreen.getText())
                        .doubleValue();

                overwriteMainScreen = true;
            } else {
                showResult();
            }
        } catch (ParseException ex) {
            txt_mainScreen.setText("Error");
            overwriteMainScreen = true;
        }

        operands[1] = null;
        operator = operatorButton.getText();
    }

    /**
     * Method that calculates the sqrt of a number.
     */
    public void calculateSqrt() {
        try {
            operands[0] = mainScreenFormatter.parse(txt_mainScreen.getText())
                    .doubleValue();

            txt_secondScreen.setText("√" + secondScreenFormatter
                    .format(operands[0]));

        } catch (ParseException ex) {
            txt_mainScreen.setText("Error");
            overwriteMainScreen = true;
        }

        if (operands[0] >= 0) {
            result = Math.sqrt(operands[0]);
            txt_mainScreen.setText(formatNumber(result));
        } else {
            txt_mainScreen.setText("No existe raíz de un # -");
            enableSymbolButtons(false);
            enableNumberButtons(false);
        }

        for (int i = 0; i < operands.length; i++) {
            operands[i] = null;
        }

        overwriteMainScreen = true;
        overwriteSecondScreen = true;
    }

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_0;
    private javax.swing.JButton btn_1;
    private javax.swing.JButton btn_2;
    private javax.swing.JButton btn_3;
    private javax.swing.JButton btn_4;
    private javax.swing.JButton btn_5;
    private javax.swing.JButton btn_6;
    private javax.swing.JButton btn_7;
    private javax.swing.JButton btn_8;
    private javax.swing.JButton btn_9;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_comma;
    private javax.swing.JButton btn_division;
    private javax.swing.JButton btn_equal;
    private javax.swing.JButton btn_minimize;
    private javax.swing.JButton btn_multiplication;
    private javax.swing.JButton btn_pow;
    private javax.swing.JButton btn_sqrt;
    private javax.swing.JButton btn_subtraction;
    private javax.swing.JButton btn_sum;
    private javax.swing.JButton btn_theme;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel pnl_TitleBar;
    private javax.swing.JPanel pnl_screen;
    private javax.swing.JTextField txt_mainScreen;
    private javax.swing.JTextField txt_secondScreen;
    // End of variables declaration//GEN-END:variables
}
