package pl.pijok.server;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient extends Thread {

    private final PacketMapper mapper;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    private final int moveByPixels = 50;

    public ServerClient(PacketMapper mapper, Socket socket) {
        this.clientSocket = socket;
        this.mapper = mapper;

        System.out.println("Creating client!");
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputLine = null;
        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }
                return;
            }

            Packet packet = mapper.mapPacket(inputLine);

            if(packet == null) {
                return;
            }

            if(!packet.getPassword().equals(Storage.PASSWORD)) {
                return;
            }

            switch (packet.getTitle()) {
                case "leftMouseButton" -> {
                    Main.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    Main.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
                case "rightMouseButton" -> {
                    Main.robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    Main.robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                }
                case "space" -> {
                    Main.robot.keyPress(32);
                }
                case "enter" -> {
                    Main.robot.keyPress(10);
                }
                case "up" -> {
                    moveMouseBy(0,-moveByPixels);
                }
                case "down" -> {
                    moveMouseBy(0,moveByPixels);
                }
                case "left" -> {
                    moveMouseBy(-moveByPixels, 0);
                }
                case "right" -> {
                    moveMouseBy(moveByPixels, 0);
                }
            }
            System.out.println(inputLine);
        }

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
        try {
            clientSocket.close();
        } catch (IOException e) {
            return;
        }
    }

    private void moveMouseBy(int x, int y){
        Main.robot.mouseMove(
                (int) MouseInfo.getPointerInfo().getLocation().getX() + x,
                (int) MouseInfo.getPointerInfo().getLocation().getY() + y
        );
    }

}
