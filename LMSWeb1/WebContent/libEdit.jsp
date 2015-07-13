<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>

<%LibrarianService librarianService = new LibrarianService();
 List <Branch> branches = librarianService.libReadBranches();
%>
<%@include file="include.html"%>
<form action="editLib" method="post">

	<h2>Hello Librarian - Select your branch</h2>
	<table class="table">
		
			<tr>
				<td>Select Branch</td>
				<td>
				<select name="branchId">
					<%for(Branch br: branches){ %>
						<option value=<%=br.getBranchId()%>><%=br.getBranchName() %></option>
					<%} %>
				</select>
				</td>
			
			</tr>
	</table>

</form>