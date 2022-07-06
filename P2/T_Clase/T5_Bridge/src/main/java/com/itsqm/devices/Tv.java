package com.itsqm.devices;

/**
 *
 * @author Bryan
 */
public class Tv implements Device {

    private boolean on_off = false;
    private int volume = 0;
    private int channel = 0;

    @Override
    public boolean isEnable() {
        return on_off;
    }

    @Override
    public void enable() {
        on_off = true;
    }

    @Override
    public void disable() {
        on_off = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        if (percent > 50) {
            volume = 50;
        } else if (percent < 0) {
            volume = 0;
        } else {
            volume = percent;
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        if (channel > 10) {
            this.channel = 1;
        } else if (channel < 1) {
            this.channel = 10;
        } else {
            this.channel = channel;
        }
    }

    @Override
    public void printStatus() {
        System.out.println("************************************");
        System.out.println("|-> TV");
        System.out.println("| El TV está: " + (on_off ? "Activo" : "Inactivo"));
        System.out.println("| El volumen es: " + volume);
        System.out.println("| La estación es: " + channel);
        System.out.println("************************************");
    }

}
