package com.itsqm.remotes;

import com.itsqm.devices.Device;

/**
 *
 * @author Bryan
 */
public class BasicRemote implements Remote {

    protected Device d;

    public BasicRemote(Device d) {
        this.d = d;
    }

    @Override
    public void power() {
        System.out.println(">Remote: Has presionado el power");

        if (d.isEnable()) {
            d.disable();
        } else {
            d.enable();
        }
    }

    @Override
    public void volumeUp() {
        System.out.println(">Remote: volume up");

        d.setVolume(d.getVolume() + 10);
    }

    @Override
    public void volumeDown() {
        System.out.println(">Remote: volume down");

        d.setVolume(d.getVolume() - 10);
    }

    @Override
    public void channelUp() {
        System.out.println(">Remote: channel up");

        d.setChannel(d.getChannel() + 1);
    }

    @Override
    public void channelDown() {
        System.out.println(">Remote: channel down");

        d.setChannel(d.getChannel() - 1);
    }

}
