package com.gcit.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.lms.dao.*;
import com.gcit.lms.domain.*;


public class AdministrativeService {
	public void createAuthor(Author author) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author Name cannot be empty or more than 45 Chars");
			} else {
				AuthorDAO adao = new AuthorDAO(conn);
				adao.create(author);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void createPublisher(Publisher publisher) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.create(publisher);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Author> readAuthors() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		return adao.readAll();
	}
	public List<Author> readAuthors(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		return adao.readAll(pageNo, pageSize);
	}

	public Author readAuthor(int authorId) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		return adao.readOne(authorId);
	}
	public void deleteAuthor(Author author) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		try {
			adao.delete(author);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void updateAuthor(Author a) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			AuthorDAO adao = new AuthorDAO(conn);
			adao.update(a);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}
	public List<Author> searchAuthors(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		AuthorDAO adao = new AuthorDAO(conn);
		return adao.readByAuthorName(searchString);
	}

	public void createBook(Book book) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book Name cannot be empty or more than 45 Chars");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.create(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void updateBook(Book book) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception(
						"Book Name cannot be empty or more than 45 Chars");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.update(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	public void deleteBook(Book book) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (book == null || book.getTitle() == null) {
				throw new Exception(
						"This is not an existing book");
			} else {
				BookDAO bdao = new BookDAO(conn);
				bdao.update(book);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Book> searchBooks(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BookDAO bdao = new BookDAO(conn);
		return bdao.readByTitle(searchString);
	}
	public List<Book> readBooks(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BookDAO bdao = new BookDAO(conn);
		return bdao.readAll(pageNo, pageSize);
	}
	public Genre readGenre(int genreId) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		GenreDAO gdao = new GenreDAO(conn);
		return gdao.readOne(genreId);
	}
	public List<Genre> readGenres() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		GenreDAO gdao = new GenreDAO(conn);
		return gdao.readAll();
	}
	public List<Genre> readGenres(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		GenreDAO gdao = new GenreDAO(conn);
		return gdao.readAll(pageNo, pageSize);
	}
	public void deleteGenre(Genre genre) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		GenreDAO gdao = new GenreDAO(conn);
		try {
			gdao.delete(genre);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void createGenre(Genre genre) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			if (genre == null || genre.getGenreName() == null
					|| genre.getGenreName().length() == 0
					|| genre.getGenreName().length() > 45) {
				throw new Exception(
						"Genre Name cannot be empty or more than 45 Chars");
			} else {
				GenreDAO gdao = new GenreDAO(conn);
				gdao.create(genre);
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public void updateGenre(Genre g) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			GenreDAO gdao = new GenreDAO(conn);
			gdao.update(g);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Genre> searchGenres(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		GenreDAO gdao = new GenreDAO(conn);
		return gdao.readByGenreName(searchString);
	}
	public List<Publisher> readPublishers() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		return pdao.readAll();
	}

	public Publisher readPublisher(int publisherId) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		return pdao.readOne(publisherId);
	}
	public void deletePublisher(Publisher publisher) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		try {
			pdao.delete(publisher);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

	}
	public List<Publisher> searchPublishers(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		return pdao.readByPublisherName(searchString);
	}
	public List<Publisher> readPublishers(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		PublisherDAO pdao = new PublisherDAO(conn);
		return pdao.readAll(pageNo, pageSize);
	}
	public void createBranch(Branch branch) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			BranchDAO brdao = new BranchDAO(conn);
			brdao.create(branch);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Branch> readBranches() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readAll();
	}

	public Branch readBranch(int branchId) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readOne(branchId);
	}
	public void deleteBranch(Branch branch) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		try {
			brdao.delete(branch);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Branch> searchBranches(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readByBranchName(searchString);
	}
	public List<Branch> readBranches(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BranchDAO brdao = new BranchDAO(conn);
		return brdao.readAll(pageNo, pageSize);
	}
	public void createBorrower(Borrower borrower) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		try {
			BorrowerDAO bodao = new BorrowerDAO(conn);
			bodao.create(borrower);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Borrower> readBorrowers() throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BorrowerDAO bodao = new BorrowerDAO(conn);
		return bodao.readAll();
	}

	public Borrower readBorrower(int cardNo) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BorrowerDAO bodao = new BorrowerDAO(conn);
		return bodao.readOne(cardNo);
	}
	public void deleteBorrower(Borrower borrower) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BorrowerDAO bodao = new BorrowerDAO(conn);
		try {
			bodao.delete(borrower);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}
	public List<Borrower> searchBorrowers(String searchString) throws Exception{
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BorrowerDAO bodao = new BorrowerDAO(conn);
		return bodao.readByBorrowerName(searchString);
	}
	public List<Borrower> readBorrowers(int pageNo, int pageSize) throws Exception {
		ConnectionUtil c = new ConnectionUtil();
		Connection conn = c.createConnection();
		BorrowerDAO bodao = new BorrowerDAO(conn);
		return bodao.readAll(pageNo, pageSize);
	}
}


