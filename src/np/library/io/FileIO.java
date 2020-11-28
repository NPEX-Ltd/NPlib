package np.library.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import np.library.exceptions.DeviceCloseException;
import np.library.exceptions.DeviceOpenException;
import np.library.exceptions.DeviceReadException;
import np.library.exceptions.DeviceWriteException;

public class FileIO extends IODevice {
	private BufferedReader fileReader;
	private BufferedWriter fileWriter;
	public FileIO(File file) {
		try {
			file.createNewFile();
			
			fileReader = new BufferedReader(new FileReader(file));
			fileWriter = new BufferedWriter(new FileWriter(file));
		} catch (IOException ioex) {
			throw new DeviceOpenException();
		}
		
	}
	
	@Override
	public void WriteString(String message) {
		try {
			fileWriter.write(message);
			fileWriter.newLine();
			fileWriter.flush();
		} catch (IOException ioex) {
			throw new DeviceWriteException();
		}
	}

	@Override
	public String ReadStringOrBlock() {
		try {
			return fileReader.readLine();
		} catch (IOException ioex) {
			throw new DeviceReadException();
		}
	}
	
	@Override
	public void Close() {
		try {
			fileReader.close();
			fileWriter.close();
		} catch (IOException ioex) {
			throw new DeviceCloseException();
		}
	}

}
