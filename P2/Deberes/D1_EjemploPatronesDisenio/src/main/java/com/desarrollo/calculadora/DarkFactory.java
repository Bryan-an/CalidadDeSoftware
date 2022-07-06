/*
 * Calculadora - JFrame
 */
package com.desarrollo.calculadora;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author Bryan Andagoya
 */
public class DarkFactory implements CalculatorFactory {

    //Fields
    JButton button;
    JPanel panel;
    JTextField textField;

    //Methods
    public JButton configureSymbolButton(JButton button) {
        button.setFont(new Font("Cambria Math", 1, 18));
        button.setForeground(new Color(38, 205, 220));
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/darkSymbolsButton.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/darkOver.png"));

        return button;
    }

    public JButton configureNumberButton(JButton button) {
        button.setFont(new Font("Cambria Math", 1, 18));
        button.setForeground(new Color(97, 176, 183));
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/darkNumbers.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/darkOver.png"));

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
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/darkNumbers.png"));
        button.setFont(new Font("Cambria Math", 1, 18));
        button.setForeground(new Color(204, 98, 63));
        button.setText("AC");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button.setRolloverIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonAC2.png"));

        return button;
    }

    @Override
    public JButton createThemeButton() {
        button = new JButton();
        button.setIcon(new ImageIcon("src/main/java/com/desarrollo/images/buttonDark-Light.png"));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        return button;
    }

    @Override
    public JPanel createMainPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(0, 12, 13));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JPanel createTitleBarPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(0, 12, 13));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JPanel createScreenPanel() {
        panel = new JPanel();
        panel.setBackground(new Color(0, 27, 54));
        panel.setLayout(new AbsoluteLayout());

        return panel;
    }

    @Override
    public JTextField createMainScreen() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(new Color(0, 27, 54));
        textField.setFont(new Font("Cambria Math", 1, 28));
        textField.setForeground(new Color(242, 252, 255));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setText("0");
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 15));

        return textField;
    }

    @Override
    public JTextField createSecondScreen() {
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(new Color(0, 27, 54));
        textField.setFont(new Font("Cambria Math", 1, 18));
        textField.setForeground(new Color(216, 238, 245));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 15));

        return textField;
    }

}
