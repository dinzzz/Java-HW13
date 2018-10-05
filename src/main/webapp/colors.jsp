<%@ page session = "true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body style="background-color: <%= session.getAttribute("pickedBgColor") %>">

<a href="setcolor?color=white"> WHITE</a>

<a href="setcolor?color=red"> RED</a>

<a href="setcolor?color=cyan" > CYAN</a>

<a href="setcolor?color=green"> GREEN</a>

<a href="index.jsp"> Back to Home</a>

</body>
</html>