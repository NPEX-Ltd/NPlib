package np.library.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import np.library.exceptions.DeviceCloseException;
import np.library.exceptions.DeviceOpenException;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;

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
	
	public static BufferedDevice Create(File file) {
		try {
			return new BufferedDevice(file);
		} catch (IOException ioex) {
			throw new DeviceOpenException();
		}
	}
	
	public static BufferedDevice Create(String hostname, int port) {
		try {
			return Create(new Socket(hostname, port));
		} catch (IOException e) {
			throw new DeviceOpenException("Unable To Open Device To "+hostname+":"+port);
		}
	}
	
	public static BufferedDevice Create(Socket sock) {
		try {
			return new BufferedDevice(sock.getInputStream(), sock.getOutputStream());
		} catch (IOException ioex) {
			throw new DeviceOpenException();
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
	public void Close() {
		try {
			if(!IsOpen()) return;
			
			fileReader.close();
			fileWriter.close();
		} catch (IOException ioex) {
			isOpen = false;
			throw new DeviceCloseException();
		} finally {
			isOpen = false;
		}
	}

	public boolean IsOpen() {
		return isOpen;
	}
}
