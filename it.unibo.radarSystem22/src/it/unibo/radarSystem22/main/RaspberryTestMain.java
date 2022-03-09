package it.unibo.radarSystem22.main;

import it.unibo.radarSystem22.domain.ILed;
import it.unibo.radarSystem22.mytest.LedEnabler;
import it.unibo.radarSystem22.mytest.RaspServerThread;

import java.net.ServerSocket;
import java.net.Socket;

public class RaspberryTestMain {

    public static final int PORT = 6666;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setReuseAddress(true);

            while(true) {
                System.out.println("Server: in attesa di richieste");

                clientSocket = serverSocket.accept();
                clientSocket.setSoTimeout(30000);
                System.out.println("Server: connessione accettata " + clientSocket);

                try {
                    new RaspServerThread(clientSocket, new LedEnabler()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
