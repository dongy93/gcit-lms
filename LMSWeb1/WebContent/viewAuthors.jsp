<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Author> authors = null;
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = adminService.readAuthors(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchAuthors(){
		$.ajax({
			  url: "searchAuthors",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#authorsTable').html(data);
			});
	}

</script>
${result }
<form action="searchAuthors" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter title name to search"><input type="button"
		value="Search!" onclick="javascript:searchAuthors();">
</form>

<nav>
  <ul class="pagination">
    <li><a href="pageAuthors?pageNo=1">1</a></li>
    <li><a href="pageAuthors?pageNo=2">2</a></li>
    <li><a href="pageAuthors?pageNo=3">3</a></li>
    <li><a href="pageAuthors?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<table class="table" id="authorsTable">
	<tr>
		<th>Author Name</th>
		<th>Edit Author</th>
		<th>Delete Author</th>
	</tr>
	<%
		for (Author a : authors) {
	%>
	<tr>
		<td>
			<%
				out.println(a.getAuthorName());
			%>
		</td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editAuthor.jsp?authorId=<%=a.getAuthorId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>';">Delete</button></td>
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
