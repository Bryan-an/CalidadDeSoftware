/*
 * 
 */
package com.desarrollo.calculadora;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

/**
 *
 * @author Bryan Andagoya
 */
public class LightFactory implements CalculatorFactory {

    //Fields
    JButton button;
    JPanel panel;
    JTextField textField;

    //Methods
    public JButton configureSymbolButton(JButton button) {
        button.setFont(new Font("Cambria Math", 1, 18)); // NOI18N
        button.setForeground(new Color(15, 70, 82));
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));

        return button;
    }

    public JButton configureNumberButton(JButton button) {
        button.setFont(new Font("Cambria Math", 1, 18)); // NOI18N
        button.setForeground(new Color(15, 70, 82));
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setPressedIcon(new ImageIcon("src/main/java/com/desarrollo/images/button3.png"));
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/button2.png"));

        return button;
    }

    //Implemented methods
    @Override
    public JButton createButton0() {
        button = new JButton();
        button.setText("0");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton1() {
        button = new JButton();
        button.setText("1");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton2() {
        button = new JButton();
        button.setText("2");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton3() {
        button = new JButton();
        button.setText("3");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton4() {
        button = new JButton();
        button.setText("4");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton5() {
        button = new JButton();
        button.setText("5");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton6() {
        button = new JButton();
        button.setText("6");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton7() {
        button = new JButton();
        button.setText("7");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton8() {
        button = new JButton();
        button.setText("8");

        return configureNumberButton(button);
    }

    @Override
    public JButton createButton9() {
        button = new JButton();
        button.setText("9");

        return configureNumberButton(button);
    }

    @Override
    public JButton createDivisionButton() {
        button = new JButton();
        button.setText("÷");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createMultiplicationButton() {
        button = new JButton();
        button.setText("X");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createSubtractionButton() {
        button = new JButton();
        button.setText("-");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createSqrtButton() {
        button = new JButton();
        button.setText("√");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createSumButton() {
        button = new JButton();
        button.setText("+");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createPowButton() {
        button = new JButton();
        button.setText("^");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createEqualButton() {
        button = new JButton();
        button.setText("=");

        return configureSymbolButton(button);
    }

    @Override
    public JButton createCommaButton() {
        button = new JButton();
        button.setText(",");

        return configureNumberButton(button);
    }

    @Override
    public JButton createClearButton() {
        button = new JButton();
        button.setFont(new Font("Cambria Math", 1, 18)); // NOI18N
        button.setForeground(new Color(15, 70, 82));
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonAC.png"));
        button.setText("AC");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonAC2.png"));

        return button;
    }

    @Override
    public JButton createThemeButton() {
        button = new JButton();
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonLight-Dark.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        return button;
    }

    @Override
    public JPanel createMainPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(221, 253, 255));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JPanel createTitleBarPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(221, 253, 255));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JPanel createScreenPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(244, 254, 255));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JTextField createMainScreen() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(new Color(244, 254, 255));
        textField.setFont(new Font("Cambria Math", 1, 28));
        textField.setForeground(new Color(35, 116, 140));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setText("0");
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));

        return textField;
    }

    @Override
    public JTextField createSecondScreen() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(new Color(244, 254, 255));
        textField.setFont(new Font("Cambria Math", 1, 18));
        textField.setForeground(new Color(139, 193, 209));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 15));

        return textField;
    }

}
