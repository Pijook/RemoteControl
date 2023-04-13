package pl.pijok.server;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static Robot robot;

    public static void main(String[] args) {
        generatePassword();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        int port = 56423;

        PacketListener packetListener = new PacketListener(port);
        packetListener.start();

        System.out.println("Server started");
        System.out.println("Password: " + Storage.PASSWORD);
    }

    private static void generatePassword() {
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 4; i++) {
            result = result + random.nextInt(10);
        }

        Storage.PASSWORD = result;
    }

}
