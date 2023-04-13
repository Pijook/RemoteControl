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
    private PacketMapper packetMapper;
    private final int port;

    public PacketListener(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("Creating new socket");
            serverSocket = new ServerSocket(port);
            packetMapper = new PacketMapper();
            while (true) {
                new ServerClient(packetMapper, serverSocket.accept()).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
