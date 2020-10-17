
//	StudentID	D0782956
//	Name		平祖安

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class SimpleClient1 {

	public static void main(String args[]) {
		Socket client = null;
		InputStream in = null;
		OutputStream out = null;
		int port = 6666;
		byte[] buf = new byte[100];

		// parameter input GUI
		ClientFrame cframe = new ClientFrame();
		cframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (cframe.state) {
			System.out.print("");
		}
		String ipAddress = cframe.getIP();
		int parameter = cframe.getNumber();
		cframe.setVisible(false);

		// GUI part
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		JFrame frame = new JFrame("Client");
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
			client = new Socket(ipAddress, port);
			in = client.getInputStream();
			out = client.getOutputStream();
			resultText.append("client send : " + parameter + "\n");
			out.write(intToByteArray(parameter));
			
			while (true) {
				int len = in.read(buf);
				parameter = byteArrayToInt(buf);
				if (parameter == 0 || len == -1) {
					if (parameter == 0) {
						resultText.append("client receive : " + parameter + "\n");
					} else {
						resultText.append("server disconnect \n");
					}
					break;
				} else {
					resultText.append("client receive : " + parameter + "\n");
					parameter--;
					out.write(intToByteArray(parameter));
					resultText.append("client send : " + parameter + "\n");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
			out.close();
			in.close();
			client.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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