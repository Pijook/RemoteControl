package pl.pijok.remotecontrolapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PacketSender {

    private static Socket clientSocket;
    private static PrintWriter printWriter;

    private static String ip;

    private static boolean sendSignal(String signal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                printWriter.println(signal);
            }
        }).start();

        return true;
    }

    public static void handleLeftMoseButton() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("leftMouseButton");
    }

    public static void handleRightMoseButton() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("rightMouseButton");
    }

    public static void handleSpace() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("space");
    }

    public static void handleEnter() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("enter");
    }

    public static void handleUpArrow() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("up");
    }

    public static void handleDownArrow() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("down");
    }

    public static void handleLeftArrow() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("left");
    }

    public static void handleRightArrow() {
        if(!checkConnection()) {
            return;
        }
        sendSignal("right");
    }

    public static boolean checkConnection() {
        return clientSocket != null && clientSocket.isConnected() && !clientSocket.isClosed();
    }

    public static void setIp(String ip) {
        PacketSender.ip = ip;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clientSocket = new Socket(ip, 56423);
                    printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                } catch (IOException e) {
                    System.out.println(e);
                    return;
                }
            }
        }).start();
    }
}
