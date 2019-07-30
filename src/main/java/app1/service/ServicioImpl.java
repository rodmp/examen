package app1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app1.dao.DAO;

@Service
public class ServicioImpl implements Servicio {

	@Autowired
	private DAO<String> dao;
	
	public String add(String t) {
		// TODO Auto-generated method stub
		return dao.add(t);
	}

	public List<String> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	public String search(String t) {
		// TODO Auto-generated method stub
		return dao.search(t);
	}

}
