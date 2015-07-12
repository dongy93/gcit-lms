package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Genre {
	private int genre_Id;
	private String genre_Name;
	/**
	 * @return the genreId
	 */
	public int getGenreId() {
		return genre_Id;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(int genre_Id) {
		this.genre_Id = genre_Id;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genre_Name;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genre_Name) {
		this.genre_Name = genre_Name;
	}
	
	public static void main(String[] args){
		try{
			String className = "com.gcit.lms.domain.Genre";
			String attrib = "genre_name";
			
			Genre a = new Genre();
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			
			a.setGenreName("test");
			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
			setter.invoke(obj, "test");
			
			System.out.println(a.getGenreName());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
