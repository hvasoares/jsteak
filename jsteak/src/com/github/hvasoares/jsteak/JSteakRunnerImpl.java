package com.github.hvasoares.jsteak;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.github.hvasoares.jsteak.impl.DescriptionBuilderImpl;
import com.github.hvasoares.jsteak.resources.SampleFeature;

public class JSteakRunnerImpl implements DescriptionGetter{
	private Class<?> clazz;
	private DescriptionBuilder descB;
	private Map<Description, Method> methodHash;
	public JSteakRunnerImpl(Class<?> clazz, DescriptionBuilder descB,Map<Description, Method> methodHash) {
		this.clazz = clazz;
		this.descB = descB;
		this.methodHash = methodHash;
	}

	@Override
	public Description getDescription() {
		String[] nameArray = clazz.getName().split("\\.");
		descB.setSuiteDescription(spaceIt(nameArray[nameArray.length-1]));
		addMethods();
		return descB.get();
	}
	
	private void addMethods() {
		Method[] methods = clazz.getMethods();
		for(Method m: methods){
				if(m.isAnnotationPresent(Scenario.class)){
					String name = spaceIt(m.getName()).toLowerCase();	
					methodHash.put(
							descB.addTestDescriptionAndReturn(clazz,name),
							m
					);
				}
		}
	}
	public String spaceIt(String value){
		Pattern p = Pattern.compile("(\\p{Upper})");
		Matcher m = p.matcher(value);
		return m.replaceAll(" $1").replaceAll("^\\s","");
		
	}
}
