package com.h2db.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@ContextConfiguration(locations = "classpath*:applicationServiceDaoContext.xml")
@RunWith(SpringRunner.class)
public class TestRunnerService {

	public static void main(String[] args) {
		
		  Result result = JUnitCore.runClasses(AdminControllerTest.class);
		  
		  for (Failure failure : result.getFailures()) {
		  System.out.println(failure.toString()); }
		  
		  System.out.println(result.wasSuccessful());
		 
		System.out.println("test only");
	}

}
