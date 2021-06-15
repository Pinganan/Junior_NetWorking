package Main;

import Setting.*;
import Basic.*;
import Connect.*;
import Single.*;
import java.net.*;
import java.io.*;

public class Client {

	public final static int SELF_LOCATION_X = 300;
	public final static int SELF_LOCATION_Y = 100;
	public final static int OTHER_LOCATION_X = 300;
	public final static int OTHER_LOCATION_Y = 500;

	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		int selfNum = 0, otherNum = 0, port = 6666, r_port = 8888;
		String ip = "192.168.1.160";
		readArduino read = new readArduino();

		BeginGUI begin = new BeginGUI();
		RegisterGUI register = new RegisterGUI();
		while (true) {
			System.out.println(begin.typeNum);
			if (begin.typeNum == 1) {
				try {
					// connect server & check account & password
					try {
						socket = new Socket(ip, port);
						in = new DataInputStream(socket.getInputStream());
						out = new DataOutputStream(socket.getOutputStream());
					} catch (UnknownHostException e3) {
						e3.printStackTrace();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
					out.writeUTF(begin.getName() + "," + begin.getPass());
					if (in.readInt() == 1) {
						break;
					} else {
						begin.typeNum = 0;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (begin.typeNum == 2) {
                begin.setVisible(false);
				register.setVisible(true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (register.isRegisterFin == 0) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					socket = new Socket(ip, r_port);
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF(register.getName() + "," + register.getPass());
					in.close();
					out.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				socket = null;
				register.setVisible(false);
				register.isRegisterFin = 0;
				begin.typeNum = 0;
				begin.setVisible(true);
			}
		}

		// create frame for self & others
		SingleFrame sf = new SingleFrame(SELF_LOCATION_X, SELF_LOCATION_Y);
		ConnectFrame cf = new ConnectFrame(OTHER_LOCATION_X, OTHER_LOCATION_Y);

		// start interface
		SingleInitPanel iPnael = new SingleInitPanel();
		sf.addPanel(iPnael);
		while (!read.isSoundTrigger) {
			System.out.printf("waiting for voice for going to select");
		}
		read.isSoundTrigger = false;

		// select interface
		SingleSelectPanel sPanel = new SingleSelectPanel(read);
		sf.addPanel(sPanel);
		while (!sPanel.isSelected) {
			System.out.println("waiting for voice for going to game");
		}
		selfNum = sPanel.selectedNumber;

		// waiting interface
		SingleWaitPanel wPanel = new SingleWaitPanel();
		sf.addPanel(wPanel);

		// send selfNum out
		try {
			out.writeInt(selfNum);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			socket.setSoTimeout(1000);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}

		// get otherNum
		while (true) {
			try {
				otherNum = in.readInt();
				// each player is ready
				break;
			} catch (IOException e) {
				System.out.println("keep waiting for other players");
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// two gaming interface
		SingleGamingPanel SGPanel = new SingleGamingPanel(out, read, selfNum);
		ConnectGamingPanel CGPanel = new ConnectGamingPanel(in, otherNum);
		sf.addPanel(SGPanel);
		cf.addPanel(CGPanel);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// end interface
		EndPanel SEPanel = null;
		EndPanel CEPanel = null;
		while (!SGPanel.role.isFin || !CGPanel.role.isFin) {
			if (SGPanel.role.isFin == true) {
				SEPanel = new EndPanel(false);
				CEPanel = new EndPanel(true);
			} else if (CGPanel.role.isFin == true) {
				SEPanel = new EndPanel(true);
				CEPanel = new EndPanel(false);
			}
		}
		cf.addPanel(CEPanel);
		sf.addPanel(SEPanel);
	}
}
