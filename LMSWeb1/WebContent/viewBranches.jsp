<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Branch"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Branch> branches = null;
	if (request.getAttribute("branches") != null) {
		branches = (List<Branch>) request.getAttribute("branches");
	} else {
		branches = adminService.readBranches(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchBranches(){
		$.ajax({
			  url: "searchBranches",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#branchesTable').html(data);
			});
	}

</script>
${result }
<form action="searchBranches" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter Branch Name to search"><input type="button"
		value="Search" onclick="javascript:searchBranches();">
</form>

<nav>
  <ul class="pagination">
    <li><a href="pageBranches?pageNo=1">1</a></li>
    <li><a href="pageBranches?pageNo=2">2</a></li>
    <li><a href="pageBranches?pageNo=3">3</a></li>
    <li><a href="pageBranches?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
<table class="table">

	<tr>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th>Edit Publisher</th>
		<th>Delete Publisher</th>
	</tr>
	<%for(Branch br: branches){ %>
	<tr>
		<td><%out.println(br.getBranchName()); %></td>
		<td><%out.println(br.getBranchAddress()); %></td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal2"
				href="editBranch.jsp?branchId=<%=br.getBranchId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=br.getBranchId()%>';">Delete</button></td>
	</tr>
	<%} %>
</table>



<!-- Modal -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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