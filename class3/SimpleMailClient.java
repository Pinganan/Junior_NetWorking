
//*******************************************************************
//*  Network Programming - Unit 3 Application based on TCP          *
//*  Program Name: SimpleMailClient                                 *
//*  The program replies the number of mails in the mailbox.        *
//*  2017.08.09                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;
import java.util.*; // for StringTokenizer
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

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
		byte[] buf = new byte[1000];
		String reply;
		String request;
		int num;
		

		try {
			GUI GUI = new GUI();
			JFrame showGUI = GUI.TakeShowGUI();

			while(GUI.state) {
				System.out.print("");
			}
			client = new Socket(GUI.getIp(), port);
			in = new BufferedInputStream(client.getInputStream());
			out = new BufferedOutputStream(client.getOutputStream());

			do // execute only once
			{
				GUI.state = true;

				// receive server greeting message
				reply = myRecv(in);
				GUI.setResponse("Greeting message: " + reply + "\n");
				if (reply.charAt(0) != '+')
					continue;

				// Username
				mySend(out, "USER " + GUI.getUserName() + "\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				GUI.setResponse("User message: " + reply + "\n");
				if (reply.charAt(0) != '+')
					continue;

				// Password
				mySend(out, "PASS " + GUI.getPass() + "\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				GUI.setResponse("Password message: " + reply + "\n");
				if (reply.charAt(0) != '+')
					continue;

				// List [Method 1]
				mySend(out, "LIST\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				System.out.println("Receive message: " + reply);
				if (reply.charAt(0) != '+')
					continue;

				// Count mails
				for (num = 0; reply.compareTo(".") != 0; num++) // until end of message
				{
					reply = myRecv(in);
					GUI.setResponse("count message: " + reply + "\n");
					System.out.println("Receive message: " + reply);
				}
				num--; // Line "." contains no mail
				GUI.setResponse("Mailbox counter: " + reply + "\n");
				System.out.println("Mailbox has " + num + " mails");

				// Status [Method 2]
				mySend(out, "STAT\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				GUI.setResponse("Status message: " + reply + "\n");
				System.out.println("Receive message: " + reply);
				StringTokenizer token = new StringTokenizer(reply);
				token.nextToken(); // skip +OK
				num = Integer.parseInt(token.nextToken());
				GUI.setResponse("Mailbox counter: " + reply + "\n");
				System.out.println("Mailbox has " + num + " mails");

				// RETR
				mySend(out, "RETR 1\r\n");
				reply = myRecv(in);
				GUI.setResponse("RETR message: " + reply + "\n");
				System.out.println("RETR message: " + reply);

				// TOP
				mySend(out, "TOP 1 0\r\n");
				reply = myRecv(in);
				while(reply.compareTo(".") != 0) // until end of message
				{
					reply = myRecv(in);
					GUI.setResponse("top message: " + reply + "\n");
					System.out.println("top message: " + reply);
				}

				// DELE
				mySend(out, "DELE 1\r\n");
				reply = myRecv(in);
				GUI.setResponse("DELE message: " + reply + "\n");
				System.out.println("dele message: " + reply);

				// Quit
				mySend(out, "QUIT\r\n");
			} while (false);
			client.close();
			GUI.setResponse("End Connection");
			System.out.println("Connection closed!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}