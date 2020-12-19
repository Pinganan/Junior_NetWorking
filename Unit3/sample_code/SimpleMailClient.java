//*******************************************************************
//*  Network Programming - Unit 3 Application based on TCP          *
//*  Program Name: SimpleMailClient                                 *
//*  The program replies the number of mails in the mailbox.        *
//*  2017.08.09                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;
import java.util.*;		// for StringTokenizer

class SimpleMailClient
{
	private static void mySend(BufferedOutputStream out, String s) throws IOException
	{
		int		i;
		
		for(i = 0 ; i < s.length() ; i++)
		{
			out.write((byte)s.charAt(i));
		}
		out.flush();
	}
	
	private static String myRecv(BufferedInputStream in) throws IOException
	{
		String	reply = "";
		int		c;
		
		for(c = in.read() ; c >= 0 && c != '\n' ; c = in.read())
		{
		    if(c != '\r')
		        reply += (char) c;
		}
		return reply;
	}
	
	public static void main(String args[])
	{
		Socket					client = null;
		int						port = 110;
		BufferedInputStream 	in = null;
		BufferedOutputStream 	out = null;
		byte []					buf = new byte[1000];
		String					reply;
		String					request;
		int						num;

		if(args.length < 3)
		{
		    System.out.println("Usage: java SimpleMailClient server username password");
		}
		else
		{
			try
			{
				client = new Socket(args[0], port);
				do	// execute only once
				{
					in = new BufferedInputStream(client.getInputStream());
					out = new BufferedOutputStream(client.getOutputStream());
					
					// receive server greeting message
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;

					// Username
					mySend(out, "USER " + args[1] + "\r\n");	// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;

					// Password					
					mySend(out, "PASS " + args[2] + "\r\n");	// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;
					
					// List [Method 1]
					mySend(out, "LIST\r\n");					// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;
					// Count mails
					for(num = 0 ; reply.compareTo(".") != 0 ; num++) // until end of message
					{
						reply = myRecv(in);
						System.out.println("Receive message: " + reply);
					}
					num --; // Line "." contains no mail
					System.out.println("Mailbox has " + num + " mails");
					
					// Status [Method 2]
					mySend(out, "STAT\r\n");					// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					StringTokenizer token = new StringTokenizer(reply);
					token.nextToken();	// skip +OK
					num = Integer.parseInt(token.nextToken());	
					System.out.println("Mailbox has " + num + " mails");
					
					// Quit 
					mySend(out, "QUIT\r\n");
				} while(false);	
				client.close();
				System.out.println("Connection closed!");
			}
			catch(UnknownHostException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}