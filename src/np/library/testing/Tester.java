package np.library.testing;
import java.lang.reflect.*;

import np.library.common.Timer;
import np.library.exceptions.TestFailedSignal;
public class Tester {
	@SuppressWarnings("deprecation")
	public static boolean Test(Class<?> clazz) {
		Object instance;
		try {
			instance = clazz.newInstance();
		} catch (Exception ex) {
			throw new TestFailedSignal("Test Must Have A Public Default Constructor...");
		}
		
		Timer timer = new Timer();
		
		for(Method method : clazz.getDeclaredMethods()) {
			if(method.getAnnotation(Test.class) != null) {
				try {
					timer.Start();
					method.invoke(instance, new Object[0]);
					float time = timer.GetTimeSeconds();
					System.out.println("Ran "+method.getName()+" Completed In "+time+" Seconds...");
				} catch (TestFailedSignal tfex) {
					System.out.println(tfex.getMessage());
					System.exit(-1);
				} catch (InvocationTargetException itex) {
					if(itex.getCause() instanceof TestFailedSignal) {
						System.out.println(((TestFailedSignal)itex.getCause()).getMessage());
						return false;
					}
				} catch (Exception ex) {
					return false;
				}
			}
		}
		return true;
	} 

	public static void Fail(String message) {
		throw new TestFailedSignal(message);
	}
}