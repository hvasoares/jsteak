package com.github.hvasoares.jsteak;

import org.junit.runner.Description;

public interface DescriptionBuilder {
	public void setSuiteDescription(String description);
	public Description addTestDescriptionAndReturn(Class<?> clazz, String description);
	public Description get();
}
