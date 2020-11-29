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

}
