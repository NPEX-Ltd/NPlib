package np.library.io;

import java.io.File;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;
@API(level = Level.ALPHA)
public class FileIO extends IODevice {
	private BufferedDevice device;
	
	public FileIO(File file) {
		device = BufferedDevice.Create(file);
	}
	
	@Override
	public void WriteString(String message) {
		device.WriteString(message);
	}

	@Override
	public String ReadStringOrBlock() {
		return device.ReadStringOrBlock();
	}
	
	@Override
	public void Close() {
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

	public long ReadLong() throws DeviceReadException {
		return device.ReadLong();
	}

	public String toString() {
		return device.toString();
	}

	@Override
	public int ReadInt() throws DeviceReadException {
		return device.ReadInt();
	}
	
	

}
