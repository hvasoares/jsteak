package com.github.hvasoares.jsteak.resources;

import org.junit.runner.RunWith;

import com.github.hvasoares.jsteak.Background;
import com.github.hvasoares.jsteak.Feature;
import com.github.hvasoares.jsteak.JSteakRunnerBuilder;
import com.github.hvasoares.jsteak.Scenario;

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
