package com.github.hvasoares.jsteak;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import com.github.hvasoares.jsteak.ClassReflectionUtils;
import com.github.hvasoares.jsteak.JSteakScenarioRunner;
import com.github.hvasoares.jsteak.resources.SampleFeature;

public class JSteakScenarioRunnerTest {

	private JSteakScenarioRunner inst;
	private Mockery ctx;

	@Test
	public void testGivenADescriptionShouldTestIfItsAMethod() throws SecurityException, NoSuchMethodException {
		ctx = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}};
		Class<SampleFeature> clazz = SampleFeature.class;
		final Map<Description,Method> methodHash = new HashMap<Description,Method>();
		
		final Description succesfullDescription = ctx.mock(Description.class,"succesfullDescription");
		final Method succesfullMethod = clazz.getMethod("thisIsAScenario");
		final Description errorDescription = ctx.mock(Description.class,"errorDescription");
		final Method error = clazz.getMethod("thisIsAnotherScenario");
		
		methodHash.put(succesfullDescription,succesfullMethod);
		methodHash.put(errorDescription,error);
		
		final RunNotifier rn = ctx.mock(RunNotifier.class);
		final ClassReflectionUtils classUtil = ctx.mock(ClassReflectionUtils.class);
		
		ctx.checking(new Expectations(){{
			atLeast(2).of(rn).addListener(with(any(RunListener.class)));
			
			for(Entry<Description, Method> e : methodHash.entrySet()){
				oneOf(rn).fireTestStarted(e.getKey());
				oneOf(rn).fireTestRunStarted(e.getKey());
				
				oneOf(classUtil).run(SampleFeature.class,e.getValue());
				if(e.getValue().equals(error)){
					will(throwException(new RuntimeException()));
					
					oneOf(rn).fireTestFailure(with(any(Failure.class)));
				}
				
				oneOf(rn).fireTestRunFinished(with(any(Result.class)));
				oneOf(rn).fireTestFinished(e.getKey());
			}			
		}});
		
		inst = new JSteakScenarioRunner(SampleFeature.class,methodHash,classUtil);
		inst.run(rn);
		ctx.assertIsSatisfied();
	}

}
