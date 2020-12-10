package np.library.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.exceptions.DeviceCloseException;
import np.library.exceptions.DeviceOpenException;
@API(level = Level.ALPHA)
public class BufferedDevice extends IODevice {
	private BufferedReader fileReader;
	private PrintWriter fileWriter;
	
	private boolean isOpen = true;
	
	public BufferedDevice(InputStream input, OutputStream output) {
		fileReader = new BufferedReader(new InputStreamReader(input));
		fileWriter = new PrintWriter(output);
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
}
