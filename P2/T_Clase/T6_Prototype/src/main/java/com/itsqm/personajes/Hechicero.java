package com.itsqm.personajes;

import com.itsqm.enemigo.Enemigo;

/**
 *
 * @author Bryan
 */
public class Hechicero extends Enemigo {

    public Hechicero() {
        System.out.println("Personaje Hechicero creado!");
        this.setHealth(88);
    }

    @Override
    public void atacar() {
        System.out.println("El Hechicero ataca");

        int newHealth = this.getHealth() - 6;

        if (newHealth < 0) {
            this.setHealth(0);
            System.out.println("El hechicero ha muerto");
        } else {
            this.setHealth(newHealth);
        }
    }

    @Override
    public void defender() {
        System.out.println("El Hechicero defiende");

        int newHealth = this.getHealth() + 4;

        if (newHealth > 88) {
            this.setHealth(88);
            System.out.println("El hechicero se ha recuperado por completo");
        } else {
            this.setHealth(newHealth);
        }
    }

    @Override
    public Enemigo clonar() {
        Hechicero h = new Hechicero();
        h.setNombre(this.getNombre());
        h.setArma(this.getArma());

        return h;
    }

}
