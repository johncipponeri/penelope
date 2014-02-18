package io.github.johncipponeri.penelope;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public abstract class Network extends Thread {

    protected int            port;
    private   DatagramSocket socket;
    private   boolean        running;

    public Network(int port) throws SocketException {
        this(port, false);
    }

    public Network(int port, boolean server) throws SocketException {
        socket = (server) ? new DatagramSocket(port) : new DatagramSocket();
        this.port = port;
        this.running = false;
    }

    public void connect() {
        if (!running) {
            running = true;
            this.start();
        }
    }

    public void disconnect() throws InterruptedException {
        if (running) {
            running = false;
            this.join();
        }
    }

    @Override
    public void run() {
        byte[] data;
        DatagramPacket packet;

        // TODO: Update Speed
        while (running) {
            // TODO: Buffer system
            data = new byte[1024];
            packet = new DatagramPacket(data, data.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            parsePacket(data, packet.getAddress(), packet.getPort());
        }
    }

    protected abstract void parsePacket(byte[] data, InetAddress address, int port);

    public void sendPacket(DatagramPacket packet) throws IOException {
        socket.send(packet);
    }
}