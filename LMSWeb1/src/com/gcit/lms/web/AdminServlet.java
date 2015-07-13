package com.gcit.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;
import com.gcit.lms.service.AdministrativeService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/addPublisher", "/viewAuthors", "/deleteAuthor",
		"/editAuthor", "/addBook", "/searchAuthors", "/pageAuthors", "/addGenre", "/viewGenres",
		"/deleteGenre", "/searchGenres", "/pageGenres", "/editGenre", "/viewPublishers", "/deletePublisher",
		"/searchPublishers", "/pagePublishers", "/editPublisher", "/addBranch", "/viewBranches", "/deleteBranch",
		"/editBranch", "/searchBranches", "/pageBranches", "/addBorrower", "/viewBorrowers", "/deleteBorrower",
		"/editBorrower", "/searchBorrowers", "/pageBorrowers", "/addCopies", "/viewCopies", "/deleteCopies",
		"/editCopies", "/searchCopies", "/pageCopies", "/addLoan", "/viewLoans", "/deleteLoan", "/editLoan",
		"/searchLoans", "/pageLoans", "/viewBooks", "/deleteBook", "/editBook", "/searchBooks", "/pageBooks"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/deleteAuthor": 
			deleteAuthor(request, response);
			break;
		case "/pageAuthors":
			pageAuthors(request, response);
			break;
		case "/searchAuthors":
			searchAuthors(request, response);
			break;
		case "/deleteBook":
			deleteBook(request, response);
			break;
		case "/pageBooks":
			pageBooks(request, response);
			break;
		case "/searchBooks":
			searchBooks(request, response);
			break;
		case "/deleteBranch":
			deleteBranch(request, response);
			break;
		case "/pageBranches":
			pageBranches(request, response);
			break;
		case "/searchBranches":
			searchBranches(request, response);
			break;
		case "/deleteGenre":
			deleteGenre(request, response);
			break;
		case "/pageGenres":
			pageGenres(request, response);
			break;
		case "/searchGenres":
			searchGenres(request, response);
			break;
