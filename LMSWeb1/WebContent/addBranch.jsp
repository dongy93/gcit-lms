<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%@include file="include.html"%>
<%AdministrativeService adminService = new AdministrativeService();
 List<Branch> branches = adminService.readBranches();

%>
<%@include file="include.html"%>
<form action="addBranch" method="post">
	<body>
		<h2>Hello Admin - Enter Branch Details</h2>
		<table class="table">
			<tr>
				<td>Enter Branch Name:</td>
				<td><input type="text" name="branchName" /></td>
			</tr>
			<tr>
				<td>Enter Branch Address:</td>
				<td><input type="text" name="branchAddress" /></td>
			</tr>
		</table>
		<input type="submit">
	</body>
</form>
