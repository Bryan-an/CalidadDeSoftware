package com.itsqm.principal;

import com.itsqm.devices.*;
import com.itsqm.remotes.BasicRemote;
import com.itsqm.remotes.Remote;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Bryan
 */
public class Principal {

    private BufferedReader input = new BufferedReader(
            new InputStreamReader(System.in));
    private boolean finish = false;
    private Device device;
    private Remote remote;

    public static void testDevice(Device d) {
        System.out.println("Probar control remoto básico");
        BasicRemote br = new BasicRemote(d);
        d.printStatus();
        br.power();
        d.printStatus();
    }

    public static void main(String[] args) {
//        testDevice(new Tv());

        /*
            switch ? tv : radio
            switch ? power : volume : channel
         */
        new Principal().run();
    }

    public void run() {
        System.out.println("*************Patrón Bridge****************");

        do {
            device = askForDevice();

            if (!finish) {
                remote = new BasicRemote(device);
                askForOperation();
            }

        } while (!finish);

        System.out.println("*****************Fin del progrma****************");
    }

    public Device askForDevice() {

        while (true) {
            System.out.println("¿Qué dispositivo deseas utilar?");
            System.out.println("1.Radio");
            System.out.println("2.TV");
            System.out.println("3.Finalizar programa");
            System.out.println("Digita el número de la opción deseada:");

            try {
                int option = Integer.parseInt(input.readLine());

                switch (option) {
                    case 1 -> {
                        return new Radio();
                    }
                    case 2 -> {
                        return new Tv();
                    }
                    case 3 -> {
                        finish = true;
                        return null;
                    }
                    default ->
                        throw new Exception();
                }

            } catch (Exception ex) {
                System.out.println("Opción inválida");
            }
        }
    }

    public void askForOperation() {
        int option = 0;

        do {
            System.out.println("¿Qué operación desea realizar?");
            System.out.println("1.Power");
            System.out.println("2.Subir volumen");
            System.out.println("3.Bajar volumen");
            System.out.println("4.Subir canal");
            System.out.println("5.Bajar canal");
            System.out.println("6.Salir");
            System.out.println("Digita el número de la opción deseada:");

            try {
                option = Integer.parseInt(input.readLine());

                if (option == 1) {
                    remote.power();
                    device.printStatus();
                } else if (option != 6) {
                    if (device.isEnable()) {
                        switch (option) {
                            case 2 -> {
                                remote.volumeUp();
                            }
                            case 3 -> {
                                remote.volumeDown();
                            }
                            case 4 -> {
                                remote.channelUp();
                            }
                            case 5 -> {
                                remote.channelDown();
                            }
                            default ->
                                throw new Exception();
                        }

                        device.printStatus();
                    } else {
                        System.out.println("El disposito se encuentra INACTIVO");
                    }
                }

            } catch (Exception ex) {
                System.out.println("Opción inválida");
            }

        } while (option != 6);
    }

}
