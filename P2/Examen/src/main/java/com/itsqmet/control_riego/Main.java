package com.itsqmet.control_riego;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 *
 * @author Bryan
 */
public class Main {

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private boolean exit = false;
    private double sprinklerCapacity;
    private double liquidCapacity;
    private double wateringTime;
    private DecimalFormat dt = new DecimalFormat("0.00");

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        System.out.println("Examen Segundo Parcial");

        do {
            sprinklerCapacity = askForInt("Ingrese la cantidad del esparsor (l/h):");
            liquidCapacity = askForInt("Ingrese la cantidad de agua o líquido a sumistrar (l):");

            wateringTime = liquidCapacity / sprinklerCapacity;

            System.out.println("El sistema tardará " + (dt.format(wateringTime)) + " horas en suministrar el líquido.");

            try {
                System.out.println("Desea finalizar el programa? (si/no)");
                exit = input.readLine().equals("si");
            } catch (Exception e) {
                System.out.println("Dato inválido");
            }
        } while (!exit);
    }

    public int askForInt(String instruction) {
        while (true) {
            try {
                System.out.println(instruction);
                int data = Integer.parseInt(input.readLine());

                if (data > 0) {
                    return data;
                }

                throw new Exception();
            } catch (Exception e) {
                System.out.println("Dato inválido");
            }
        }
    }
}
