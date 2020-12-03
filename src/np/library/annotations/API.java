package np.library.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Retention(RUNTIME)
@Documented
public @interface API {
	Level level();
	
	public static enum Level {
		IN_TESTING, 
		BETA,
		ALPHA,
		STABLE,
		DEPRECATED
	}
}
