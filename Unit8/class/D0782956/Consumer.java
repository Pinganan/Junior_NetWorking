
//*******************************************************************
//*  Network Programming - Unit 8 Non-blocking Socket               *
//*  Program Name: MultiPortClient                                  *
//*  The program creates a nonblocking mode socket.                 *
//*  The program connects to multi-port server and send/receive     *
//*     message.                                                    *
//*  The program gets the server IP from args[0], port from args[1].*
//*  The first message to send is arg[2] and the second message     *
//*     to send is args[3].                                         *
//*  2016.02.04                                                     *
//*******************************************************************
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.net.*;
import java.io.*;
  
import javax.swing.*;
import java.awt.*;
class Consumer {

    

    public static void main(String args[]) {

        String ip = JOptionPane.showInputDialog("ip for client", "127.0.0.1");
		int num = Integer.parseInt(JOptionPane.showInputDialog("port for client", "8881"));

        GUI g = new GUI("c");

        try {
            // Creates a socket channel
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(ip, num));

            // if the socket has connected, sc.finishConnect() will return false
            for (int loopcount = 0; !sc.finishConnect(); loopcount++) {
                // do something
                System.out.println("Loop count = " + loopcount);
                g.setResponse("Loop count = " + loopcount + "\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
            // Connection established
            int count = 2000;
            // messages from args[2] and args[3]
            while (true) // messages from args[2] and args[3]
            {
                if (count > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count -= 20;
                }
                else {
                    count = 2000;
                    System.out.println("data waiting");
                    g.setResponse("data waiting\n");
                }
				// Receive message
				ByteBuffer 	b = ByteBuffer.allocate(1000); 
				int	len = sc.read(b);	// read message from sc (non-blocking)
				if(len > 0) {
                    count = 2000;
					System.out.println("Receive " + (count-1) + "th message : "	+ new String(b.array(), 0, len));
                    g.setResponse("Receive " + (count-1) + "th message : "	+ new String(b.array(), 0, len) + "\n");
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}