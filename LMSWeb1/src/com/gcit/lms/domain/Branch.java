package com.gcit.lms.domain;

import java.lang.reflect.Method;

public class Branch {
	private int branchId;
	private String branchName;
	private String branchAddress;
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchAddress
	 */
	public String getBranchAddress() {
		return branchAddress;
	}
	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	public static void main(String[] args){
		try{
			String className = "com.gcit.lms.domain.Branch";
			String attrib = "branchName";
			String attrib2 = "branchAddress";
			
			Branch a = new Branch();
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			
			a.setBranchName("test");
			a.setBranchAddress("McLean");
			Method setter = c.getMethod("set"+attrib.substring(0,1).toUpperCase()+attrib.substring(1, attrib.length()), String.class);
			setter.invoke(obj, "test");
			Method setter2 = c.getMethod("set"+attrib2.substring(0,1).toUpperCase()+attrib2.substring(1, attrib2.length()), String.class);
			setter2.invoke(obj, "McLean");
			
			System.out.println(a.getBranchName() + " " + a.getBranchAddress());
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
