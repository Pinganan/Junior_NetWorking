//*******************************************************************
//*  Network Programming - Unit 6 Remote Method Invocation          *
//*  Program Name: ArithmeticInterface                              *
//*  The program defines the interface for Java RMI.                *
//*  2014.02.26                                                     *
//*******************************************************************
import java.rmi.Remote;

public interface ArithmeticInterface extends Remote
{
	public long add(long a, long b) throws java.rmi.RemoteException;
	public long sub(long a, long b) throws java.rmi.RemoteException;
	public long mul(long a, long b) throws java.rmi.RemoteException;
	public long div(long a, long b) throws java.rmi.RemoteException;
}

