//*******************************************************************
//*  Network Programming - Unit 4 Concurrent Process                *
//*  Program Name: MainProg1.java                                   *
//*  The program creates threads by extending thread subclass.      *
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
		
		t.setName(ThreadName);
		System.out.println("Thread " + ThreadName + " is created!!");
	}
}

public class MainProg1
{
	public static void main(String args[])
	{
		Thread	t = Thread.currentThread();
		System.out.println("Current threat name is " + t.getName());
		
		// Create two threads
		ThreadBySubclass thread1 = new ThreadBySubclass("T1");
		ThreadBySubclass thread2 = new ThreadBySubclass("T2");
		
		System.out.println("Number of threads (before):" + Thread.activeCount());
		
		thread1.start();    // if call thread1.run(), no thread is created
		thread2.start();	// create thread and execute thread2.run()
		
		System.out.println("Number of threads (after):" + Thread.activeCount());
	}
}