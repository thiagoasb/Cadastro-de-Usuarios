<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
</head>
<body>

	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 style="color: #ff0000">${msg}</h3>
	</center>
	
	<form action="salvarTelefones" method="post" id="formUser">
		<ul class="form-style-1">
			<li>

				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly="readonly" id="user" name="user"
							value="${userEscolhido.id}" class="field-long"></td>
						
						<td><input type="text" readonly="readonly" id="nome" name="nome"
							value="${userEscolhido.nome}" class="field-long"></td>

					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>
						<td>
							<select id="tipo" name="tipo" style="width: 173px;">
								<option>Fixo</option>
								<option>Celular</option>
								<option>Setor</option>
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="salvar" style="width: 173px;"
							onclick="return validarCampos()? true : false;"> 
						</td>
						<td><input type="submit" value="voltar" style="width: 173px;"
							onclick="document.getElementById('formUser').action='salvarTelefones?acao=voltar'"> 
						</td>
					</tr>
				</table> <li>
		
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados:</caption>
			<tr>
				<th>Id</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Excluir</th>
				
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 150px"><c:out value="${fone.id}" /></td>
					<td style="width: 150px"><c:out value="${fone.numero}" /></td>
					<td><c:out value="${fone.tipo}" /></td>
					
 
					<td>
						<a href="salvarTelefones?user=${fone.usuario}&acao=deleteFone&foneId=${fone.id}" onclick="return confirm('Confirmar a exclusão?');"><img
							src="resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('Informe o Número');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				alert('Informe o Tipo');
				return false;
			} 
			return true;
		}

	</script>
	</body>
</html>