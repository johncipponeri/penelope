package io.github.johncipponeri.penelope;

import java.io.IOException;
import java.net.*;

public abstract class Client extends Network {

    private InetAddress    serverAddress;

    public Client() throws SocketException, UnknownHostException {
        this("localhost", 1340);
    }

    public Client(String serverAddress, int serverPort) throws UnknownHostException, SocketException {
        super(serverPort);

        this.serverAddress = InetAddress.getByName(serverAddress);
    }

    public void sendData(byte[] data) throws IOException {
        sendPacket(new DatagramPacket(data, data.length, serverAddress, port));
    }
}