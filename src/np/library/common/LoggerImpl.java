package np.library.common;

class LoggerImpl extends Logger {
	protected LoggerImpl(String name) {
		super(name);
	}

	protected LoggerImpl(Class<?> clazz) {
		super(clazz);
	}

	public void Info(Object... args) {Print(args);}
	public void Debug(Object... args) {Print(args);}
	public void Warn(Object... args) {Print(args);}
	
	private void Print(Object... args) {
		System.out.print("["+name+"]: ");
		for(Object obj : args) System.out.print(obj);
		System.out.println();
	}
	
	
}