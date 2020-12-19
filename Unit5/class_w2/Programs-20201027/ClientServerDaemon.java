
import java.net.*;
import java.io.*;

public class ClientServerDaemon extends Thread {

    DatagramSocket socket = null;
    InetAddress address = null;
    int port;
    boolean running;
    UDPSocket data;

    public ClientServerDaemon(DatagramSocket socket, InetAddress address, int port, UDPSocket data) {
        this.socket = socket;
        this.address = address;
        this.port = port;
        this.data = data;
        start();
    }

    public void run() {
        byte[] buf = new byte[data.BufferSize];
        DatagramPacket packet = null;
        int type, receiveNum;

        packet = new DatagramPacket(buf, buf.length);
        while (data.getIsAlive()) {
            try {
                socket.receive(packet);
                System.out.println("QAQ");
                type = (int) buf[0];
                receiveNum = (int) buf[1];
                receiveNum = receiveNum * 256 + (int) buf[2];
                receiveNum = receiveNum * 256 + (int) buf[3];
                receiveNum = receiveNum * 256 + (int) buf[4];
                receiveNum += 1;
                receiveNum %= (data.mode + 1);
                System.out.println(receiveNum+"---------------------");
                if (type == (int) 'M') {
                    if (receiveNum == data.getReceiveSocketNum()) {
                        byte[] ack_buf = new byte[data.BufferSize+5];
                        ack_buf[0] = (byte) 'A';
                        ack_buf[1] = (byte) ((receiveNum & 0xff000000) >>> 24);
                        ack_buf[2] = (byte) ((receiveNum & 0x00ff0000) >>> 16);
                        ack_buf[3] = (byte) ((receiveNum & 0x0000ff00) >>> 8);
                        ack_buf[4] = (byte) ((receiveNum & 0x000000ff));
                        DatagramPacket ack_packet = new DatagramPacket(ack_buf, ack_buf.length, address, port);
                        socket.send(ack_packet);
                        data.addReceiveSocketNum();
                        data.socketReceive();
                        // server store all daemon data
                    } else {
                        // store data
                    }
                } else if (type == (int) 'A') {
                    data.setAckSocketNum(receiveNum);
                } else if (type == (int) 'F') {
                    socket.close();
                }
                System.out.println(receiveNum + " " + new String(packet.getData()).trim());
            } catch (Exception e) {
                // server timeout
                // client
            }
        }
    }
}