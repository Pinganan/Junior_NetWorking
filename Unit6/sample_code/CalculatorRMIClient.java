//*******************************************************************
//*  Network Programming - Unit 6 Remote Method Invocation          *
//*  Program Name: CalculatorRMIClient                              *
//*  The program is a RMI client.                                   *
//*  Usage: java CalculatorRMIClient op num1 num2,                  *
//*         op = add, sub, mul, div                                 *
//*  2014.02.26                                                     *
//*******************************************************************
import java.io.*;
import java.rmi.*;

class CalculatorRMIClient
{
	public static void main(String args[])
	{
	    ArithmeticInterface		o = null;
		int		op= 0; 	// add=0, sub=1, mul = 2, div = 3
		long	num1 = 0, num2 = 0, result = 0;
	
	    if(args.length < 3)
	    {
	        System.out.println("Usage: java Calculator op num1 num2, op = add, sub, mul, div");
	        System.exit(1);
	    }
	    
	    if(args[0].compareTo("add") == 0)
	        op = 0;
	    else if(args[0].compareTo("sub") == 0)
	        op = 1;
	    else if(args[0].compareTo("mul") == 0)  
	    	op = 2;
	    else if(args[0].compareTo("div") == 0)
	        op = 3;  
	    else
	    {
	        System.out.println("Usage: java Calculator op num1 num2, op = add, sub, mul, div");
	        System.exit(1);
	    }  
	    
	    try
	    {
	    	num1 = Long.parseLong(args[1]);
	    	num2 = Long.parseLong(args[2]);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Integer format error!!");
	    	System.exit(1);
	    }
	    
	    //System.setSecurityManager(new RMISecurityManager());
	    // Connect to RMIServer
	    try
	    {
	    	o = (ArithmeticInterface) Naming.lookup("rmi://127.0.0.1/arithmetic");
	    	System.out.println("RMI server connected");
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Server lookup exception: " + e.getMessage());
	    }
	    
	    try
	    {
	    	switch(op)
	    	{
	        	case 0: // add
	        		result = o.add(num1, num2);
	        		System.out.println(num1 + " + " + num2 + " = " + result);
	        		break;
	        	case 1: // sub
	        		result = o.sub(num1, num2);
	        		System.out.println(num1 + " - " + num2 + " = " + result);
	        		break;
	        	case 2: // mul
	        		result = o.mul(num1, num2);
	        		System.out.println(num1 + " * " + num2 + " = " + result);
	        		break;
	        	case 3: // div
	        		result = o.div(num1, num2);
	        		System.out.println(num1 + " / " + num2 + " = " + result);
	        		break;
	        }
	    }
        catch(Exception e)
        {
        	System.out.println("ArithmeticServer exception: " + e.getMessage());
        	e.printStackTrace();
        }
	}
}