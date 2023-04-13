package pl.pijok.server;

public class PacketMapper {

    public Packet mapPacket(String temp) {
        String[] parts = temp.split(";");
        Packet packet = null;
        try {
            packet = Packet.builder()
                    .password(parts[0])
                    .title(parts[1])
                    .value(parts[2])
                    .build();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

        return packet;
    }

    public String mapString(Packet packet) {
        return packet.getPassword() + ";" + packet.getTitle() + ";" + packet.getValue();
    }

}
