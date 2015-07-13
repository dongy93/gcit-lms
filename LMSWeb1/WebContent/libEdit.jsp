<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>

<%LibrarianService librarianService = new LibrarianService();
 List <Branch> branches = librarianService.readBranches();
%>
<%@include file="include.html"%>
<form action="editLib" method="post">
<body>
<table class="table">
		<h2>Hello Librarian - Select your branch</h2>
			<tr>
				<td>Select Branch</td>
				<td>
				<select name="branchId">
					<%for(Branch br: branch){ %>
						<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
					<%} %>
				</select>
				</td>
			
			</tr>

</body>