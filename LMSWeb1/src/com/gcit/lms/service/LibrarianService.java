package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.Book_LoansDAO;
import com.gcit.lms.dao.Book_CopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.domain.Book_Copies;
import com.gcit.lms.domain.Branch;

public class LibrarianService {
	public void upLibBranch(Branch branch) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			BranchDAO brdao = new BranchDAO(conn);
			brdao.update(branch);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Branch> libReadBranches() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readAll();
	}

	public Branch libReadBranch(int branchId) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readOne(branchId);
	}
	public void upLibBookCopies(Book_Copies bc) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			Book_CopiesDAO bcdao = new Book_CopiesDAO(conn);
			bcdao.update(bc);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
}
