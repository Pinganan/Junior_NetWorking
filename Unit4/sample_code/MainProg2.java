//*******************************************************************
//*  Network Programming - Unit 4 Concurrent Process                *
//*  Program Name: MainProg2.java                                   *
//*  The program creates threads by implementing Runnable Interface.*
//*  2016.02.01                                                     *
//*******************************************************************

class ThreadByRunnable implements Runnable
{
	ThreadByRunnable(String name)		// Constructor
	{
		Thread t = new Thread(this, name);
		t.start();
	}
	public void run()	// execute after thread has been initialized
	{
		System.out.println("Thread " + (Thread.currentThread()).getName() + " is created!!");
	}
}

public class MainProg2
{
	public static void main(String args[])
	{
		Thread	t = Thread.currentThread();
		System.out.println("Current threat name is " + t.getName());
		
		// Create two threads
		ThreadByRunnable thread1 = new ThreadByRunnable("T1");
		ThreadByRunnable thread2 = new ThreadByRunnable("T2");
		System.out.println("Number of threads :" + Thread.activeCount());
	}
}