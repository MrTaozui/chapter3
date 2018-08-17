package test.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjj .
 */
public class MyThreadLocal<T> {
    private Map<Thread,T> container= Collections.synchronizedMap(new HashMap<Thread, T>());
}
