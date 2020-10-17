do // execute only once
			{
				GUI.state = true;

				// receive server greeting message
				reply = myRecv(in);
				GUI.setResponse("Greeting message: " + reply + "\n");

				// Username
				mySend(out, "USER " + "iecs02" + "\r\n"); // don't forget "\r\n"
				// mySend(out, "USER " + GUI.getUserName() + "\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				GUI.setResponse("User message: " + reply + "\n");

				// Password
				mySend(out, "PASS " + "t2dWTfhC" + "\r\n"); // don't forget "\r\n"
				// mySend(out, "PASS " + GUI.getPass() + "\r\n"); // don't forget "\r\n"
				reply = myRecv(in);
				GUI.setResponse("Password message: " + reply + "\n");

				// // List [Method 1]
				// mySend(out, "LIST\r\n"); // don't forget "\r\n"
				// reply = myRecv(in);
				// GUI.setResponse("LIST message: " + reply + "\n");
				// System.out.println("Receive message: " + reply);
				// if (reply.charAt(0) != '+')
				// continue;
				// // Count mails
				// for (num = 0; reply.compareTo(".") != 0; num++) // until end of message
				// {
				// reply = myRecv(in);
				// GUI.setResponse("count message: " + reply + "\n");
				// System.out.println("Receive message: " + reply);
				// }
				// num--; // Line "." contains no mail
				// GUI.setResponse("Mailbox counter: " + num + "\n");
				// System.out.println("Mailbox has " + num + " mails");

				// // Status [Method 2]
				// mySend(out, "STAT\r\n"); // don't forget "\r\n"
				// reply = myRecv(in);
				// GUI.setResponse("Status message: " + reply + "\n");
				// System.out.println("Receive message: " + reply);
				// StringTokenizer token = new StringTokenizer(reply);
				// token.nextToken(); // skip +OK
				// num = Integer.parseInt(token.nextToken());
				// GUI.setResponse("Mailbox counter: " + num + "\n");
				// System.out.println("Mailbox has " + num + " mails");

				// // TOP
				// for (int count = 1; count < num + 1; count++) {
				// 	mySend(out, "TOP " + count + " 0\r\n");
				// 	reply = myRecv(in);
				// 	GUI.setResponse("TOP message: " + reply + "\n");
				// 	System.out.println("TOP message: " + reply);

				// 	while (reply.compareTo(".") != 0) // until end of message
				// 	{
				// 		reply = myRecv(in);

				// 		if (reply.matches("^Date.*")) {
				// 			GUI.setResponse("TOP date message: " + reply + "\n");
				// 		} else if (reply.matches("^From.*")) {
				// 			GUI.setResponse("TOP from message: " + reply + "\n");
				// 		} else if (reply.matches("^To.*")) {
				// 			GUI.setResponse("TOP to message: " + reply + "\n");
				// 		} else if (reply.matches("Subject.*")) {
				// 			GUI.setResponse("TOP subject message: " + reply + "\n");
				// 		}
				// 	}

				// }

				// // RETR
				// for (int count = 1; count < num + 1; count++) {
				// 	mySend(out, "RETR " + count + "\r\n");
				// 	GUI.setResponse("-----------------------------" + count + "\n");
				// 	reply = myRecv(in);

				// 	// Normal Mail
				// 	while (reply.compareTo(".") != 0) // until end of message
				// 	{
				// 		reply = myRecv(in);
				// 		GUI.setResponse("RETR message: " + reply + "\n");
				// 	}
				// }
				

				// // DELE
				// mySend(out, "DELE 1\r\n");
				// reply = myRecv(in);
				// GUI.setResponse("DELE message: " + reply + "\n");
				// System.out.println("dele message: " + reply);

				// Quit
				mySend(out, "QUIT\r\n");
            } while (false);
            






            mySend(out, "RETR " + GUI.getParameter() + "\r\n");
							reply = myRecv(in);
							GUI.setResponse("RETR message: " + reply + "\n");

							while (reply.compareTo(".") != 0) {
								reply = myRecv(in);
								GUI.setResponse("RETR message: " + reply + "\n");
							}

							GUI.setResponse("--------------------" + GUI.getParameter() + "th mail infomation\n\n");