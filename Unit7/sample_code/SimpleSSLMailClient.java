//*******************************************************************
//*  Network Programming - Unit 7 Secure Socket                     *
//*  Program Name: SimpleSSLMailClient                              *
//*  The program replies the number of mails in the mailbox.        *
//*  Usage: java SimpleSSLMailClient server port user_name password *
//*     Ex: java SimpleSSLMailClient pop.gmail.com 995 user pwd     *
//*  2014.02.02                                                     *
//*******************************************************************
import java.net.*;
import java.io.*;
import java.util.*;		// for StringTokenizer
import javax.net.ssl.*;

class SimpleSSLMailClient
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
		SSLSocket				client = null;
		BufferedInputStream 	in = null;
		BufferedOutputStream 	out = null;
		byte []					buf = new byte[1000];
		String					reply;
		String					request;
		int						num;

		if(args.length < 4)
		{
		    System.out.println("Usage: java SimpleMailClient server port username password");
		}
		else
		{
			try
			{
				SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
    			client = (SSLSocket)factory.createSocket(args[0], Integer.parseInt(args[1]));
    			
    			// Use default cipher suites
    			String[] supportedCS = client.getSupportedCipherSuites();
				client.setEnabledCipherSuites(supportedCS);
   			
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
					mySend(out, "USER " + args[2] + "\r\n");	// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;

					// Password					
					mySend(out, "PASS " + args[3] + "\r\n");	// don't forget "\r\n"
					reply = myRecv(in);
					System.out.println("Receive message: " + reply);
					if(reply.charAt(0) != '+')
					    continue;
					
					// Status
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