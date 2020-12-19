//*******************************************************************
//*  Network Programming - Unit 4 Concurrent Process                *
//*  Program Name: MainProg6.java                                   *
//*  The program creates threads and shows the solution for  the    *
//*    producer-consumer problem.                                   *
//*  2016.02.01                                                     *
//*******************************************************************

class Producer extends Thread
{
	Resource resource;
	
	Producer(Resource r)		// Constructor
	{
		resource = r;
		start();
	}
	public void run()	// execute after thread has been initialized
	{
		resource.Create();
	}
}

class Consumer extends Thread
{
	Resource resource;
	
	Consumer(Resource r)		// Constructor
	{
		resource = r;
		start();
	}
	public void run()	// execute after thread has been initialized
	{
		System.out.println("Count = " + resource.GetResult());
	}
}

class Resource
{
	int		count = 0;
	synchronized void Create()
	{
		System.out.println("Creating .....");
		try
		{
			Thread.sleep(3000);
		}
		catch(InterruptedException e) { }
		
		count = 1;
		notify();
	}
	
	synchronized int GetResult()
	{
		System.out.println("Waiting for notify.....");
		try
		{
			wait();
		}
		catch(InterruptedException e) { }
		return count;
	}
}

public class MainProg6
{
	public static void main(String args[])
	{
		Resource r = new Resource();
		
		// Create two threads
		Consumer thread1 = new Consumer(r);
		Producer thread2 = new Producer(r);
	}
}

