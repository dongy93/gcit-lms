<%@page import="com.gcit.lms.service.AdministrativeService"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.domain.Publisher"%>
<%AdministrativeService adminService = new AdministrativeService();
 List<Publisher> publishers = adminService.readPublishers();
%>
<%@include file="include.html"%>
${result }

<table class="table">

	<tr>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th>Edit Publisher</th>
		<th>Delete Publisher</th>
	</tr>
	<%for(Publisher p: publishers){ %>
	<tr>
		<td><%out.println(p.getPublisherId()); %></td>
		<td><%out.println(p.getPublisherName()); %></td>
		<td><%out.println(p.getPublisherAddress()); %></td>
		<td><%out.println(p.getPublisherPhone()); %></td>
		<td><button type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#myModal2" href="editPublisher.jsp?publisherId=<%=p.getPublisherId()%>">Edit</button></td>
		<td><button type="button" class="btn btn-md btn-danger" onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>';">Delete</button></td>
	</tr>
	<%} %>
</table>

<nav>
  <ul class="pagination">
    <li><a href="pagePublishers?pageNo=1">1</a></li>
    <li><a href="pagePublishers?pageNo=2">2</a></li>
    <li><a href="pagePublishers?pageNo=3">3</a></li>
    <li><a href="pagePublishers?pageNo=4">4</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>

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