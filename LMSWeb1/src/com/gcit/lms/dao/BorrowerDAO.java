package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO {

	public BorrowerDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Borrower borrower) throws Exception {
		save("insert into tbl_borrower (name, address, phone) values(?, ?, ?)",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });
	}

	public void update(Borrower borrower) throws Exception {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });

	}

	public void delete(Borrower borrower) throws Exception {
		save("delete from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardNo() });
	}

	public List<Borrower> readAll() throws Exception {
		return (List<Borrower>) read("select * from tbl_borrower", null);
	}

	public Borrower readOne(int cardNo) throws Exception {
		List<Borrower> borrowers = (List<Borrower>) read("select * from tbl_borrower where cardNo = ?", new Object[] {cardNo});
		if(borrowers!=null && borrowers.size()>0){
			return borrowers.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Borrower> borrowers =  new ArrayList<Borrower>();
		
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			
			borrowers.add(a);
		}
		return borrowers;

	}

	@Override
	public List extractDataFirstLevel(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		List<Borrower> borrowers =  new ArrayList<Borrower>();
		
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			
			borrowers.add(a);
		}
		return borrowers;
	}
	public List<Borrower> readByBorrowerName(String searchString) throws Exception{
		searchString = "%"+searchString+"%";
		return (List<Borrower>) read("select * from tbl_borrower where name like ?", new Object[] {searchString});
	}
	
	public List<Borrower> readAll(int pageNo, int pageSize) throws Exception{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Borrower>) read("select * from tbl_borrower", null);
		
	}

}