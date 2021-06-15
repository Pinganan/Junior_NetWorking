package Main;

import java.net.*;
import java.io.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Server {

    public final static int PLAYER_AMOINT = 2;
    public static Socket[] sockets = new Socket[2];

    public static void main(String args[]) {
        String datapath = "user.txt";
        ServerSocket serverSocket = null;
        int port = 6666, count = 0, p1 = 0, p2 = 0;
        DataInputStream in = null;
        DataOutputStream out = null;

        ServerRegister sRegister  = new ServerRegister();
        sRegister.start();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("waiting connect");
            while (true) {
                sockets[0] = null;
                sockets[1] = null;
                count = 0;
                while (count < PLAYER_AMOINT) {
                    try {
                        sockets[count] = serverSocket.accept();
                        // receive number of role of choice from player
                        in = new DataInputStream(sockets[count].getInputStream());
                        out = new DataOutputStream(sockets[count].getOutputStream());
                        String token[] = new String(in.readUTF()).split(",");
                        String username = token[0];
                        String password = token[1];
                        // read txt
                        File filename = new File(datapath);
                        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
                        BufferedReader br = new BufferedReader(reader);
                        String line = "";
                        boolean login = false;
                        line = br.readLine();// first username
                        while (line != null) {
                            System.out.println(line);
                            if (line.equals(username)) {
                                line = br.readLine();// this password
                                if (line.equals(password)) {
                                    login = true;
                                }
                                break;
                            } else {
                                line = br.readLine();// this password
                            }
                            line = br.readLine();// next username
                        }
                        if (!login) {
                            out.writeInt(0);
                            in.close();
                            out.close();
                            sockets[count].close();
                            sockets[count] = null;
                            continue;
                        } else {
                            out.writeInt(1);

                            System.out.println("connecting ...");
                            if (count == 0) {
                                p1 = in.readInt();
                            } else {
                                p2 = in.readInt();
                            }
                            count++;
                            if (count == PLAYER_AMOINT) {
                                count = 0;
                                System.out.println("------------------------connecting ...");
                                // send p1, p2 choice number to each
                                try {
                                    out = new DataOutputStream(sockets[0].getOutputStream());
                                    out.writeInt(p2);
                                    out = new DataOutputStream(sockets[1].getOutputStream());
                                    out.writeInt(p1);

                                    // out = new DataOutputStream(sockets[0].getOutputStream());
                                    // out.writeInt(p1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // create thread
                                ServerThread t1 = new ServerThread(sockets[0], sockets[1]);
                                ServerThread t2 = new ServerThread(sockets[1], sockets[0]);
                                t1.start();
                                t2.start();
                                sockets[0] = null;
                                sockets[1] = null;

                                // ServerThread t2 = new ServerThread(sockets[0], sockets[0]);
                                // t2.start();
                                // sockets[0] = null;
                            }
                        }
                    } catch (IOException e) {
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
