package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.domain.ILed;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RaspServerThread extends Thread {

    private Socket socket;
    private ILed led;

    public RaspServerThread(Socket socket, ILed led) {
        this.socket = socket;
        this.led = led;
    }

    @Override
    public void run() {
        DataInputStream diStream = null;
        DataOutputStream doStream = null;

        try {
            diStream = new DataInputStream(socket.getInputStream());
            doStream = new DataOutputStream(socket.getOutputStream());

            while(socket.isConnected()) {
                String msg = diStream.readUTF();

                if(msg != null) {
                    System.out.println("Server: received " + msg);

                    switch (msg) {
                        case "turnOn":
                            led.turnOn();
                            break;
                        case "turnOff":
                            led.turnOff();
                            break;
                        case "state":
                            String res = led.getState() ? "on" : "off";
                            doStream.writeUTF(res);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
