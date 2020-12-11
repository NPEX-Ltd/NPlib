package np.library.io;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;
@API(level = Level.ALPHA)
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

	public byte[] ReadAllBytes() throws DeviceReadException {
		return device.ReadAllBytes();
	}

	public void WriteAllBytes(byte[] data) throws DeviceWriteException {
		device.WriteAllBytes(data);
	}

	public void WriteByte(byte b) throws DeviceWriteException {
		device.WriteByte(b);
	}

	public void WriteShort(short s) throws DeviceWriteException {
		device.WriteShort(s);
	}

	public void WriteInt(int i) throws DeviceWriteException {
		device.WriteInt(i);
	}

	public void WriteLong(long l) throws DeviceWriteException {
		device.WriteLong(l);
	}

	public byte ReadByte() throws DeviceReadException {
		return device.ReadByte();
	}

	public short ReadShort() throws DeviceReadException {
		return device.ReadShort();
	}

	public int ReadInt() throws DeviceReadException {
		return device.ReadInt();
	}

	public long ReadLong() throws DeviceReadException {
		return device.ReadLong();
	}
	
	
	
}
