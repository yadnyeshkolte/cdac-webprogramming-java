package com.library;

public class Books {
	int id;
	String title;
	String author;
	String genre;
	int year;
	public Books(int id, String title, String author, String genre, int year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
}
