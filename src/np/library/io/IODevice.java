package np.library.io;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public abstract class IODevice implements AutoCloseable {
	public abstract void WriteString(String message);
	public abstract String ReadStringOrBlock();
	
	public void Open() {}
	public void Close() {}
	
	@Override
	public void close() throws Exception {
		Close();
	}
}
