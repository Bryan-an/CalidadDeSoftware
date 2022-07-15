package com.itsqm.principal;

import com.itsqm.enemigo.Enemigo;
import com.itsqm.enemigo.GestorEnemigo;

/**
 *
 * @author Bryan
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorEnemigo ge = new GestorEnemigo();
        Enemigo g1 = ge.getEnemigo("Aztrain");

        System.out.println("*************************************");
        System.out.println(">El guerrero original se llama:");
        System.out.println(">" + g1.getNombre());
        System.out.println(">Su arma original es:");
        System.out.println(">" + g1.getArma());
        System.out.println("*************************************");

        Enemigo g2 = ge.getClon("Aztrain");
        System.out.println(">Nombre: " + g2.getNombre());
        System.out.println(">Arma: " + g2.getArma());
        System.out.println("*************************************");

        g2.setNombre("Aztrain2");
        g2.setArma("Espada");
        System.out.println("Al modificar el guerrero tenemos");
        System.out.println(">Nombre del clon: " + g2.getNombre());
        System.out.println(">Arma del clon: " + g2.getArma());

        System.out.println("*************************************");
        g2.atacar();
        System.out.println(">Salud del clon: " + g2.getHealth());
        g2.defender();
        System.out.println(">Salud del clon: " + g2.getHealth());

        Enemigo h1 = ge.getEnemigo("Arutam");

        System.out.println("*************************************");
        System.out.println(">El hechicero original se llama:");
        System.out.println(">" + h1.getNombre());
        System.out.println(">Su arma original es:");
        System.out.println(">" + h1.getArma());
        System.out.println("*************************************");

        Enemigo h2 = ge.getClon("Arutam");
        System.out.println(">Nombre: " + h2.getNombre());
        System.out.println(">Arma: " + h2.getArma());
        System.out.println("*************************************");

        h2.setNombre("Arutam2");
        h2.setArma("Hechizo");
        System.out.println("Al modificar el hechicero tenemos");
        System.out.println(">Nombre del clon: " + h2.getNombre());
        System.out.println(">Arma del clon: " + h2.getArma());

        System.out.println("*************************************");
        h2.atacar();
        System.out.println(">Salud del clon: " + h2.getHealth());
        h2.defender();
        System.out.println(">Salud del clon: " + h2.getHealth());
        h2.defender();
        System.out.println(">Salud del clon: " + h2.getHealth());
    }

}
