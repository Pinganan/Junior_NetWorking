//*******************************************************************
//*  Network Programming - Unit 4 Concurrent Process                *
//*  Program Name: MainProg4.java                                   *
//*  The program creates threads and solves the critical section    *
//*    problem between threads. (Method 1)                          *
//*  2016.02.01                                                     *
//*******************************************************************

class SharedObject
{
    private static int	count = 0;		// shared variable
    
	void CriticalSection(String name)
	{
		System.out.println(name + " enters critical section.");
		for(int i = 0 ; i < 5 ; i++)
		{
		  	System.out.println(name + ":" + i);
		   	count++;					// access shared variable
		   	try
		   	{
		   		Thread.sleep(500);		// sleep 0.5 second
		   	}
		   	catch(InterruptedException e){ }
		}	
		System.out.println(name + " leaves critical section.");
	}
	
	public static int replyCount()
	{
	    return(count);
	}
}

class ThreadBySubclass extends Thread
{
	String					ThreadName;
	SharedObject			SO = new SharedObject();
	public static Object 	lock = new Object();
	
	ThreadBySubclass(String name)		// Constructor
	{
		ThreadName = name;
	}
	public void run()	// execute after thread has been initialized
	{
		Thread	t = Thread.currentThread();
		
		t.setName(ThreadName);

		synchronized(lock)		// Uncomment this line and recompile
		{
			SO.CriticalSection(ThreadName);
		}
	}
}

public class MainProg4
{
	public static void main(String args[])
	{
		Thread	t = Thread.currentThread();
		
		// Create two threads
		ThreadBySubclass thread1 = new ThreadBySubclass("T1");
		ThreadBySubclass thread2 = new ThreadBySubclass("T2");
	
		System.out.println("Count = " + SharedObject.replyCount());
			
		thread1.start();    // if call thread1.run(), no thread is created
		thread2.start();	// create thread and execute thread2.run()
		
		try
		{
			thread1.join();		// waiting thread1 and thread2
			thread2.join();
		}
		catch(InterruptedException e){ }
		System.out.println("Count = " + SharedObject.replyCount());
	}
}
