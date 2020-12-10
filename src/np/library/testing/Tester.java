package np.library.testing;
import java.lang.reflect.*;

import java.util.Collection;
import java.util.List;

import np.library.annotations.API;
import np.library.annotations.API.Level;
import np.library.common.Timer;
import np.library.exceptions.TestFailedSignal;
@API(level = Level.ALPHA)
public class Tester {
	public static boolean Test(Class<?> clazz) {
		try {
			TestAllMethods(clazz);
		} catch (TestFailedSignal tfsig) {
			System.err.println(tfsig.getMessage());
			System.exit(-1);
		} catch (Exception ex) {
			if(ex.getCause() instanceof TestFailedSignal) {
				TestFailedSignal tfsig = (TestFailedSignal) ex.getCause();
				System.err.println(tfsig.GetMessage());
				System.exit(-1);
			} else {
				ex.printStackTrace();
				System.exit(-10);
			}
		}
		return true;
	}
	
	public static boolean TestExceptions(Class<?> clazz) {
		try {
			TestAllMethods(clazz);
		} catch (Exception ex) {
			return true;
		}
		System.err.println(clazz.getName() + " Didn't Throw Any Exceptions...");
		System.exit(-1);
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private static void TestAllMethods(Class<?> clazz) throws Exception {
		Object instance;
		instance = clazz.newInstance();
		int passedMethodCounter = 0;
		for(Method method : clazz.getDeclaredMethods()) {
			
			if(method.getAnnotation(Test.class) != null) {
				if(method.getExceptionTypes().length < 1) {
					System.err.println("Testing Method " + method);
					if(TestMethod(method, instance)) passedMethodCounter++;
					
				} else {
					System.err.println("Testing Exceptional Method " + method);
					if(TestMethodException(method, instance)) {
						passedMethodCounter++;
					} else {
						Fail("Method Didn't Throw an Exception...");
					} 
				}
			}
		}
		System.out.println(clazz.getName() + " Passed " + passedMethodCounter + " Tests...");
	}
	
	private static boolean TestMethod(Method method, Object instance) {
		try {
			Call(method, instance);
		} catch (TestFailedSignal signal) {
			throw signal;
		} catch (Exception ex) {
			return false;
		}
 		return true;
	}
	
	private static boolean TestMethodException(Method method, Object instance) {
		Timer timer = new Timer();
		timer.Start();
		try {
			Call(method, instance);
		} catch (TestFailedSignal signal) {
			throw signal;
		} catch (Exception ex) {
			return true;
		}
 		return false;
	}
	
	public static void Call(Method method, Object instance) throws Exception {
		Timer timer = new Timer();
		try {
			timer = new Timer();
			timer.Start();
			method.invoke(instance, new Object[0]);
			float time = timer.GetTimeSeconds();
			System.out.println("Completed Test " + method.getName() + " in " + time + " seconds...");
		} catch (Exception e) {
			float time = timer.GetTimeSeconds();
			System.out.println("Completed Test " + method.getName() + " in " + time + " seconds...");
			FilterExceptions(e);
		}
	}
	
	private static void FilterExceptions(Exception e) throws Exception {
		if(e.getCause() instanceof TestFailedSignal) {
			TestFailedSignal tfsig = (TestFailedSignal) e.getCause();
			throw tfsig;
		} else if(!(e.getCause() instanceof ReflectiveOperationException)) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void Fail(String message) {
		throw new TestFailedSignal(message);
	}
	
	@SuppressWarnings("deprecation")
	public static void FailIfLacksDefaultConstructor(Class<?> clazz) {
		try {
			clazz.newInstance();
		} catch (Exception ex) {
			Fail(clazz.getName() + " Lacks A Public Default Constructor...");
		}
	}
	 
	public static <T> void FailIfNotEqual(T objectOne, T objectTwo) {
		if(!objectOne.equals(objectTwo)) {
			Fail(objectOne + " Does Not Equal " + objectTwo);
		}
	}
	
	public static Method GetMethodFromName(String name, Class<?> clazz, Class<?>... params) {
		try {
			return clazz.getMethod(name, params);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			Fail("Unable To Find Method '"+name+" in class " + clazz.getName());
			return null;
		}
	}
	
	public static void FailIfTrue(boolean state) {
		if(state) Fail("Recieved True...");
	}

	public static void FailIfNull(Object object) {
		if(object  == null) {
			Fail("Recieved Null...");
		}
	}
	
	public static void FailIfNotNull(Object object) {
		if(object != null) {
			Fail("Recieved "+object+"...");
		}
	}
	
	public static void FailIfEmptyList(List<?> list) {
		if(list.isEmpty()) {
			Fail("List Is Empty...");
		}
	}
	
	public static void FailIfEmptyCollection(Collection<?> collection) {
		if(collection.isEmpty()) {
			Fail("Collection Is Empty...");
		}
	}
}