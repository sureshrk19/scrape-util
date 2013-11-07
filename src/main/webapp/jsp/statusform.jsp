<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>scrap information</title>
</head>
<body>
<%
	String scrapData = (String)session.getAttribute("scrapData");
	%>
	
	<table align="center" border="1" cellspacing="0" cellpadding="0">
		<caption>
			<strong>Scrap Response Information</strong>
		</caption>
			<tr>
				<td>
					<h4><%=scrapData %></h4>
				</td>
			</tr>
	</table>
</body>
</html>