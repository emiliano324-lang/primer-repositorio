package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.User;

public class UserRepository {

	private final String FILE = "src/assets/files/users.json";
	private final ObjectMapper mapper =
			new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

	public void save(User user) throws IOException {
		
		List<User> users = getUsers();
		users.add(user);
		updateAll(users);
		
		/*try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true)))){
			writer.write(user.toCsv());
			writer.newLine();
		}*/
	}


	public List<User> getUsers() throws IOException{
		
		File file = new File(FILE);
		
		if(!file.exists() || file.length() == 0) {
			return new ArrayList<>();
		}
				
		return mapper.readValue(file, new TypeReference<List<User>>() {} );
		
		/*List<User> users = new ArrayList<User>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
			String line;
			
			while((line = reader.readLine()) != null) {
				User user = User.fromCsv(line);
				users.add(user);
			}
		}
		
		return users;*/
	}
	
	public void updateAll(List<User> users) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(FILE), StandardCharsets.UTF_8))) {

	        for (User user : users) {
	            writer.write(user.toCsv());
	            writer.newLine();
	        }
	    }
	}
	 
	public void delete(int index) throws IOException {
		List<User> users = getUsers();
		users.remove(index);
		updateAll(users);
	}
	
	public void update(int index, User updatedUser) throws IOException {
		List<User> users = getUsers();
		users.set(index, updatedUser);
		updateAll(users);
	}
}