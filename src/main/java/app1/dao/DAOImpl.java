package app1.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import app1.model.Search;
import app1.model.User;
import app1.service.Levenshtein;

@Service
public class DAOImpl implements DAO<String> {

	@Autowired
	private Environment env;

	@Autowired
	private Levenshtein metric;

	public String add(String t) {
		try {
			File file = new File(env.getProperty("ruta") + env.getProperty("filename"));

			Gson gson = new Gson();

			User user = gson.fromJson(t, User.class);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(gson.toJson(user) + "\n");
			bw.close();

			return "Usuario agregado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public List<String> list() {
		File file = new File(env.getProperty("ruta") + env.getProperty("filename"));

		if (!file.exists()) {
			return null;
		}
		List<String> list = null;
		try {
			list = Files.readAllLines(Paths.get(env.getProperty("ruta") + env.getProperty("filename")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public String search(String t) {
		File file = new File(env.getProperty("ruta") + env.getProperty("filename"));
			
		if (!file.exists()) {
			return null;
		}
		List<User> list = null;
		List<User> list2 = new ArrayList<>();
		try {
			
			Gson gson = new Gson();
			
			Search search = gson.fromJson(t, Search.class);
			
			Stream<String> stream = Files.lines(Paths.get(env.getProperty("ruta") + env.getProperty("filename")));
									
			list = stream.map(s -> gson.fromJson(s, User.class)).collect(Collectors.toList());
			
			int value = 0;
			String s1 = "", s2 = "";
			for(User user: list) {

				if(search.getSearch().length() < user.getName().length()) {
					value =  user.getName().length() - search.getSearch().length();
					s1 = user.getName().trim();
					s2 = search.getSearch().trim();
				}else if(user.getName().length() < search.getSearch().length()) {
					value = search.getSearch().length() - user.getName().length();
					s1 = search.getSearch().trim();
					s2 = user.getName().trim();
				}else {
					value = search.getSearch().length();
				}
				
				//if(metric.distance(search.getSearch(), user.getName()) < value) 
				if(metric.distance(s1, s2) <= value)	
					list2.add(user);
				
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list2.toString();
	}

}
