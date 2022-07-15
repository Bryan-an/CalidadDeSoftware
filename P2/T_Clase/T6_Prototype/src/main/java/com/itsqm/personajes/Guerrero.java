package com.itsqm.personajes;

import com.itsqm.enemigo.Enemigo;

/**
 *
 * @author Bryan
 */
public class Guerrero extends Enemigo {

    public Guerrero() {
        System.out.println("Personaje Guerrero creado!");
        this.setHealth(100);
    }

    @Override
    public void atacar() {
        System.out.println("El guerrero ataca");

        int newHealth = this.getHealth() - 10;

        if (newHealth < 0) {
            this.setHealth(0);
            System.out.println("El guerrero ha muerto");
        } else {
            this.setHealth(newHealth);
        }
    }

    @Override
    public void defender() {
        System.out.println("El guerrero defiende");

        int newHealth = this.getHealth() + 5;

        if (newHealth > 100) {
            this.setHealth(100);
            System.out.println("El guerrero se ha recuperado por completo");
        } else {
            this.setHealth(newHealth);
        }
    }

    @Override
    public Enemigo clonar() {
        Guerrero g = new Guerrero();
        g.setNombre(this.getNombre());
        g.setArma(this.getArma());

        return g;
    }

}
