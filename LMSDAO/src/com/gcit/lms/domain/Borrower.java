package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Borrower {
	private int cardNo;
	private String name;
	private String address;
	private String phone;
	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static void main(String[] args){
		try{
			String className = "com.gcit.lms.domain.Borrower";
			String attrib = "name";
			String attrib2 = "address";
			String attrib3 = "phone";
			
			Borrower a = new Borrower();
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			
			a.setName("test");
			a.setAddress("McLean");
			a.setPhone("703-209-5504");
			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
			setter.invoke(obj, "test");
			Method setter2 = c.getMethod("set"+attrib2.substring(0,1).toUpperCase()+attrib2.substring(1, attrib2.length()), String.class);
			setter2.invoke(obj, "McLean");
			Method setter3 = c.getMethod("set"+attrib3.substring(0,1).toUpperCase()+attrib3.substring(1, attrib3.length()), String.class);
			setter3.invoke(obj, "703-209-5504");
			System.out.println(a.getName() + " " + a.getAddress() + " " + a.getPhone());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}