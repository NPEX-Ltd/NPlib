package np.library.io;

import java.util.Scanner;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;
@API(level = Level.ALPHA)
public class SystemIO extends IODevice {

	@Override
	public void WriteString(String message) {
		System.out.println(message);
	}

	@Override
	public String ReadStringOrBlock() {
		Scanner sc = new Scanner(System.in);
		String message = sc.next();
		sc.close();
		return message;
	}

	@Override
	public byte[] ReadAllBytes() throws DeviceReadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void WriteAllBytes(byte[] data) throws DeviceWriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WriteByte(byte b) throws DeviceWriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WriteShort(short s) throws DeviceWriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WriteInt(int i) throws DeviceWriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WriteLong(long l) throws DeviceWriteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte ReadByte() throws DeviceReadException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public short ReadShort() throws DeviceReadException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ReadInt() throws DeviceReadException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long ReadLong() throws DeviceReadException {
		// TODO Auto-generated method stub
		return 0;
	}

}
