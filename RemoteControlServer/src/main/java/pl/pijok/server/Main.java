package pl.pijok.server;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static Robot robot;

    public static void main(String[] args) {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        PacketListener packetListener = new PacketListener();
        try {
            packetListener.start(56423);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
