package com.github.hvasoares.jsteak;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

public interface DescriptionGetter {

	public abstract Description getDescription();
}