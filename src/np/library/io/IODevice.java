package np.library.io;

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
