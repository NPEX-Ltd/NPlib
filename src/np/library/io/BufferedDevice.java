package np.library.io;

import java.io.*;
import java.net.Socket;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.*;
@API(level = Level.ALPHA)
public class BufferedDevice extends IODevice {
	private BufferedReader fileReader;
	private PrintWriter fileWriter;
	
	private DataInputStream input;
	private DataOutputStream output;
	
	private boolean isOpen = true;
	
	public BufferedDevice(InputStream _input, OutputStream _output) {
		fileReader = new BufferedReader(new InputStreamReader(_input));
		fileWriter = new PrintWriter(_output);
		
		this.input = new DataInputStream(_input);
		this.output = new DataOutputStream(_output);
	}
	
	private BufferedDevice(File file) throws IOException {
			this(new FileInputStream(file), new FileOutputStream(file)); 
	}
	
	public static BufferedDevice Create(File file)
	throws DeviceOpenException {
		try {
			return new BufferedDevice(file);
		} catch (IOException ioex) {
			throw new DeviceOpenException(ioex);
		}
	}
	
	public static BufferedDevice Create(String hostname, int port)
	throws DeviceOpenException {
		try {
			return Create(new Socket(hostname, port));
		} catch (IOException e) {
			throw new DeviceOpenException(e);
		}
	}
	
	public static BufferedDevice Create(Socket sock)
	throws DeviceOpenException {
		try {
			return new BufferedDevice(sock.getInputStream(), sock.getOutputStream());
		} catch (IOException ioex) {
			throw new DeviceOpenException(ioex);
		}
	}

	@Override
	public void WriteString(String message) {
			fileWriter.write(message + "\r\n");
			fileWriter.flush();
	}

	@Override
	public String ReadStringOrBlock() {
		try {
			System.err.println("NIO is open?: "+isOpen);
			if(!isOpen) return null;
			return fileReader.readLine();
		} catch (IOException ioex) {
			Close();
			return null;
		}
	}
	
	@Override
	public void Close()
	throws DeviceCloseException {
		try {
			if(!IsOpen()) return;
			
			fileReader.close();
			fileWriter.close();
		} catch (IOException ioex) {
			isOpen = false;
			throw new DeviceCloseException(ioex);
		} finally {
			isOpen = false;
		}
	}

	public boolean IsOpen() {
		return isOpen;
	}

	@Override
	public byte[] ReadAllBytes() throws DeviceReadException {
		try {
			return input.readAllBytes();
		} catch (IOException ioex) {
			throw new DeviceReadException(ioex);
		}
	}

	@Override
	public void WriteAllBytes(byte[] data) throws DeviceWriteException {
		try {
			output.write(data);
		} catch (IOException ioex) {
			throw new DeviceWriteException(ioex);
		}
	}

	@Override
	public void WriteByte(byte b) throws DeviceWriteException {
		try {
			output.write(b);
		} catch (IOException ioex) {
			throw new DeviceWriteException(ioex);
		}
	}

	@Override
	public void WriteShort(short s) throws DeviceWriteException {
		try {
			output.writeShort(s);
		} catch (IOException ioex) {
			throw new DeviceWriteException(ioex);
		}
	}

	@Override
	public void WriteInt(int i) throws DeviceWriteException {
		try {
			output.writeInt(i);
		} catch (IOException ioex) {
			throw new DeviceWriteException(ioex);
		}
	}

	@Override
	public void WriteLong(long l) throws DeviceWriteException {
		try {
			output.writeLong(l);
		} catch (IOException ioex) {
			throw new DeviceWriteException(ioex);
		}
	}

	@Override
	public byte ReadByte() throws DeviceReadException {
		try {
			return input.readByte();
		} catch (EOFException eofex) {
			throw new JuggledException(eofex);
		} catch (IOException ioex) {
			throw new DeviceReadException(ioex);
		}
	}

	@Override
	public short ReadShort() throws DeviceReadException {
		try {
			return input.readShort();
		} catch (EOFException eofex) {
			throw new JuggledException(eofex);
		} catch (IOException ioex) {
			throw new DeviceReadException(ioex);
		}
	}

	@Override
	public int ReadInt() throws DeviceReadException {
		try {
			return input.readInt();
		} catch (EOFException eofex) {
			throw new JuggledException(eofex);
		} catch (IOException ioex) {
			throw new DeviceReadException(ioex);
		}
	}

	@Override
	public long ReadLong() throws DeviceReadException {
		try {
			return input.readLong();
		} catch (EOFException eofex) {
			throw new JuggledException(eofex);
		} catch (IOException ioex) {
			throw new DeviceReadException(ioex);
		}
	}
}
