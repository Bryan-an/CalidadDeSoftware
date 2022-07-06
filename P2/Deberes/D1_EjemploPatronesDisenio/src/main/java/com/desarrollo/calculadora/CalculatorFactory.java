/*
 * Calculadora - JFrame
 */
package com.desarrollo.calculadora;

import javax.swing.*;

/**
 *
 * @author Bryan Andagoya
 */
public interface CalculatorFactory {

    //Buttons
    JButton createButton0();

    JButton createButton1();

    JButton createButton2();

    JButton createButton3();

    JButton createButton4();

    JButton createButton5();

    JButton createButton6();

    JButton createButton7();

    JButton createButton8();

    JButton createButton9();

    JButton createDivisionButton();

    JButton createMultiplicationButton();

    JButton createSubtractionButton();

    JButton createSqrtButton();

    JButton createSumButton();

    JButton createPowButton();

    JButton createEqualButton();

    JButton createCommaButton();

    JButton createClearButton();

    JButton createThemeButton();

    //Panels
    JPanel createMainPanel();

    JPanel createTitleBarPanel();

    JPanel createScreenPanel();

    //Text fields
    JTextField createMainScreen();

    JTextField createSecondScreen();

}
