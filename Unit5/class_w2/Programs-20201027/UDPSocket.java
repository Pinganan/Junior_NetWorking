
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class UDPSocket {

    public static final int SocketIdle = 100; // 1 second
    public static final int SleepIdle = 10; // 0.1 sec
    public static final int BufferSize = 1000;
    public static final boolean Debug = true;

    DatagramSocket socket = null;
    boolean isServer = false, needWait = false, isAlive = true;
    InetAddress address = InetAddress.getByName("127.0.0.1");
    int port, mode = 3, checkNum = 3, lastNum=0;
    int sendSocketNum, receiveSocketNum, ackSocketNum;
    ClientServerDaemon daemon = null;
    byte[] buffer = new byte[BufferSize];
    ArrayList<byte[]> data = new ArrayList<byte[]>();

    public UDPSocket(int port) throws Exception {
        socket = new DatagramSocket(port);
        isServer = true;
        receiveSocketNum = mode - 1;
    }

    public UDPSocket(InetAddress address, int port) throws Exception {
        socket = new DatagramSocket();
        this.address = address;
        this.port = port;
        isServer = true;
        sendSocketNum = 0;
        checkNum = mode;
    }

    public void serverAccept() throws Exception {
        DatagramPacket packet = null;
        String sendStr = "SYN/ACK";
        socket.setSoTimeout(0);
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        this.address = packet.getAddress();
        this.port = packet.getPort();
        buffer = sendStr.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        socket.setSoTimeout(500);
        ClientServerDaemon daemon = new ClientServerDaemon(socket, address, port, this);
    }

    public void clientConnect() throws Exception {
        DatagramPacket packet = null;
        String sendStr = "SYN";
        socket.setSoTimeout(0);
        buffer = sendStr.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        sendStr = "ACK";
        buffer = sendStr.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
        socket.setSoTimeout(500);
        ClientServerDaemon daemon = new ClientServerDaemon(socket, address, port, this);
    }

    public void socketReceive() throws Exception {

    }

    synchronized public void setAckSocketNum(int num) {
        this.ackSocketNum = num;
    }

    synchronized public int getAckSocketNum() {
        return this.ackSocketNum;
    }

    synchronized public int getSendSocketNum() {
        return this.sendSocketNum;
    }

    synchronized public int getReceiveSocketNum() {
        return this.receiveSocketNum;
    }

    synchronized public void addSendSocketNum() {
        this.sendSocketNum++;
        this.sendSocketNum %= (mode + 1);
    }

    synchronized public void addReceiveSocketNum() {
        this.receiveSocketNum += 3;
        this.receiveSocketNum %= (mode + 1);
    }

    synchronized public boolean getIsAlive() {
        return this.isAlive;
    }

    public void socketSend(byte[] temp, int length) throws Exception {
        DatagramPacket sendPacket = null;
        byte[] sendbuf = new byte[BufferSize];
        int i, sendNum, j;
        data.add(temp);
        this.isAlive = true;
        sendNum = getSendSocketNum();
        for (i = 0; i < temp.length; i++)
            sendbuf[5 + i] = temp[i];
        sendbuf[0] = (byte) 'M';
        sendbuf[1] = (byte) ((sendNum & 0xff000000) >>> 24);
        sendbuf[2] = (byte) ((sendNum & 0x00ff0000) >>> 16);
        sendbuf[3] = (byte) ((sendNum & 0x0000ff00) >>> 8);
        sendbuf[4] = (byte) ((sendNum & 0x000000ff));
        length += 5;
        sendPacket = new DatagramPacket(sendbuf, length, address, port);
        socket.send(sendPacket);
        addSendSocketNum();
        if (getSendSocketNum() == checkNum) {
            checkNum = (checkNum + mode) % (mode + 1);
            needWait = true;
        }
        int count = 10;
        boolean isBug = false;
        while (needWait) {
            try {
                Thread.sleep(100);
            } catch (Exception d) {
            }
            if (getSendSocketNum() == getAckSocketNum()) {
                needWait = false;
                this.isAlive = false;
                data.clear();
                lastNum = getSendSocketNum();
            }
            count--;
            if (count < 0) {
                isBug = true;
                needWait = false;
            }
        }
        if (isBug) {
            sendSocketNum = lastNum;
            checkNum = lastNum+3;
            for (i = 0; i < mode; i++) {
                this.socketSend(data.get(i), data.get(i).length);
                System.out.println("Send : " + new String(data.get(i)));
            }
        }
    }

    public void close() throws Exception {
		DatagramPacket packet;
		byte[] buf = new byte[BufferSize + 5];
		int sn_send;

		sn_send = getSendSocketNum();

		// first 5 bytes are F, sn_send
		buf[0] = (byte) 'F';
		buf[1] = (byte) ((sn_send & 0xff000000) >>> 24);
		buf[2] = (byte) ((sn_send & 0x00ff0000) >>> 16);
		buf[3] = (byte) ((sn_send & 0x0000ff00) >>> 8);
		buf[4] = (byte) ((sn_send & 0x000000ff));

		packet = new DatagramPacket(buf, buf.length, address, port);
		socket.send(packet);
		socket.close();
	}
}