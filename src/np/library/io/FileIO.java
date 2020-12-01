package np.library.io;

import java.io.File;

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
