<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Car Configuration Proof</title>
</head>
<body>
	<h1>Here is what you selected:</h1>
	<form action="http://localhost:8080/project1unit5Client/GetModels"
		method="post">
		<table border="2" cellpadding="10" bordercolor="black">
			<tr>
				<th><%=request.getAttribute("Make/Model")%></th>
				<td>Base Price</td>
				<td> <%=request.getAttribute("basePrice")%></td>
			</tr>
			<tr>
				<%
					int optionSetAmount = (Integer) request
							.getAttribute("optionSetAmount");
					for (int i = 0; i < optionSetAmount; i++) {
						String optionSetName = (String) request.getAttribute(Integer
								.toString(-i - 1));
						String optionName = (String) request.getAttribute(Integer
								.toString(2 * i));
						float optionPrice = (Float) request.getAttribute(Integer
								.toString(2 * i + 1));
				%>
				<th><%=optionSetName%></th>
				<td><%=optionName%></td>
				<td><%=optionPrice%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<th>Total Price</th>
				<td></td>
				<td><%=request.getAttribute("totalPrice")%></td>
			</tr>
		</table>
		<input type="submit" value="Go Back to Mainpage">
	</form>

</body>
</html>