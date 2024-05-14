package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class GameServer {
    private int port;
    private volatile boolean isRunning;
    private ServerSocket serverSocket;

    public GameServer(int port) {
        this.port = port;
        this.isRunning = false;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            System.out.println("Server started. Waiting for clients...");

            while (isRunning) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected");
                    new ClientThread(clientSocket, this).start();
                } catch (SocketException e) {
                    if (isRunning) {
                        System.out.println("Error in client connection");
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.out.println("Error in client connection");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error in client connection");
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error when closing server socket");
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
        System.out.println("Server stopped.");
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error when closing server socket");
            e.printStackTrace();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public static void main(String[] args) {
        int serverPort = 12345;
        GameServer server = new GameServer(serverPort);
        server.start();
    }
}