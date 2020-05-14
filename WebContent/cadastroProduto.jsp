<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produtos</title>
</head>
<body>
	
	<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>
	
	<center>
		<h1>Cadastro de Produtos</h1>
	</center>
	
	<form action="produtoServlet" method="post" id="formProd">
		<table>
			<tr>
				<td>Código</td>
				<td><input type="text" readonly="readonly" name="codigo"
					id="codigo" value="${prod.id}"></td>
			</tr>
			
			<tr>
				<td>Nome</td>
				<td><input type="text" name="nome" id="nome" 
					value="${prod.nome}"></td>
			</tr>
			
			<tr>
				<td>Quantidade</td>
				<td><input type="text" name="qtde" id="qtde" 
					value="${prod.quantidade}"></td>
			</tr>
			
			<tr>
				<td>Valor(R$)</td>
				<td><input type="text" name="valor" id="valor" 
					value="${prod.valor}"></td>
			</tr>
			
			<tr>
				<td></td>
				<td>
					<input type="submit" value="salvar">
					<input type="submit" value="cancelar" onclick="document.getElementById('formProd').action='produtoServlet?acao=reset'">
				</td>
			</tr>
		</table>
	</form>
	
	<table>
		<caption>Produtos cadastrados:</caption>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Qtde</th>
			<th>Valor(R$)</th>
			<th>Excluir</th>
			<th>Editar</th>
		</tr>
		
		<c:forEach items="${produtos}" var="prod">
			<tr>
				<td><c:out value="${prod.id}" /></td>
				<td><c:out value="${prod.nome}" /></td>
				<td><c:out value="${prod.quantidade}" /></td>
				<td><c:out value="${prod.valor}" /></td>
				<td>
					<a href="produtoServlet?acao=delete&prod=${prod.id}"><img src="resource/img/excluir.png" alt="Excluir" title="Excluir" width="20px" height="20px"></a>
				</td>
				<td>
					<a href="produtoServlet?acao=editar&prod=${prod.id}"><img src="resource/img/editar.png" alt="Editar" title="Editar" width="20px" height="20px"></a>
				</td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>