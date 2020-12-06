package np.library.gui;

import java.util.HashMap;

import np.library.common.Pair;


public class ActionMap<T> {
	private HashMap<String, T> actions = new HashMap<>();

	public ActionMap() {}
	@SafeVarargs
	public ActionMap(Pair<String, T>... actionList) {
		for(Pair<String, T> pair : actionList) {
			AddPair(pair);
		}
	}
	
	public T get(Object key) {
		return actions.get(key);
	}

	public T put(String key, T value) {
		return actions.put(key, value);
	}
	
	public void AddPair(Pair<String, T> pair) {
		put(pair.first, pair.second);
	}
	
	public HashMap<String, T> ToHashMap() { return actions; }
	
}
