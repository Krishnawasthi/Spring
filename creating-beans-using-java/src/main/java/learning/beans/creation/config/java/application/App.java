package learning.beans.creation.config.java.application;

import java.lang.ref.PhantomReference;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learning.beans.creation.config.java.SpringConfig;
import learning.beans.creation.java.Employee;

public class App {

	public static void main(String[] args) {
		
		//creating an ioc container
		System.out.println("Creating spring bean using java!!!!!!!!!!!!!!");
		ApplicationContext iocContainer = new AnnotationConfigApplicationContext(SpringConfig.class);
		
	   Employee emp =( Employee) iocContainer.getBean("emp1");  //requesting bean
	   
	    String result =  emp.toString();
	    System.out.println(result);
	    
	    Employee emp1 =( Employee) iocContainer.getBean("emp2");  //requesting bean
		   
	    String result1 =  emp1.toString();
	    System.out.println(result1);
		
		

	}

}
