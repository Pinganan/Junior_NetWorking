
//  StudentID   D0782956
//  Name        PingTsuAn

import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Thread;
import java.io.*;

public class SimpleServer1 {
	public static void main(String args[]) {
		ServerSocket srverSocket = null;
		Socket sc = null;
		InputStream in = null;
		OutputStream out = null;
		int port = 6666;
		byte[] buf = new byte[100];

		// GUI part
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Server");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(250, 250);
		frame.setLocation((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2);
		JTextArea resultText = new JTextArea(""); // chat information
		Container content = frame.getContentPane();
		resultText.setEditable(false);
		JScrollPane scroll = new JScrollPane(resultText);
		content.add(scroll, BorderLayout.CENTER);
		frame.setVisible(true);

		try {
			// Creates a server socket, bound to the specified port.
			srverSocket = new ServerSocket(port);
			resultText.append("Waiting for request ...\n");

			System.out.println("Waiting for request ...");
			try {
				sc = srverSocket.accept();
				in = sc.getInputStream();
				out = sc.getOutputStream();

				while (true) {
					int len = in.read(buf);
					int num = byteArrayToInt(buf);
					if (num == 0 || len == -1) {
						if (num == 0) {
							resultText.append("server receive : " + num + "\n");
						} else {
							resultText.append("client disconnect\n");
						}
						break;
					} else {
						resultText.append("server receive : " + num + "\n");
						num--;
						out.write(intToByteArray(num));
						resultText.append("sever send : " + num + "\n");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				}
				in.close();
				out.close();
				sc.close();
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				srverSocket.close();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 255 | (b[2] & 255) << 8 | (b[1] & 255) << 16 | (b[0] & 255) << 24;
	}

	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 255), (byte) ((a >> 16) & 255), (byte) ((a >> 8) & 255),
				(byte) (a & 255) };
	}
}
