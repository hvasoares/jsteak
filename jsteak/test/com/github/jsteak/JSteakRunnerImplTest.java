package com.github.jsteak;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

import com.github.jsteak.resources.SampleFeature;

public class JSteakRunnerImplTest {

	private Mockery ctx;
	
	@Before
	public void setUp(){
		ctx = new Mockery(){{
			setImposteriser(ClassImposteriser.INSTANCE);
		}}; 
	}


	@Test
	public void shouldGenerateTheDescriptionTree() {
		final Class<SampleFeature> clazz = SampleFeature.class;
		final Description result = ctx.mock(Description.class);
		final DescriptionBuilder desc = ctx.mock(DescriptionBuilder.class);
		
		JSteakRunnerImpl inst = new JSteakRunnerImpl(clazz,desc,new HashMap<Description,Method>());
		
		ctx.checking(new Expectations(){{
			oneOf(desc).addTestDescriptionAndReturn(clazz,"this is a scenario");
			oneOf(desc).addTestDescriptionAndReturn(clazz,"this is another scenario");
			oneOf(desc).setSuiteDescription("Sample Feature");
			oneOf(desc).get(); will(returnValue(result));
		}});
	
		assertEquals(inst.getDescription(),result);
		
		ctx.assertIsSatisfied();
	}
	


}
