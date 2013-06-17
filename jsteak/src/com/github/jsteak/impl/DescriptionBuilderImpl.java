package com.github.jsteak.impl;

import java.util.HashMap;

import org.junit.runner.Description;

import com.github.jsteak.DescriptionBuilder;

public class DescriptionBuilderImpl implements DescriptionBuilder{

	private String name;
	private HashMap<Class<?>, String> map;
	private Description suite;

	public DescriptionBuilderImpl(){
		map = new HashMap<Class<?>,String>();
	}
	
	@Override
	public void setSuiteDescription(String value) {
		this.name = value;
		suite = Description.createSuiteDescription(name);
	}

	@Override
	public Description addTestDescriptionAndReturn(Class<?> clazz, String description) {
		this.map.put(clazz,description);
		Description result = Description.createTestDescription(clazz,description);
		suite.addChild(result);
		return result;
	}

	@Override
	public Description get() {
		return suite;
	}

}
