<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">
	<ul>

		<li><a href="colors.jsp">Background color chooser</a></li>

		<li><a href="trigonometric?a=0&b=90"> Sine and cosine of
				every number in first quadrant</a></li>

		<li><a href="funnystory"> Funny story</a></li>
		
		<li><a href="report.jsp"> Report image</a></li>
		
		<li><a href="powers?a=1&b=100&n=3"> Create an excel file</a></li>
		
		<li><a href="appinfo.jsp"> Application info</a></li>
		
		<li><a href="glasanje"> Voting for your favorite bands</a></li>
	</ul>
	<form action="trigonometric" method="GET">
		Starting angle:<br> <input type="number" name="a" min="0"
			max="360" step="1" value="0"><br> Ending angle:<br>
		<input type="number" name="b" min="0" max="360" step="1" value="360"><br>
		<input type="submit" value="Create a table"><input
			type="reset" value="Reset">
	</form>


</body>
</html>