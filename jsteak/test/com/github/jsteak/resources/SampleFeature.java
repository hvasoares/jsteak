package com.github.jsteak.resources;

import org.junit.runner.RunWith;

import com.github.jsteak.Background;
import com.github.jsteak.Feature;
import com.github.jsteak.JSteakRunnerBuilder;
import com.github.jsteak.Scenario;

@RunWith(JSteakRunnerBuilder.class)
@Feature
public class SampleFeature {	
	@Scenario
	public void thisIsAScenario(){
		throw new RuntimeException("mmmm");
	}
	@Scenario
	public void thisIsAnotherScenario(){
		
	}
}
