package models;

public class User {
	
	private String name;
	private String password;
	private String confirmPassword;
	private String email;
	private String sex;
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String email, String password, String confirmPassword, String sex) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.sex = sex;
	}
	
	public User(String name, String email, String sexo) {
		this.name = name;
		this.email = email;
		this.sex = sexo;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSex() {
		return sex;
	}
	
	public String toCsv() {
		return name + "," + email + "," + sex;
	}
	
	public static User fromCsv(String userData) {
		
		String data[] = userData.split(",");
		
		String name = data[0];
		String email = data[1];
		String sexo = data[2];
		
		return new User(name, email, sexo);
	}
}
