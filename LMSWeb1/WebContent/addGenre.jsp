<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%AdministrativeService adminService = new AdministrativeService();
 List<Author> authors = adminService.readAuthors();
 List<Genre> genres = adminService.readGenres();
 List<Publisher> pubs = adminService.readPublishers();
%>
<%@include file="include.html"%>
<form action="addGenre" method="post">
	<body>
		<h2>Hello Admin - Enter Genre Details</h2>
		<table class="table">
			<tr>
				<td>Enter Genre Name:</td>
				<td><input type="text" name="genre_name" /></td>
			</tr>

		</table>
		<input type="submit">
	</body>
</form>
