package np.library.testing;

import java.lang.annotation.*;

import np.library.annotations.API;
import np.library.annotations.API.Level;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@API(level = Level.ALPHA)
public @interface Test {}