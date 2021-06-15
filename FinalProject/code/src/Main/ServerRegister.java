package Main;

import java.net.*;
import java.io.*;
import java.net.Socket;

public class ServerRegister extends Thread {
    String datapath = "user.txt";
    ServerSocket serverSocket = null;
    int port = 8888;
    DataInputStream in = null;
    DataOutputStream out = null;
    boolean flag = true;

    public void run() {
        Socket socket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true) {
            try {
                socket = serverSocket.accept();
                // receive number of role of choice from player
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                String token[] = new String(in.readUTF()).split(",");
                String username = token[0];
                String password = token[1];
                File filename = new File(datapath);
                // read
                InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
                BufferedReader br = new BufferedReader(reader);
                String line = "";
                line = br.readLine();// first username
                while (line != null) {
                    if (line.equals(username)) {
                        flag = false;
                        break;
                    } else {
                        line = br.readLine();// this password
                    }
                    line = br.readLine();// next username
                }
                if (flag) {
                    // write
                    BufferedWriter output = new BufferedWriter(new FileWriter(filename, true));
                    output.write(username + "\r\n" + password + "\r\n");
                    output.flush();
                    output.close();
                }
                in.close();
                out.close();
                socket.close();
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
