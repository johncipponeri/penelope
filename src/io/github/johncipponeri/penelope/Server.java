package io.github.johncipponeri.penelope;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public abstract class Server extends Network {

    public Server(int port) throws SocketException {
        super(port);
    }

    public Server(int port, boolean server) throws SocketException {
        super(port, server);
    }

    public void sendData(byte[] data, InetAddress address, int port) throws IOException {
        sendPacket(new DatagramPacket(data, data.length, address, port));
    }
}