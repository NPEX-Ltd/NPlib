package np.library.io;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkIO extends IODevice {
	private BufferedDevice device;
	private String host, address;
	private int port;
	
	public NetworkIO(String hostname, int port) {
		this.device = BufferedDevice.Create(hostname, port);
		this.port = port;
		this.host = hostname;
		try {
			this.address = InetAddress.getByName(hostname).getHostAddress();
		} catch (UnknownHostException e) {
			this.address = "UNKOWN-HOST";
		}
	}
	
	public NetworkIO(Socket socket) {
		device = BufferedDevice.Create(socket);
		port = socket.getPort();
		host = socket.getInetAddress().getHostName();
		address = socket.getInetAddress().getHostAddress();
	}
	
	@Override
	public void WriteString(String message) {
		device.WriteString(message);
	}

	@Override
	public String ReadStringOrBlock() {
		return device.ReadStringOrBlock();
	}
	
	public boolean IsOpen() { 
		return device.IsOpen();
	}
	
	@Override
	public String toString() {
		return "(" + address + ") | " + host + ":" + port;
	}

	public void Disconnect() {
		device.Close();
	}
	
	
	
}
