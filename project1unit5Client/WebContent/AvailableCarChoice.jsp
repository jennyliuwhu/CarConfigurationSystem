<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Available Car Choice</title>
</head>
<body>
	<h1>Available Car Choice</h1>
	Choose a car:
	<form action="http://localhost:8080/project1unit5Client/GetChoices"
		method="post">
		<select NAME="selectCar">
			<%
				int flag = 1;
				String attribute;
				while (request.getAttribute(Integer.toString(flag)) != null) {
					attribute = (String) request.getAttribute(Integer
							.toString(flag));
			%>
			<option value=<%=attribute%>><%=attribute%></option>
			<%
				flag++;
				}
			%>
		</select> <input type="submit" value="Submit My Choice">
	</form>
</body>
</html>