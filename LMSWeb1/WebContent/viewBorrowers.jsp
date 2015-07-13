<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Borrower"%>
<%
	AdministrativeService adminService = new AdministrativeService();
	List<Borrower> borrowers = null;
	if (request.getAttribute("borrowers") != null) {
		borrowers = (List<Borrower>) request.getAttribute("borrowers");
	} else {
		borrowers = adminService.readBorrowers(0, 10);

	}
%>
<%@include file="include.html"%>
<script>
	function searchBorrowers(){
		$.ajax({
			  url: "searchBorrowers",
			  data: {
				  searchString: $('#searchString').val()
			  }
			}).done(function(data) {
				$('#borrowersTable').html(data);
			});
	}

</script>
${result }
<form action="searchBorrowers" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter Borrower name to search"><input type="button"
		value="Search!" onclick="javascript:searchBorrowers();">
</form>

<nav>
  <ul class="pagination">
    <li><a href="pageBorrowers?pageNo=1">1</a></li>
    <li><a href="pageBorrowers?pageNo=2">2</a></li>
    <li><a href="pageBorrowers?pageNo=3">3</a></li>
    <li><a href="pageBorrowers?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

<table class="table" id="borrowersTable">
	<tr>
		<th>Borrower ID</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone</th>
		<th>Edit Borrower</th>
		<th>Delete Borrower</th>
	</tr>
	<%
		for (Borrower bo : borrowers) {
	%>
	<tr>
		<td>
			<%
				out.println(bo.getCardNo());
			%>
		</td>
		<td>
			<%
				out.println(bo.getName());
			%>
		</td>
		<td>
			<%
				out.println(bo.getAddress());
			%>
		</td>
		<td>
			<%
				out.println(bo.getPhone());
			%>
		</td>
		<td><button type="button" class="btn btn-md btn-success"
				data-toggle="modal" data-target="#myModal1"
				href="editBorrower.jsp?cardNo=<%=bo.getCardNo()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger"
				onclick="javascript:location.href='deleteBorrower?cardNo=<%=bo.getCardNo()%>';">Delete</button></td>
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
