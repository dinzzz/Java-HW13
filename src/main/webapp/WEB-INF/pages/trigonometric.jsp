<%@ page import="java.util.Date,java.util.Calendar"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">
	<h1>Trigonometry results</h1>
	<p>Results:</p>

	<table>
		<thead>
			<tr>
				<th>Broj</th>
				<th>Sinus</th>
				<th>Kosinus</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="zapis" items="${results}">
				<tr>
					<td>${zapis.number}</td>
					<td>${zapis.sine}</td>
					<td>${zapis.cosine}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="index.jsp"> Back to Home</a>
</body>
</html>