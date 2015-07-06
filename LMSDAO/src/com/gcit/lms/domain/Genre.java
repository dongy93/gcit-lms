package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Genre {
	private int genreId;
	private String genreName;
	/**
	 * @return the genreId
	 */
	public int getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
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
