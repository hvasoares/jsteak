package com.github.jsteak.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.jsteak.ClassReflectionUtils;

public class ClassReflectionUtilImpl implements ClassReflectionUtils {

	@Override
	public void run(Class<?> class1, Method method) {
		try {
			method.invoke(createObject(class1));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("The test method must have arity 0",e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("The test method must be public",e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("The test not passed because previous errors",e);
		}
	}

	private Object createObject(Class<?> class1) {
		Constructor<?>[] cs = class1.getConstructors();
		Object object;
		try {
			object = cs[0].newInstance();
		} catch (IllegalArgumentException e1) {
			throw new RuntimeException("The feature class must have one and only one default constructor",e1);
		} catch (InstantiationException e1) {
			throw new RuntimeException("The test class couldn't be instantiated",e1);
		} catch (IllegalAccessException e1) {
			throw new RuntimeException("The constructor of this class is private or protected",e1);
		} catch (InvocationTargetException e1) {
			throw new RuntimeException("Couldnt create the feature object check previous errors",e1);
		}
		return object;
	}
	
}
