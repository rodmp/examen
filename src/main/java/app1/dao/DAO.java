package app1.dao;

import java.util.List;

public interface DAO<T> {

	String add(T t);
	List<T> list();
	String search(T t);
}
