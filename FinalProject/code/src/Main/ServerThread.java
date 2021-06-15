package Main;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket s1 = null;
    DataInputStream in = null;
    DataOutputStream out = null;

    // self read socket & other write socket
    public ServerThread(Socket s1, Socket s2) {
        try {
            in = new DataInputStream(s1.getInputStream());
            out = new DataOutputStream(s2.getOutputStream());
            this.s1 = s1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        boolean runflag = true;
        while (runflag) {
            try {
                int number = in.readInt();
                out.writeInt(number);
            } catch (IOException e) {
                //e.printStackTrace();
                runflag = false;
                //System.out.println("waiting connect");
            }
        }
        try {
            in.close();
            out.close();
            s1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
