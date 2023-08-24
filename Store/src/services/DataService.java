package services;

import java.util.List;

import intefaces.Accessible;


public interface DataService {
	
	String insert(Accessible element);
	boolean exist(String code);
	Accessible get(String code);
	Integer delete(String code);
	List<Accessible> getAll();
	String update(Accessible element);

}
