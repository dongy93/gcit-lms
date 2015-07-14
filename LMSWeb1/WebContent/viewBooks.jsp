<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Book"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Author"%>
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
    <div id="first-container">
    <table class="table" id="booksTable">
	<tr>
		<th>Book Title</th>
		<th>Publisher ID</th>
		<th>Genres</th>
		<th>Authors</th>
		
		<th>Edit Book</th>
		<th>Delete Book</th>
	</tr>
	<%
		for (Book b : books) {
	%>
	<tr>
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
		<td>
			<%
				for (Genre g : b.getGenres()) {
					out.println(g.getGenreName());
				}
			%>
		</td>
		<td>
			<%
				for (Author a : b.getAuthors()) {
					out.println(a.getAuthorName());
				}
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
    
    <div class="my-navigation">
        <div class="simple-pagination-first"></div>
        <div class="simple-pagination-previous"></div>
        <div class="simple-pagination-page-numbers"></div>
        <div class="simple-pagination-next"></div>
        <div class="simple-pagination-last"></div>
    </div>
    <div class="simple-pagination-page-x-of-x"></div>
    <div class="simple-pagination-showing-x-of-x"></div>
    <div>
        Display <select class="simple-pagination-items-per-page"></select> items per page.
    </div>
    <div>
        Go directly to page <select class="simple-pagination-select-specific-page"></select>.
    </div>
</div>
</div>
        <script>
            (function($) {

                $('#first-container').simplePagination({
                    items_per_page:2,
                    number_of_visible_page_numbers: 2
                });
            })(jQuery);
        </script>


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
