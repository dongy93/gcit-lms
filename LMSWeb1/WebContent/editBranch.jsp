<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%AdministrativeService adminService = new AdministrativeService();
 String branchId = request.getParameter("branchId");
 Branch branch = adminService.readBranch(Integer.parseInt(branchId));
%>
<div class="modal-body">
<form action="editBranch" method="post">
			
			<br>Enter Branch Name: <input type="text" name="branchName" value=<%=branch.getBranchName()%>>
			<br>Enter Branch Address: <input type="text" name="branchAddress" value=<%=branch.getBranchAddress()%>>
			<input type="hidden" name="branchId" value=<%=branch.getBranchId() %>>
		<input type="submit"/>
</form>
</div>
