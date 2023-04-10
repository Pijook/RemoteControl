package pl.pijok.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;

public class PacketListener extends Thread {

    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Starting server");
        while (true) {
            new ServerClient(serverSocket.accept()).start();
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
