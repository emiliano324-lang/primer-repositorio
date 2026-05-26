package models;

public class User {
	
	private int id;
	private String name;
	private String password;
	private String confirmPassword;
	private String email;
	private String sex;
	private Player player;

	
	public User() {
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public User(String name, String email, String password, String sex) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.sex = sex;
	}
	
	public User(int id, String name, String email, String sex) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.sex = sex;
	}
	
	public User(String name, String email, String sex) {
		this.name = name;
		this.email = email;
		this.sex = sex;
	}
	public User(int id,String name, String email, String password, String sex,Player player) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.setPlayer(player);
	}
	
	public User(int id,Player  player) {
		this.setPlayer(player);
	}
	
	
	/*public User(int id, String email, String password) {
	this.id = id;
	this.email = email;
	this.name = name;
	this.password = password;
	this.confirmPassword = confirmPassword;
	}*/
	
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
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/*public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}*/
	
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
