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
	Language language;
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


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	
	
	
}
