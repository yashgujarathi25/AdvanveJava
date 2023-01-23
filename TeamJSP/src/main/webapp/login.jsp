<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
table td{
 padding:10px
}
</style>
<center>
<hr>
<h1>Login Form</h1>
<hr>
	<form action="validateServlet" method="post">
		<table>
			<tr>
				<td>Email : </td><td><input type="text" name="txtemail"></td>
			</tr>
			<tr>
				<td>Password : </td><td><input type="password" name="txtpass"></td>
			</tr><tr>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
</center>



</body>
</html>