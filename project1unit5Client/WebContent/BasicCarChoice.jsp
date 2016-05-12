<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Car Choice</title>
</head>
<body>
	<h1>Basic Car Choice</h1>
	<form action="http://localhost:8080/project1unit5Client/GetPrices"
		method="post">
		<table border="2" cellpadding="10" bordercolor="black">
			<tr>
				<th>Make/Model</th>
				<td><%=request.getAttribute("Make/Model")%></td>
			</tr>
			<tr>
				<%
					int optionSetAmount = (Integer) request
							.getAttribute("optionSetAmount");
					for (int i = 0; i < optionSetAmount; i++) {
						String optionSetName = (String) request.getAttribute(Integer
								.toString(-i - 1));
						int j = 0;
				%>
				<th><%=optionSetName%></th>
				<td><select NAME=<%=Integer.toString(-i - 1)%>>
						<%
							while (request.getAttribute(Integer.toString(i * 100 + j)) != null) {
						%>
						<option value=<%=Integer.toString(i * 100 + j)%>><%=request.getAttribute(Integer
							.toString(i * 100 + j))%></option>
						<%
							j++;
								}
						%>
				</select></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" name="car" value=<%=request.getAttribute("car")%>>
		<input type="submit" value="Submit My Choice">
	</form>

</body>
</html>