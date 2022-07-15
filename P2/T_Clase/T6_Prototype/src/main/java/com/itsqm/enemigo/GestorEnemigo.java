package com.itsqm.enemigo;

import com.itsqm.personajes.*;
import java.util.Hashtable;

/**
 *
 * @author Bryan
 */
public class GestorEnemigo {

    private Hashtable hEnemigos = new Hashtable();

    public GestorEnemigo() {
        Enemigo h1 = new Hechicero();
        h1.setNombre("Arutam");
        h1.setArma("Arco y flecha");
        addEnemigo(h1.getNombre(), h1);

        Enemigo g1 = new Guerrero();
        g1.setNombre("Aztrain");
        g1.setArma("Katana");
        addEnemigo(g1.getNombre(), g1);

    }

    public void addEnemigo(String nombre, Enemigo objE) {
        this.hEnemigos.put(nombre, objE);
    }

    public Enemigo getEnemigo(String nombre) {
        Enemigo objE = (Enemigo) this.hEnemigos.get(nombre);
        return objE;
    }

    public Enemigo getClon(String nombre) {
        Enemigo objE = (Enemigo) hEnemigos.get(nombre);
        return objE.clonar();
    }
}
