package it.unibo.radarSystem22.mytest;

import it.unibo.radarSystem22.interfaces.Interaction;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPInteraction implements Interaction {

    private Socket socket;
    private DataOutputStream doStream;
    private DataInputStream diStream;

    public TCPInteraction(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.doStream = new DataOutputStream(socket.getOutputStream());
        this.diStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void forward(String msg) throws Exception {
        doStream.writeUTF(msg);
        System.out.println("(forward) Inviato: " + msg);
    }

    @Override
    public String request(String msg) throws Exception {
        doStream.writeUTF(msg);
        System.out.println("(request) Inviato: " + msg);

        String res = null;
        while(res == null) {
            res = diStream.readUTF();
        }
        System.out.println("(request) Ricevuto: " + msg);

        return res;
    }

    @Override
    public String receiveMsg() throws Exception {
        String res = null;

        while(res == null) {
            res = diStream.readUTF();
        }
        System.out.println("(receiveMsg) Ricevuto: " + res);

        return res;
    }

    @Override
    public void close() throws Exception {
        this.socket.close();
    }
}
