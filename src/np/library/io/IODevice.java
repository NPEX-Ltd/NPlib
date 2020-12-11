package np.library.io;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;

@API(level = Level.ALPHA)
public abstract class IODevice implements AutoCloseable {
	public abstract void WriteString(String message) throws DeviceWriteException;
	public abstract String ReadStringOrBlock() throws DeviceReadException;
	
	public abstract byte[] ReadAllBytes (           ) throws DeviceReadException;
	public abstract void   WriteAllBytes(byte[] data) throws DeviceWriteException;
	
	public abstract void WriteByte (byte  b) throws DeviceWriteException;
	public abstract void WriteShort(short s) throws DeviceWriteException;
	public abstract void WriteInt  (int   i) throws DeviceWriteException;
	public abstract void WriteLong (long  l) throws DeviceWriteException;
	
	public abstract byte  ReadByte () throws DeviceReadException;
	public abstract short ReadShort() throws DeviceReadException;
	public abstract int   ReadInt  () throws DeviceReadException;
	public abstract long  ReadLong () throws DeviceReadException;
	
	public void Open() {}
	public void Close() {}
	
	@Override
	public void close() throws Exception {
		Close();
	}
}
