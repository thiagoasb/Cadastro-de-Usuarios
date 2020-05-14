<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

</head>
<body>
	
	<a href="acessoliberado.jsp">Início</a>
	<a href="index.jsp">Sair</a>

	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color:#ff0000">${msg}</h3>
	</center>	
	<form action="salvarUsuario" method="post" id="formUser">
		<ul class="form-style-1"> <li>

			<table>
				<tr>
					<td>Código:</td>
					<td><input type="text" readonly="readonly" id="id" name="id"
						value="${user.id}" class="field-long"></td>
				</tr>

				<tr>
					<td>Login:</td>
					<td><input type="text" id="login" name="login"
						value="${user.login}"></td>
				</tr>

				<tr>
					<td>Senha:</td>
					<td><input type="password" id="senha" name="senha"
						value="${user.senha}"></td>
				</tr>
				
				<tr>
					<td>Nome:</td>
					<td><input type="text" id="nome" name="nome"
						value="${user.nome}"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" id="email" name="email"
						value="${user.email}"></td>
				</tr>
				
				<tr>
					<td>Fone:</td>
					<td><input type="text" id="fone" name="fone"
						value="${user.fone}"></td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="salvar" onclick="return validarCampos()? true : false;"> 
						<input type="submit" value="Cancelar" onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'">
					</td>
				</tr>
			</table>
			<li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados:</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}" /></td>
					<td style="width: 150px"><c:out value="${user.login}" /></td>
					<td><c:out value="${user.nome}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.fone}" /></td>

					<td>
						<a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png" alt="Excluir" title="Excluir" width="20px" height="20px"></a>
					</td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.png" alt="Editar" title="Editar" width="20px" height="20px"></a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript">
		function validarCampos() {
			if(document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			}
			else if(document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			} else if(document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if(document.getElementById("email").value == '') {
				alert('Informe o email');
				return false;
			} else if(document.getElementById("fone").value == '') {
				alert('Informe o telefone');
				return false;
			} 
			return true;
		}
	</script>

</body>
</html>