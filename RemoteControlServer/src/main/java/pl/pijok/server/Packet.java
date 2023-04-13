package pl.pijok.server;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Packet {

    private final String password;
    private final String title;
    private final String value;

}
