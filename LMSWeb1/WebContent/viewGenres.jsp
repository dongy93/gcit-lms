<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Genre> genres = null;
	if (request.getAttribute("genres") != null) {
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		genres = adminService.readGenres(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchGenres(){
		$.ajax({
			  url: "searchGenres",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#genresTable').html(data);
			});
	}

</script>
${result }
<form action="searchGenres" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter Genre name to search"><input type="button"
		value="Search!" onclick="javascript:searchGenres();">
</form>

<nav>
  <ul class="pagination">
    <li><a href="pageGenres?pageNo=1">1</a></li>
    <li><a href="pageGenres?pageNo=2">2</a></li>
    <li><a href="pageGenres?pageNo=3">3</a></li>
    <li><a href="pageGenres?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<table class="table" id="genresTable">
	<tr>
		<th>Genre ID</th>
		<th>Genre Name</th>
		<th>Edit Genre</th>
		<th>Delete Genre</th>
	</tr>
	<%
		for (Genre g : genres) {
	%>
	<tr>
		<td>
			<%
				out.println(g.getGenreId());
			%>
		</td>
		<td>
			<%
				out.println(g.getGenreName());
			%>
		</td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal3"
				href="editGenre.jsp?genre_id=<%=g.getGenreId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteGenre?genre_id=<%=g.getGenreId()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

<!-- Modal -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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