package test;

import java.util.Set;

import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;

public class ClassUtilTest {
@Test
public  void  getClassSetTest(){


	Set<Class<?>> set=ClassUtil.getClassSet("org.smart4j");
	for (Class<?> clazz : set) {
		System.out.println(clazz.getSimpleName());
	}
}

}
