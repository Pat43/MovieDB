package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Movie {	
	
	int id;
	String name;
	File file;
	File image;
	String synopsis;
	String country;
	String language;
	boolean vo;
	List<Genre> genres;
	
	public List<Integer> getGenreIds(){
		List<Integer> ids = new ArrayList<>();
		for(Genre genre : genres)
			ids.add(genre.getId());
		return ids;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public boolean isVo() {
		return vo;
	}
	public void setVo(boolean vo) {
		this.vo = vo;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	
	
	
}
