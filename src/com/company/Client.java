package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String IP;
    private int PORT;
    private Socket socket;
    private BufferedReader clientInput;
    private BufferedReader fromServer;
    private PrintWriter writeToServer;
    private String name;

    public Client(Socket socket, String name) {
        this.socket = socket;
        this.name = name;

        try {
            this.clientInput = new BufferedReader(new InputStreamReader(System.in));
            this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writeToServer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException var4) {
            this.closeSocket();
        }

    }

    public void closeSocket() {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void sendToServer(String message) throws IOException {
        this.writeToServer.println(message);
    }

    public String getFromServer() throws IOException {
        return this.fromServer.readLine();
    }

    public String getIP() {
        return this.IP;
    }

    public int getPORT() {
        return this.PORT;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getClientInput() {
        return this.clientInput;
    }

    public PrintWriter getWriteToServer() {
        return this.writeToServer;
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket("localhost", 6969), "Person1");
        System.out.print("Enter something: ");
        String userInput = client.getClientInput().readLine();
        client.sendToServer(userInput);
        String in = client.getFromServer();
        PrintStream var10000 = System.out;
        String var10001 = client.getName();
        var10000.println(var10001 + ": " + in);
    }
}