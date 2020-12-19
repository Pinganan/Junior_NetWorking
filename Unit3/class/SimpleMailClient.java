
// Student ID : D0782956
// Name       : Ping Tsu An
import java.net.*;
import java.io.*;
import java.util.*; // for StringTokenizer
import javax.swing.*;
import java.lang.Thread;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

class SimpleMailClient {

	private static void mySend(BufferedOutputStream out, String s) throws IOException {
		int i;

		for (i = 0; i < s.length(); i++) {
			out.write((byte) s.charAt(i));
		}
		out.flush();
	}

	private static String myRecv(BufferedInputStream in) throws IOException {

		String reply = "";
		int c;

		for (c = in.read(); c >= 0 && c != '\n'; c = in.read()) {
			if (c != '\r')
				reply += (char) c;
		}
		return reply;
	}

	public static void main(String args[]) {
		Socket client = null;
		int port = 110;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		String reply;
		int num = 0;
		boolean iniFlag = true;
		boolean decodeFlag = false;
		String user = "";
		String pass = "";
		String ip = "";

		try {
			GUI GUI = new GUI();
			JFrame showGUI = GUI.TakeShowGUI();

			while (true) {
				if (GUI.state) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

					}
				} else {
					GUI.state = true;

					if(GUI.getIp().equals(ip) && GUI.getUserName().equals(user) && GUI.getPass().equals(pass)) {
						// no do
					}
					else {
						user = GUI.getUserName();
						pass = GUI.getPass();
						ip = GUI.getIp();
						iniFlag = true;
					}

					if (iniFlag) {
						// client = new Socket("140.134.135.41", port);
						client = new Socket(GUI.getIp(), port);
						in = new BufferedInputStream(client.getInputStream());
						out = new BufferedOutputStream(client.getOutputStream());
						reply = myRecv(in);
						GUI.setResponse("Greeting message: " + reply + "\n");
						// mySend(out, "USER " + "iecs09" + "\r\n"); // don't forget "\r\n"
						mySend(out, "USER " + GUI.getUserName() + "\r\n"); // don't forget "\r\n"
						reply = myRecv(in);
						GUI.setResponse("User message: " + reply + "\n");
						// mySend(out, "PASS " + "6X6ZQ4WG" + "\r\n"); // don't forget "\r\n"
						mySend(out, "PASS " + GUI.getPass() + "\r\n"); // don't forget "\r\n"
						reply = myRecv(in);
						GUI.setResponse("Password message: " + reply + "\n");
						GUI.setResponse("\n--------------------init  finish--------------------\n\n");

						mySend(out, "STAT\r\n"); // don't forget "\r\n"
						reply = myRecv(in);
						StringTokenizer token = new StringTokenizer(reply);
						token.nextToken();
						num = Integer.parseInt(token.nextToken());

						iniFlag = false;
					}

					if (GUI.actID == 1) {
						// Status
						mySend(out, "STAT\r\n"); // don't forget "\r\n"
						reply = myRecv(in);
						GUI.setResponse("Status message: " + reply + "\n");
						StringTokenizer token = new StringTokenizer(reply);
						token.nextToken(); // skip +OK
						num = Integer.parseInt(token.nextToken());
						GUI.setResponse("Mailbox counter: " + num + "\n");
						GUI.setResponse("--------------------Here are " + num + " mails\n\n");
						continue;
					} else if (GUI.actID == 2) {
						// DELE
						if (GUI.getParameter() <= num) {
							mySend(out, "DELE " + GUI.getParameter() + "\r\n");
							reply = myRecv(in);
							GUI.setResponse("DELE message: " + reply + "\n");
							GUI.setResponse(
									"--------------------" + GUI.getParameter() + "th delete successfully \n\n");
						} else {
							GUI.setResponse("--------------------Don't kid, ok??\n\n");
						}
						mySend(out, "QUIT\r\n");
						iniFlag = true;

					} else if (GUI.actID == 3) {
						// TOP
						for (int count = 1; count < num + 1; count++) {
							mySend(out, "TOP " + count + " 0\r\n");
							reply = myRecv(in);
							GUI.setResponse("TOP message: " + reply + "\n");
							System.out.println("TOP message: " + reply);

							while (reply.compareTo(".") != 0) // until end of message
							{
								reply = myRecv(in);

								if (reply.matches("^Date.*")) {
									GUI.setResponse("TOP date message: " + reply + "\n");
								} else if (reply.matches("^From.*")) {
									GUI.setResponse("TOP from message: " + reply + "\n");
								} else if (reply.matches("^To.*")) {
									GUI.setResponse("TOP to message: " + reply + "\n");
								} else if (reply.matches("Subject.*")) {
									GUI.setResponse("TOP subject message: " + reply + "\n");
								}
							}
							GUI.setResponse("--------------------" + count + "th mail infomation\n\n");
						}
					} else if (GUI.actID == 4) {
						// RETR
						if (GUI.getParameter() <= num) {
							mySend(out, "RETR " + GUI.getParameter() + "\r\n");
							reply = myRecv(in);
							GUI.setResponse("RETR message: " + reply + "\n");

							String temp = "";

							while (reply.compareTo(".") != 0) {
								reply = myRecv(in);
								// GUI.setResponse("RETR message: " + reply + "\n");
								temp += reply;
								if (reply.equals("")) {
									temp = "";
								}
								if (reply.matches(".*(utf-8).*")) {
									decodeFlag = true;
								}
							}
							if (decodeFlag) {
								String s1 = new String(temp.getBytes("US-ASCII"), "GB2312");
								GUI.setResponse(temp + "\n");
							} else {
								GUI.setResponse(temp + "\n");
							}
							System.out.println(temp + "   " + GUI.getParameter());

							GUI.setResponse("--------------------" + GUI.getParameter() + "th mail infomation\n\n");

						} else {
							GUI.setResponse("--------------------Don't kid, ok??\n\n");
						}
					} else {
						// QUIT
						mySend(out, "QUIT\r\n");
						GUI.setResponse("--------------------End Connection");
						break;
					}
				}
			}
			in.close();
			out.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}