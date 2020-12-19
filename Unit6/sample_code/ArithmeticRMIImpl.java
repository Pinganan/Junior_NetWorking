//*******************************************************************
//*  Network Programming - Unit 6 Remote Method Invocation          *
//*  Program Name: ArithmeticRMIImpl                                *
//*  The program implements the services defended in the interface, *
//*    ArithmeticInterface.java, for Java RMI.                      *
//*  2014.02.26                                                      *
//*******************************************************************
import java.rmi.*;
import java.rmi.server.*;

public class ArithmeticRMIImpl extends UnicastRemoteObject implements ArithmeticInterface
{
	// This implementation must have a public constructor.
	// The constructor throws a RemoteException.
	public ArithmeticRMIImpl() throws java.rmi.RemoteException
	{
		super(); 	// Use constructor of parent class
	}
		
	// Implementation of the service defended in the interface
	public long add(long a, long b) throws java.rmi.RemoteException
	{
	    System.out.println("Receive numbers " + a + " and " + b + " by add()");
		return(a + b);
	}
		
	public long sub(long a, long b) throws java.rmi.RemoteException
	{
		System.out.println("Receive numbers " + a + " and " + b + " by sub()");
		return(a - b);
	}
		
	public long mul(long a, long b) throws java.rmi.RemoteException
	{
		System.out.println("Receive numbers " + a + " and " + b + " by mul()");
		return(a * b);
	}
		
	public long div(long a, long b) throws java.rmi.RemoteException
	{
		System.out.println("Receive numbers " + a + " and " + b + " by div()");
		return(a / b);
	}
}