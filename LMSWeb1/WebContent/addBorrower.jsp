<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Author"%>
<%@page import="com.gcit.lms.domain.Genre"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%@page import="com.gcit.lms.domain.Borrower" %>
<%AdministrativeService adminService = new AdministrativeService();
 List<Borrower> borrowers = adminService.readBorrowers();
%>
<%@include file="include.html"%>
<form action="addBorrower" method="post">
	<body>
		<h2>Hello Admin - Enter Borrower Details</h2>
		<table class="table">
			<tr>
				<td>Enter Borrower Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter Borrower Address:</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Enter Borrower Phone:</td>
				<td><input type="text" name="phone" /></td>
			</tr>
		</table>
		<input type="submit">
	</body>
</form>
