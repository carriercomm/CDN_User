package NewStack;

import java.util.concurrent.BlockingQueue;

import newNetwork.Connection;

public class PacketListener extends Thread{
	
	private BlockingQueue<Packet> packetQueue;
	boolean execute = false;
	private Connection connection;
	
	public PacketListener(BlockingQueue<Packet> queue,Connection con)
	{
		packetQueue = queue;
		connection = con;
		execute = true;
	}
	
	public void close()
	{
		execute = false;
	}
	
	public void run()
	{
		System.out.println("Inside NewStack.PacketListener:");
		while(execute)
		{	
			
			try
			{
				Packet packet = connection.readPacket();   
				if(packet != null)
				{
					packetQueue.put(packet);
				}
			}catch(Exception e)
			{
				//e.printStackTrace();
				System.out.println("Inside NewStack.PacketListener: No Connection is available");
				execute = false ;
			}

		}	
	}
	
	
}