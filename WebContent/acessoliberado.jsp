<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
	<jsp:setProperty property="*" name="calcula" />
	<center style="padding-top:10%;">
		<h1>Bem Vindo ao Sistema!</h1>
	
		<%--salvarUsuario � a url da servlet que sera interceptada --%>
		<a href="salvarUsuario?acao=listartodos"><img
			src="resources/img/user.png" alt="Gerenciar usu�rios"
			title="Gerenciar usu�rios" width="60px" height="60px"></a>
		<a href="produtoServlet?acao=listartodos"><img
			src="resources/img/box.png" alt="Gerenciar produtos"
			title="Gerenciar produtos" width="60px" height="60px"></a>
	</center>
</body>
</html>