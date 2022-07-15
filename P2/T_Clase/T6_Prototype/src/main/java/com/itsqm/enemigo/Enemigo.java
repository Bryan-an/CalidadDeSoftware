package com.itsqm.enemigo;

/**
 *
 * @author Bryan
 */
public abstract class Enemigo {

    private String nombre;
    private String arma;
    private int health;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract void atacar();

    public abstract void defender();

    public abstract Enemigo clonar();
}