/*		case "/deleteCopies":
			deleteCopies(request, response);
			break;
		case "/pageCopies":
			pageCopies(request, response);
			break;
		case "/searchCopies":
			searchCopies(request, response);
			break;
		case "/deleteLoans":
			deleteLoans(request, response);
			break;
		case "/pageLoans":
			pageLoans(request, response);
			break;
		case "/searchLoans":
			searchLoans(request, response);
			break;
*/
		case "/deleteBorrower":
			deleteBorrower(request, response);
			break;
		case "/pageBorrowers":
			pageBorrowers(request, response);
			break;
		case "/searchBorrowers":
			searchBorrowers(request, response);
			break;
		case "/deletePublisher":
			deletePublisher(request, response);
			break;
		case "/pagePublishers":
			pagePublishers(request, response);
			break;
		case "/searchPublishers": {
			searchPublishers(request, response);
			break;
		}
		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());
		switch (reqUrl) {
		case "/addAuthor": {
			createAuthor(request, response);
			break;
		}
		case "/addPublisher": {
			createPublisher(request, response);
			break;
		}
/*		case "/addBorrower":
			createBorrower(request, response);
			break;
		case "/addBranch":
			createGenre(request, response);
			break;
		case "/addGenre": {
			createGenre(request, response);
			break;
		}
*/		case "/addBook": {
			addBook(request, response);
			break;
		}
		case "/viewAuthors": {
			viewAuthors(request, response);
			break;
		}
		case "/viewBranches": {
			viewBranches(request, response);
			break;
		}
		case "/viewBorrowers": {
			viewBorrowers(request, response);
			break;
		}
		case "/viewPublishers": {
			viewPublishers(request, response);
			break;
		}
		case "/viewBooks": {
			viewBooks(request, response);
			break;
		}
		case "/viewGenres": {
			viewGenres(request, response);
			break;
		}
		case "/editAuthor": {
			editAuthor(request, response);
			break;
		}
/*		case "/editGenre": {
			editGenre(request, response);
			break;
		}
		case "/editBranch": {
			editBranch(request, response);
			break;
		}
		case "/editBorrower": {
			editBorrower(request, response);
			break;
*/		
		case "/editPublisher": {
			editPublisher(request, response);
			break;
		}
/*		case "/editBook": {
			editBook(request, response);
			break;
		}
*/		case "/searchAuthors": {
			searchAuthors(request, response);
			break;
		}
/*		case "/searchGenres":
			searchGenres(request, response);
			break;
*/		case "/searchPublishers": {
			searchPublishers(request, response);
			break;
}
		case "/searchBooks": {
			searchBooks(request, response);
			break;
		}
/*		case "/searchAuthors":
			searchAuthors(request, response);
			break;
		case "/searchAuthors":
			searchAuthors(request, response);
			break;
*/		default: {
			break;
}
		}
	}

	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookTitle = request.getParameter("bookTitle");
		String[] authorIds = request.getParameterValues("authorId");
		String[] genreIds = request.getParameterValues("genreId");
		int pubId = Integer.parseInt(request.getParameter("pubId"));

		AdministrativeService adminService = new AdministrativeService();
		List<Author> authors = new ArrayList<Author>();
		List<Genre> genres = new ArrayList<Genre>();
		Publisher publisher = new Publisher();
		Book book = new Book();
		book.setTitle(bookTitle);
		try {
			for(String authorId : authorIds) {
				authors.add(adminService.readAuthor(Integer.parseInt(authorId)));	
			}
			book.setAuthors(authors);
			book.setPublisher(adminService.readPublisher(pubId));
			for(String genreId : genreIds) {
				genres.add(adminService.readGenre(Integer.parseInt(genreId)));
			}
			book.setGenres(genres);
			adminService.createBook(book);
			request.setAttribute("result", "Book Added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);

	}
	private void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = new Book();
		book.setBookId(Integer.parseInt(bookId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBooks.jsp");
		try {
			new AdministrativeService().deleteBook(book);

			request.setAttribute("result", "Book Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void pageBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Book> books = new AdministrativeService().readBooks(pageNo, 10);
			request.setAttribute("books", books);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBooks.jsp");
		rd.forward(request, response);
	}
	private void searchBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Book> books =  new AdministrativeService().searchBooks(searchString);
			request.setAttribute("books", books);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Book ID</th><th>Book Name</th><th>Edit Book</th><th>Delete Book</th></tr>");
			for(Book b: books){
				str.append("<tr><td>"+b.getBookId()+"</td><td>"+b.getTitle()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editBook.jsp?bookId="+b.getBookId()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deleteBook?bookId="+b.getBookId()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Book> viewBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readBooks(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void editAuthor(HttpServletRequest request,
			HttpServletResponse response) {
		String authorName = request.getParameter("authorName");
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		Author a = new Author();
		a.setAuthorName(authorName);
		a.setAuthorId(authorId);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updateAuthor(a);
			request.setAttribute("result", "Author updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorName = request.getParameter("authorName");
		Author a = new Author();
		a.setAuthorName(authorName);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createAuthor(a);
			request.setAttribute("result", "Author Added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Author add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);
	}

	private void createPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		Publisher p = new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherAddress(publisherAddress);
		p.setPublisherPhone(publisherPhone);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createPublisher(p);
			request.setAttribute("result", "Publisher added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);
	}

	private List<Author> viewAuthors(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readAuthors(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void pageAuthors(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Author> authors = new AdministrativeService().readAuthors(pageNo, 10);
			request.setAttribute("authors", authors);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		rd.forward(request, response);
	}
	
	private void searchAuthors(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Author> authors =  new AdministrativeService().searchAuthors(searchString);
			request.setAttribute("authors", authors);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Author ID</th><th>Author Name</th><th>Edit Author</th><th>Delete Author</th></tr>");
			for(Author a: authors){
				str.append("<tr><td>"+a.getAuthorId()+"</td><td>"+a.getAuthorName()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editAuthor.jsp?authorId="+a.getAuthorId()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deleteAuthor?authorId="+a.getAuthorId()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewAuthors.jsp");
		try {
			new AdministrativeService().deleteAuthor(author);

			request.setAttribute("result", "Author Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	
	private void createGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genre_name = request.getParameter("genre_name");
		Genre g = new Genre();
		g.setGenreName(genre_name);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.createGenre(g);
			request.setAttribute("result", "Genre Added Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Genre add failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/admin.jsp");
		rd.forward(request, response);
	}
	private List<Genre> viewGenres(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readGenres(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void deleteBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String branchId = request.getParameter("branchId");
		Branch branch = new Branch();
		branch.setBranchId(Integer.parseInt(branchId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBranches.jsp");
		try {
			new AdministrativeService().deleteBranch(branch);

			request.setAttribute("result", "Branch Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void pageBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Branch> branches = new AdministrativeService().readBranches(pageNo, 10);
			request.setAttribute("branches", branches);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBranches.jsp");
		rd.forward(request, response);
	}
	private void searchBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Branch> branches =  new AdministrativeService().searchBranches(searchString);
			request.setAttribute("branches", branches);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Branch ID</th><th>Branch Name</th><th>Edit Branch</th><th>Delete Branch</th></tr>");
			for(Branch br: branches){
				str.append("<tr><td>"+br.getBranchId()+"</td><td>"+br.getBranchName()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editBranch.jsp?branchId="+br.getBranchId()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deleteBranch?branchId="+br.getBranchId()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Branch> viewBranches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readBranches(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardNo = request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardNo));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBorrowers.jsp");
		try {
			new AdministrativeService().deleteBorrower(borrower);

			request.setAttribute("result", "Borrower Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void pageBorrowers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Borrower> borrowers = new AdministrativeService().readBorrowers(pageNo, 10);
			request.setAttribute("borrowers", borrowers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewBorrowers.jsp");
		rd.forward(request, response);
	}
	private void searchBorrowers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Borrower> borrowers =  new AdministrativeService().searchBorrowers(searchString);
			request.setAttribute("borrowers", borrowers);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Card No</th><th>Name</th><th>Edit Borrower</th><th>Delete Borrower</th></tr>");
			for(Borrower bo: borrowers){
				str.append("<tr><td>"+bo.getCardNo()+"</td><td>"+bo.getName()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editBorrower.jsp?cardNo="+bo.getCardNo()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deleteBorrower?cardNo="+bo.getCardNo()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Borrower> viewBorrowers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readBorrowers(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String publisherId = request.getParameter("publisherId");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(publisherId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		try {
			new AdministrativeService().deletePublisher(publisher);

			request.setAttribute("result", "Publisher Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void pagePublishers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Publisher> publishers = new AdministrativeService().readPublishers(pageNo, 10);
			request.setAttribute("publishers", publishers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		rd.forward(request, response);
	}
	private void searchPublishers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Publisher> publishers =  new AdministrativeService().searchPublishers(searchString);
			request.setAttribute("publishers", publishers);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Publisher ID</th><th>Publisher Name</th><th>Edit Publisher</th><th>Delete Publisher</th></tr>");
			for(Publisher p: publishers){
				str.append("<tr><td>"+p.getPublisherId()+"</td><td>"+p.getPublisherName()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editPublisher.jsp?publisherId="+p.getPublisherId()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deletePublisher?publisherId="+p.getPublisherId()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private List<Publisher> viewPublishers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			return new AdministrativeService().readPublishers(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void editPublisher(HttpServletRequest request,
			HttpServletResponse response) {
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		int publisherId = Integer.parseInt(request.getParameter("publisherId"));
		Publisher p = new Publisher();
		p.setPublisherName(publisherName);
		p.setPublisherId(publisherId);
		p.setPublisherAddress(publisherAddress);
		p.setPublisherPhone(publisherPhone);
		AdministrativeService adminService = new AdministrativeService();
		try {
			adminService.updatePublisher(p);
			request.setAttribute("result", "Publisher updated Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher update failed " + e.getMessage());
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewPublishers.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String genre_id = request.getParameter("genre_id");
		Genre genre = new Genre();
		genre.setGenreId(Integer.parseInt(genre_id));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewGenres.jsp");
		try {
			new AdministrativeService().deleteGenre(genre);

			request.setAttribute("result", "Genre Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre Delete Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void pageGenres(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		try {
			List<Genre> genres = new AdministrativeService().readGenres(pageNo, 10);
			request.setAttribute("genres", genres);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/viewGenres.jsp");
		rd.forward(request, response);
	}
	private void searchGenres(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String searchString = request.getParameter("searchString");
		try {
			List<Genre> genres =  new AdministrativeService().searchGenres(searchString);
			request.setAttribute("genres", genres);
			StringBuilder str = new StringBuilder();
			str.append("<tr><th>Genre ID</th><th>Genre Name</th><th>Edit Genre</th><th>Delete Genre</th></tr>");
			for(Genre g: genres){
				str.append("<tr><td>"+g.getGenreId()+"</td><td>"+g.getGenreName()+"</td><td><button type='button' "
						+ "class='btn btn-md btn-success' data-toggle='modal' data-target='#myModal1' href='editGenre.jsp?genre_id="+g.getGenreId()+"'>"
								+ "Edit</button></td><td><button type='button' class='btn btn-md btn-danger' onclick='javascript:location.href="
								+ "'deleteGenre?genreId="+g.getGenreId()+"'>Delete</button></td></tr>");
			}
			response.getWriter().write(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


