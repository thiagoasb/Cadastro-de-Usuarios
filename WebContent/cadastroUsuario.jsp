<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de usu�rio</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>

	<a href="acessoliberado.jsp">In�cio</a>
	<a href="index.jsp">Sair</a>

	<center>
		<h1>Cadastro de usu�rio</h1>
		<h3 style="color: #ff0000">${msg}</h3>
	</center>
	<form action="salvarUsuario" method="post" id="formUser" enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>

				<table>
					<tr>
						<td>C�digo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>

						<td>CEP:</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultarCep();" value="${user.cep}"
							placeholder="Informe um CEP v�lido"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Escolha um login v�lido"></td>

						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}" placeholder="Informe a rua do usu�rio"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Escolha uma senha v�lida"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}" placeholder="Informe o bairro"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Informe o nome do usu�rio"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}" placeholder="Informe a cidade do usu�rio"></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" id="email" name="email"
							value="${user.email}" placeholder="Informe o email do usu�rio"></td>

						<td>UF:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}" placeholder="Informe o UF do usu�rio"></td>
					</tr>

					<tr>
						<td>Fone:</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}" placeholder="Informe um telefone de emerg�ncia"></td>

						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					</tr>
					
					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto"><input type="text" style="display: none;" name="fotoTemp" readonly="readonly" value="${user.fotoBase64}" ></td>
					</tr>
					
					<tr>
						<td>Curr�culo:</td>
						<td><input type="file" name="curriculo" value="curriculo"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="salvar"
							onclick="return validarCampos()? true : false;"> 
							<input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'">
						</td>
					</tr>
				</table>
			<li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usu�rios cadastrados:</caption>
			<tr>
				<th>Id</th>
				<th>Login</th>
				<th>Foto</th>
				<th>Curr�culo</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
				<th>CEP</th>
				<th>Excluir</th>
				<th>Editar</th>
				<th>Fones</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}" /></td>
					<td style="width: 150px"><c:out value="${user.login}" /></td>
					<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src="<c:out value='${user.temporarioFotoUser}' />" alt="Imagem User" width="32px" height="32px"></a></td>
					<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">Curr�culo</a></td>
					<td><c:out value="${user.nome}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.fone}" /></td>
					<td><c:out value="${user.cep}" /></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resources/img/editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
					<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img
							src="resources/img/fone.png" alt="Telefones" title="Telefones"
							width="20px" height="20px"></a></td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe a Senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("email").value == '') {
				alert('Informe o email');
				return false;
			} else if (document.getElementById("fone").value == '') {
				alert('Informe o telefone');
				return false;
			}
			return true;
		}

		function consultarCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							//CEP pesquisado n�o foi encontrado.
							$("#cep").val("");
							$("#rua").val("");
							$("#bairro").val("");
							$("#cidade").val("");
							$("#estado").val("");
							$("#ibge").val("");
							alert("CEP n�o encontrado.");
						}
					});
		}
	</script>
	</bo
			dy>
</html>