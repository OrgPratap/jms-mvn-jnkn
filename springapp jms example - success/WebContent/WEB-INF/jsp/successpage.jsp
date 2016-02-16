<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head><title><fmt:message key="successpage.title"/></title></head>
<body>
	<h1><fmt:message key="heading"/></h1>
	<p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
	<h3>Received Products :</h3>
	<c:forEach items="${model.products}" var="prod">
		<c:out value="${prod.description}"/> <i>$<c:out value="${prod.price}"/></i><br><br>
	</c:forEach>
	<br>
	<a href="<c:url value="hello.htm"/>">Increase Prices</a>
	<br>
</body>
</html>