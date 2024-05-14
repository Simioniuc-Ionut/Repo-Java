package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private final String serverAddress;
    private final int serverPort;

    public GameClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void start() {
        try (
                Socket socket = new Socket(serverAddress, serverPort);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to server. Type 'exit' to quit.");

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);

                // Dacă comanda este "exit", ieșim din bucla de citire
                if (userInputLine.equalsIgnoreCase("exit")) {
                    // Citim și afișăm răspunsul de la server
                    String serverResponse = in.readLine();
                    System.out.println("Server response: " + serverResponse);
                    System.out.println("Client response: Client exit");
                    break;
                }

                // Citim și afișăm răspunsul de la server
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 12345; // Change this to the server's port

        GameClient client = new GameClient(serverAddress, serverPort);
        client.start();
    }
}