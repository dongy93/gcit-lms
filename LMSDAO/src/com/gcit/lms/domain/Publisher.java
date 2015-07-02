package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Publisher {
	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;
	/**
	 * @return the publisherId
	 */
	public int getPublisherId() {
		return publisherId;
	}
	/**
	 * @param publisherId the publisherId to set
	 */
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 * @return the publisherName
	 */
	public String getPublisherName() {
		return publisherName;
	}
	/**
	 * @param publisherName the publisherName to set
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	/**
	 * @return the publisherAddress
	 */
	public String getPublisherAddress() {
		return publisherAddress;
	}
	/**
	 * @param publisherAddress the publisherAddress to set
	 */
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	/**
	 * @return the publisherPhone
	 */
	public String getPublisherPhone() {
		return publisherPhone;
	}
	/**
	 * @param publisherPhone the publisherPhone to set
	 */
	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}
	public static void main(String[] args){
		try{
			String className = "com.gcit.lms.domain.Publisher";
			String attrib = "publisherName";
			String attrib2 = "publisherAddress";
			String attrib3 = "publisherPhone";
			
			Publisher a = new Publisher();
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			
			a.setPublisherName("test");
			a.setPublisherAddress("McLean");
			a.setPublisherPhone("703-209-5504");
			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
			setter.invoke(obj, "test");
			Method setter2 = c.getMethod("set"+attrib2.substring(0,1).toUpperCase()+attrib2.substring(1, attrib2.length()), String.class);
			setter2.invoke(obj, "McLean");
			Method setter3 = c.getMethod("set"+attrib3.substring(0,1).toUpperCase()+attrib3.substring(1, attrib3.length()), String.class);
			setter3.invoke(obj, "703-209-5504");
			System.out.println(a.getPublisherName() + " " + a.getPublisherAddress() + " " + a.getPublisherPhone());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
