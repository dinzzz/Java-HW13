<%@ page import = "java.util.Date, java.text.SimpleDateFormat, java.util.Locale" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body
	style="background-color: <%=session.getAttribute("pickedBgColor")%>">
	<h1>Application information</h1>
	<p>Time elapsed since the start of the application:</p>
	<%
	long nowTime = System.currentTimeMillis();
	
	long timeElapsed = nowTime - (long)request.getServletContext().getAttribute("startTime");
	
	long seconds = timeElapsed / 1000;
	long minutes = seconds / 60;
	long hours = minutes / 60;
	long days = hours / 24;
	out.println(
		    days + " days, " + hours%24 + " hours, " + minutes%60 + " minutes, " + seconds%60 + " seconds.\n" 
		  );
	%>
	
	<a href="index.jsp"> Back to Home</a>
	
</body>
</html>