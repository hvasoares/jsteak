package com.github.jsteak;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.github.jsteak.impl.ClassReflectionUtilImpl;
import com.github.jsteak.impl.DescriptionBuilderImpl;

public class JSteakRunnerBuilder extends Runner {
	
	private JSteakRunnerImpl descriptionGetter;
	private HashMap<Description, Method> methodHash;
	private JSteakScenarioRunner runner;
	private Class<?> clazz;
	private ClassReflectionUtils defaultClassUtil;
	
	public JSteakRunnerBuilder(Class<?> clazz) {
		this.clazz = clazz;
		descriptionGetter = new JSteakRunnerImpl(
				clazz, 
				new DescriptionBuilderImpl(),
				methodHash = new HashMap<Description,Method>()		
		);
		defaultClassUtil = new ClassReflectionUtilImpl();
	}

	@Override
	public Description getDescription() {
		return getDescriptionGetter().getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		getRunner().run(notifier);
	}

	public JSteakRunnerImpl getDescriptionGetter() {
		return descriptionGetter;
	}
	public JSteakScenarioRunner getRunner() {
		if(runner==null)
			runner = new JSteakScenarioRunner(
				clazz,
				methodHash, 
				defaultClassUtil
			);
		return runner;
	}
	
	public void setDefaultClassUtil(ClassReflectionUtils defaultClassUtil) {
		this.defaultClassUtil = defaultClassUtil;
	}
}
