package com.itsqmet.cbdd;

/**
 *
 * @author Bryan
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configuracion c = Configuracion.getConfiguracion("localhost", "facturador", "root");

        System.out.println("-->" + c.getUrl());
        System.out.println("-->" + c.getBaseDatos());
        System.out.println("-->" + c.getUsuario());

        System.out.println("");

        Configuracion c1 = Configuracion.getConfiguracion("localhost:3306", "Vuelos", "cma");

        System.out.println("-->" + c1.getUrl());
        System.out.println("-->" + c1.getBaseDatos());
        System.out.println("-->" + c1.getUsuario());
    }

}
