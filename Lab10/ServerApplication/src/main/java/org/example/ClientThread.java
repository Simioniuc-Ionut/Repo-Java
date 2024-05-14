package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final GameServer gameServer;

    public ClientThread(Socket clientSocket, GameServer gameServer) {
        this.clientSocket = clientSocket;
        this.gameServer = gameServer;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server received: " + inputLine);

                if (inputLine.equals("stop") || inputLine.equals("exit")) {
                    out.println("Server stopped");
                    gameServer.stop();
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}