<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Book> books = null;
	if (request.getAttribute("books") != null) {
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = adminService.readBooks(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchBooks(){
		$.ajax({
			  url: "searchBooks",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#booksTable').html(data);
			});
		$(document).ready(function(){
		    $('[data-toggle="popover"]').popover();   
		});
	}

</script>
${result }
<form action="searchBooks" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter title to search"><input type="button"
		value="Search" onclick="javascript:searchBooks();">
</form>

<nav>
  <ul class="pagination">
    <li><a href="pageBooks?pageNo=1">1</a></li>
    <li><a href="pageBooks?pageNo=2">2</a></li>
    <li><a href="pageBooks?pageNo=3">3</a></li>
    <li><a href="pageBooks?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<table class="table" id="booksTable">
	<tr>
		<th>Book ID</th>
		<th>Book Title</th>
		<th>Publisher ID</th>
		
		<th>Edit Book</th>
		<th>Delete Book</th>
	</tr>
	<%
		for (Book b : books) {
	%>
	<tr>
		<td>
			<%
				out.println(b.getBookId());
			%>
		</td>
		<td>
			<%
				out.println(b.getTitle());
			%>
		</td>
		<td>
			<%
				out.println(b.getPublisher().getPublisherId());
			%>
		</td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editBook.jsp?bookId=<%=b.getBookId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

<!-- Modal -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
