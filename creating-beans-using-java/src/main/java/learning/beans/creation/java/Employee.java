package learning.beans.creation.java;

//this is where we create or pojo class
public class Employee {
	 String band;
	 String id;
	 String name;
	 public String getBand() {
		 return band;
	 }
	 public void setBand(String band) {
		 this.band = band;
	 }
	 public String getId() {
		 return id;
	 }
	 public void setId(String id) {
		 this.id = id;
	 }
	 public String getName() {
		 return name;
	 }
	 public void setName(String name) {
		 this.name = name;
	 }
	 @Override
	 public String toString() {
		return "Employee [band=" + band + ", id=" + id + ", name=" + name + "]";
	 }
	
}
