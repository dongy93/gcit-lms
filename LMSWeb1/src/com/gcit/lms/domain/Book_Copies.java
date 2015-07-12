package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Book_Copies {
	private Book book;
	private Branch branch;
	private int noOfCopies;
	
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the branch
	 */
	public Branch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	/**
	 * @return the num of copies
	 */
	public int getCopies() {
		return noOfCopies;
	}
	/**
	 * @param noOfCopies the noOfCopies to set
	 */
	public void setCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
