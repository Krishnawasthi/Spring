package com.infosys.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infosys.employee.Employee;

public class Driver {
	//here we write the IOC container
	public static void main(String[] args) {
		String configPath = "beans.xml";
		 //creating an IOC container 
		ApplicationContext iocContainer  = new ClassPathXmlApplicationContext(configPath);
		 
	Employee employee = (Employee) iocContainer.getBean("emp1");
	Employee empl = (Employee) iocContainer.getBean("emp2");
	System.out.println(employee.getName()+ " "+ employee.getId());
	System.out.println(empl.getName()+ " "+ empl.getId());
	}

}
