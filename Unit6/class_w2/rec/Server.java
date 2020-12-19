
import java.rmi.*;
import java.rmi.server.*;

public class Server {
    public static void main(String[] args) {
        try
		{
			RMIimplement name = new RMIimplement();
			System.out.println("Registering ...");
			Naming.rebind("arithmetic", name);	// arithmetic is the name of the service
			System.out.println("Register success");
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
    }
}
