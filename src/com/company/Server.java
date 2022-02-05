package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader sentData;
    private PrintWriter writeToClient;
    private int port;

    public Server(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection!");
        this.serverSocket = serverSocket;
    }

    public void startServer() throws IOException {
        if (!this.serverSocket.isClosed()) {
            this.socket = this.serverSocket.accept();
            System.out.println("Client connected");
            this.startStreams();
        }

    }

    public void startStreams() throws IOException {
        this.sentData = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writeToClient = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public void sentToClient(String message) throws IOException {
        this.writeToClient.println(message);
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public BufferedReader getSentData() {
        return this.sentData;
    }

    public PrintWriter getWriteToClient() {
        return this.writeToClient;
    }

    public int getPort() {
        return this.port;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(new ServerSocket(6969));
        server.startServer();
        String in = server.getSentData().readLine();
        server.sentToClient(in);
    }
}