package defaultPackage;

public class Employee {
	
	private String name;
	private int age;
	private String birthDate;
	private String dept;
	private String mobile;
	private String salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age<0) {
			//throw InvalidAgeException
		}
		else {
			this.age = age;
		}
		
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSalary() {
		return salary;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
		//find the age as per today's date
//		this.age = x
	}
	
	

}
