package np.library.common;

import np.library.annotations.API;
import np.library.annotations.API.Level;

@API(level = Level.ALPHA)
public class Pair<T1, T2> {
	public T1 first;
	public T2 second;
	
	public Pair(T1 first, T2 second) {
		super();
		this.first = first;
		this.second = second;
	}
}
