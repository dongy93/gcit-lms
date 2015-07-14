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
	<script type="text/javascript" src="path_to/jquery.js"></script>
	<script type="text/javascript" src="path_to/jquery.simplePagination.js"></script>
</script>
${result }
<form action="searchBorrowers" method="post">
	<input type="text" class="col-md-8" id="searchString" name="searchString"
		placeholder="Enter Borrower name to search"><input type="button"
		value="Search!" onclick="javascript:searchBorrowers();">
</form>
        <div id="first-container">

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
   <div class="my-navigation">
        <div class="simple-pagination-first"></div>
        <div class="simple-pagination-previous"></div>
        <div class="simple-pagination-page-numbers"></div>
        <div class="simple-pagination-next"></div>
        <div class="simple-pagination-last"></div>
    </div>
    <div class="simple-pagination-page-x-of-x"></div>
    <div class="simple-pagination-showing-x-of-x"></div>
    <div>
        Display <select class="simple-pagination-items-per-page"></select> items per page.
    </div>
    <div>
        Go directly to page <select class="simple-pagination-select-specific-page"></select>.
    </div>
</div>
</div>
        <script>
            (function($) {

                $('#first-container').simplePagination({
                    items_per_page:5,
                    number_of_visible_page_numbers: 2
                });
            })(jQuery);
        </script>

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
