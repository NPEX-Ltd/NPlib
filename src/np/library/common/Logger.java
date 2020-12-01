package np.library.common;

import np.library.common.strings.StringFormatter;

public abstract class Logger {
	protected final String name;
	protected StringFormatter formatter;

	public abstract void Info(Object... args);
	public abstract void Debug(Object... args);
	public abstract void Warn(Object... args);

	protected Logger(String name) {
		this.name = name;
	}

	protected Logger(Class<?> clazz) {
		this(clazz.getName());
	}

	public static Logger CreateNew(Class<?> clazz) {
		return new LoggerImpl(clazz);
	}
}