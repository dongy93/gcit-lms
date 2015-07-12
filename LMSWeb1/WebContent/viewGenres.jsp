<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%AdministrativeService adminService = new AdministrativeService();
 List<Genre> genres = adminService.readGenres();
%>
<%@include file="include.html"%>
${result }

<table class="table">

	<tr>
		<th>Genre ID</th>
		<th>Genre Name</th>
		<th>Edit Author</th>
		<th>Delete Author</th>
	</tr>
	<%for(Genre g: genres){ %>
	<tr>
		<td><%out.println(g.getGenreId()); %></td>
		<td><%out.println(g.getGenreName()); %></td>
		<td><button type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#myModal1" href="editGenre.jsp?genre_id=<%=g.getGenreId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger" onclick="javascript:location.href='deleteAuthor?authorId=<%=g.getGenreId()%>';">Delete</button></td>
	</tr>
	<%} %>
</table>

<div id="myModal1" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    </div>
  </div>
</div>