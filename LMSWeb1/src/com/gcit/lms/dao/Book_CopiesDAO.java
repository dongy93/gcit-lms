package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book_Copies;
import com.gcit.lms.domain.Borrower;

public class Book_CopiesDAO extends BaseDAO<Book_Copies> {
	public Book_CopiesDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book_Copies book_copies) throws Exception {
		save("insert into tbl_book_copies (bookId, branchId, noOfCopies) values(?, ?, ?)",
				new Object[] { book_copies.getBook().getBookId(), book_copies.getBranch().getBranchId(), book_copies.getCopies() });
	}

	public void update(Book_Copies book_copies) throws Exception {
		save("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] { book_copies.getCopies(), book_copies.getBook().getBookId(), book_copies.getBranch().getBranchId() });

	}

	public void delete(Book_Copies book_copies) throws Exception {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { book_copies.getBook().getBookId(), book_copies.getBranch().getBranchId()  });
	}

	public List<Book_Copies> readAll() throws Exception {
		return (List<Book_Copies>) read("select * from tbl_book_copies", null);
	}

	public Book_Copies readOne(int bookId, int branchId) throws Exception {
		List<Book_Copies> book_copies = (List<Book_Copies>) read("select * from tbl_book_copies where bookId = ?, branchId = ?", new Object[] {bookId, branchId});
		if(book_copies!=null && book_copies.size()>0){
			return book_copies.get(0);
		}
		return null;
	}

	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Book_Copies> book_copies =  new ArrayList<Book_Copies>();
		BookDAO bdao = new BookDAO(getConnection());
		BranchDAO brdao = new BranchDAO(getConnection());
		
		while(rs.next()){
			Book_Copies bc = new Book_Copies();
			bc.setBook(bdao.readOne(rs.getInt("bookId")));
			bc.setBranch(brdao.readOne(rs.getInt("branchId")));
			bc.setCopies(rs.getInt("noOfCopies"));
			
			book_copies.add(bc);
		}
		return book_copies;

	}

	@Override
	public List<Book_Copies> extractDataFirstLevel(ResultSet rs)
			throws Exception {
		// TODO Auto-generated method stub
		List<Book_Copies> book_copies =  new ArrayList<Book_Copies>();
		BookDAO bdao = new BookDAO(getConnection());
		BranchDAO brdao = new BranchDAO(getConnection());
		
		while(rs.next()){
			Book_Copies bc = new Book_Copies();
			bc.setCopies(rs.getInt("noOfCopies"));
			
			book_copies.add(bc);
		}
		return book_copies;

	}
	public List<Book_Copies> readByBookCopyTitle(String searchString) throws Exception{
		searchString = "%"+searchString+"%";
		return (List<Book_Copies>) read("select * from tbl_book_copies join tbl_book where tbl_book.title like ?", new Object[] {searchString});
	}
	
	public List<Book_Copies> readAll(int pageNo, int pageSize) throws Exception{
		setPageNo(pageNo);
		setPageSize(pageSize);
		return (List<Book_Copies>) read("select * from tbl_book_copies", null);
		
	}

}
