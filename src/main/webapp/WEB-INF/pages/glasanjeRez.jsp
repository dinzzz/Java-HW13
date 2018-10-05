<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<style type="text/css">
table.rez td {
	text-align: center;
}
</style>
<meta charset="UTF-8">
</head>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">

	<h1>Voting results</h1>
	<p>Here by stand upon the - results to the voting of your favorite
		bands</p>
	<table border="1" cellspacing="0" class="rez">
		<thead>
			<tr>
				<th>Band</th>
				<th>Number of votes</th>
			</tr>
		</thead>


		<tbody>
			<c:forEach var="u" items="${viewModels}">
				<tr>
					<td>${u.bandName}</td>
					<td>${u.numberOfVotes}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h2>Result chart</h2>
	<img alt="Pie-chart" src="glasanje-grafika" width="400" height="400" />

	<h2>Results in excel format</h2>
	<p>
		Results are available <a href="glasanje-xls">here</a>
	</p>


	<h1>Band songs</h1>
	<p>Here are the epic performances from these awesome victorious bands</p>
	<ol>
		<c:forEach var="u" items="${maxViewModels}">
			<li><a href="${u.bandSong}" target="_blank">${u.bandName}</a></li>
		</c:forEach>
	</ol>
	
	<a href="index.jsp"> Back to Home</a>


</body>
</html>