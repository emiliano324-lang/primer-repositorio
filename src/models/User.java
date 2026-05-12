package models;

public class User {
	
	private int id;
	private String name;
	private String password;
	private String confirmPassword;
	private String email;
	private String sex;
	private String imagePath;
	
	public User() {
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String email, String password, String sex, String imagePath) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.imagePath = imagePath;
	}
	
	public User(int id, String name, String email, String sexo, String imagePath) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sexo;
		this.imagePath = imagePath;
	}
	
	public User(String name, String email, String sexo, String imagePath) {
		this.name = name;
		this.email = email;
		this.sex = sexo;
		this.imagePath = imagePath;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public int getId() {
		return id;
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
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String toCsv() {
		return name + "," + email + "," + sex;
	}
	
	public static User fromCsv(String userData) {
		
		String data[] = userData.split(",");
		
		String name = data[0];
		String email = data[1];
		String sexo = data[2];
		
		return new User(name, email, sexo, null);
	}
}
