package com.github.jsteak;

import org.junit.runner.Description;

public interface DescriptionBuilder {
	public void setSuiteDescription(String description);
	public Description addTestDescriptionAndReturn(Class<?> clazz, String description);
	public Description get();
}
