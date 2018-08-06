package test;

import java.util.Set;

import org.smart4j.framework.util.ClassUtil;

public class ClassUtilTest {
public static void main(String[] args) {
	Set<Class<?>> set=ClassUtil.getClassSet("org.smart4j");
	for (Class<?> clazz : set) {
		System.out.println(clazz.getSimpleName());
	}
}

}
