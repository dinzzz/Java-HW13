<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">
	<h1>OS usage</h1>
	<p>Here are the results of OS usage in survey that we completed.</p>
	<img src="reportImage">
	<a href="index.jsp"> Back to Home</a>



</body>
</html>