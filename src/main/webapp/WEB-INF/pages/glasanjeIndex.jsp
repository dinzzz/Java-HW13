<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
</head>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">

	<h1>Vote for your favorite band:</h1>
	<p>Click on the link of your favorite band to submit your vote!</p>
	<ol>
		<c:forEach var="u" items="${bands}">
			<li><a href="glasanje-glasaj?id=${u.id}">${u.bandName}</a></li>
		</c:forEach>
	</ol>

	<a href="index.jsp"> Back to Home</a>
</body>
</html>