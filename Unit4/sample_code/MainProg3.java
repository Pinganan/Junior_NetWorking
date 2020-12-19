//*******************************************************************
//*  Network Programming - Unit 4 Concurrent Process                *
//*  Program Name: MainProg3.java                                   *
//*  The program creates threads and tests the sleep(),             *
//*    setPriority() and join() functions.                          *
//*  2016.02.01                                                     *
//*******************************************************************

class ThreadBySubclass extends Thread
{
	String	ThreadName;
	
	ThreadBySubclass(String name)		// Constructor
	{
		ThreadName = name;
	}
	public void run()	// execute after thread has been initialized
	{
		Thread	t = Thread.currentThread();
		int		i;
		
		t.setName(ThreadName);
		System.out.println("Thread " + ThreadName + " is created!!");
		for(i = 0 ; i < 10 ; i++)
		{
			System.out.println(ThreadName + ":" + i);
			if((i == 5) && (ThreadName.compareTo("T1") == 0))
			{
				try
				{
					System.out.println(ThreadName + " sleep 1 seconds!!");
					Thread.sleep(1000);
				}
				catch(InterruptedException e)
				{
					System.out.println(ThreadName + " Interrupt!!");
				}
			}
		}
	}
}

public class MainProg3
{
	public static void main(String args[])
	{
		int		i;
		Thread	t = Thread.currentThread();
		System.out.println("Current threat name is " + t.getName());
		
		// Create three threads
		ThreadBySubclass thread1 = new ThreadBySubclass("T1");
		ThreadBySubclass thread2 = new ThreadBySubclass("T2");
		
		thread1.start();    // if call thread1.run(), no thread is created
		thread2.start();	// create thread and execute thread2.run()
		
//		thread1.setPriority(1);
//		thread2.setPriority(10);

		try
		{
			System.out.println(t.getName() + " wait Thread1!!");
			thread1.join();
		}
		catch(InterruptedException e)
		{
			System.out.println(t.getName() + " Interrupt!!");
		}
		for(i = 0 ; i < 10 ; i++)
		{
			System.out.println(t.getName() + ":" + i);
		}
	}
}