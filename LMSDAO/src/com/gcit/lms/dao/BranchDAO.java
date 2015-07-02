package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Branch;

public class BranchDAO extends BaseDAO {

	public void create(Branch branch) throws Exception {
		save("insert into tbl_library_branch (branchName, branchAddress) values(?, ?)",
				new Object[] { branch.getBranchName(), branch.getBranchAddress()});
	}

	public void update(Branch branch) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchAddress()});

	}

	public void delete(Branch branch) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchAddress()});
	}

	public List<Branch> readAll() throws Exception {
		return (List<Branch>) read("select * from tbl_library_branch", null);
	}

	public Branch readOne(int branchId) throws Exception {
		List<Branch> branches = (List<Branch>) read("select * from tbl_library_branch", new Object[] {branchId});
		if(branches!=null && branches.size()>0){
			return branches.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Branch> branches =  new ArrayList<Branch>();
		
		while(rs.next()){
			Branch a = new Branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			
			branches.add(a);
		}
		return branches;

	}

}