package learning.beans.creation.config.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import learning.beans.creation.java.Employee;

@Configuration  //this class is the source of bean definition
public class SpringConfig {
	
	//here we will create beans and give the values to beans
	
	@Bean("emp1") //telling Spring framework to manage employee bean
	public Employee createEmp() {
		
		Employee emp = new Employee();
		emp.setBand("b1");
		emp.setId("km123");
		emp.setName("Krishna awasthi");
		return emp;
		
	}
	
	@Bean("emp2") //telling Spring framework to manage employee bean
	public Employee createEmp1() {
		
		Employee emp = new Employee();
		emp.setBand("b2");
		emp.setId("subham123");
		emp.setName("shubham awasthi");
		return emp;
		
	}
}
