package com.github.jsteak;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import com.github.jsteak.resources.SampleFeature;

public class JSteakScenarioRunner{
	private Class<?> clazz;
	private Map<Description, Method> methodHash;
	private ClassReflectionUtils classUtil;
	
	public JSteakScenarioRunner(Class<?> clazz,Map<Description, Method> methodHash, ClassReflectionUtils classUtil) {
		super();
		this.clazz = clazz;
		this.methodHash = methodHash;
		this.classUtil = classUtil;
	}

	public void run(RunNotifier rn) {
		for(Entry<Description, Method> e : methodHash.entrySet()){
			Description description = e.getKey();
			Result r = new Result();
			rn.addListener(r.createListener());

			rn.fireTestStarted(description);
			rn.fireTestRunStarted(description);
			try{
				classUtil.run(clazz, e.getValue());
			}catch(Throwable ex){
				rn.fireTestFailure(new Failure(description, ex));
			}
			rn.fireTestRunFinished(r);
			rn.fireTestFinished(description);
		}
	}
}