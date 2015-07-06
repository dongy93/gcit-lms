package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Book_Loans;
import com.gcit.lms.domain.Borrower;
public class Book_LoansDAO extends BaseDAO<Book_Loans>{
	public Book_LoansDAO(Connection conn) throws Exception {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book_Loans book_loans) throws Exception {
		save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values(?, ?, ?, current_timestamp, date_add(current_timestamp, interval 7 day)",
				new Object[] { book_loans.getBook().getBookId(), book_loans.getBranch().getBranchId(), book_loans.getBorrower().getCardNo()});
	}

	public void update(Book_Loans book_loans) throws Exception {
		save("update tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { book_loans.getDateOut(), book_loans.getDueDate(), book_loans.getDateIn(), book_loans.getBook().getBookId(), book_loans.getBranch().getBranchId(), book_loans.getBorrower().getCardNo() });

	}

	public void delete(Book_Loans book_loans) throws Exception {
		save("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { book_loans.getBook().getBookId(), book_loans.getBranch().getBranchId(), book_loans.getBorrower().getCardNo() });
	}

	public List<Book_Loans> readAll() throws Exception {
		return (List<Book_Loans>) read("select * from tbl_book_loans", null);
	}

	public Book_Loans readOne(int bookId, int branchId) throws Exception {
		List<Book_Loans> book_loans = (List<Book_Loans>) read("select * from tbl_book_loans", new Object[] {bookId, branchId});
		if(book_loans!=null && book_loans.size()>0){
			return book_loans.get(0);
		}
		return null;
	}
	@Override
	public List extractData(ResultSet rs) throws Exception {
		List<Book_Loans> book_loans =  new ArrayList<Book_Loans>();
		BookDAO bdao = new BookDAO(getConnection());
		BranchDAO brdao = new BranchDAO(getConnection());
		BorrowerDAO bodao = new BorrowerDAO(getConnection());
		
		while(rs.next()){
			Book_Loans bl = new Book_Loans();
			bl.setBook(bdao.readOne(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pdao.readOne(rs.getInt("pubId")));
			@SuppressWarnings("unchecked")
			List<Author> authors = (List<Author>) aDao.readFirstLevel("select * from tbl_author where authorId In"
					+ "(select authorId from tbl_book_authors where bookId=?)", new Object[] {rs.getInt("bookId")});
			b.setAuthors(authors);
			books.add(b);
		}
		
		return books;
	}

	@Override
	public List<Book_Loans> extractDataFirstLevel(ResultSet rs)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
