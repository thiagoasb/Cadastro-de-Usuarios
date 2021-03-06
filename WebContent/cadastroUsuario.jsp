<%@page import="beans.BeanCursoJsp"%>
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
		<h1>Cadastro de Usu�rio</h1>
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
							placeholder="Informe um CEP v�lido" maxlength="9"></td>
					</tr>

					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Escolha um login v�lido" maxlength="10"></td>

						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}" placeholder="Informe a rua do usu�rio" maxlength="50"></td>
					</tr>

					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Escolha uma senha v�lida" maxlength="10"></td>

						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}" placeholder="Informe o bairro" maxlength="50"></td>
					</tr>

					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder="Informe o nome do usu�rio" maxlength="50"></td>

						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}" placeholder="Informe a cidade do usu�rio" maxlength="50"></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" id="email" name="email"
							value="${user.email}" placeholder="Informe o email do usu�rio" maxlength="50"></td>

						<td>UF:</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}" placeholder="Informe o UF do usu�rio" maxlength="30"></td>
					</tr>
					
					<tr>
						<td>Sexo:</td>
						<td>
							<input type="radio" name="sexo" 
							<%
								if(request.getAttribute("user") != null) {
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.getSexo().equalsIgnoreCase("masculino")) {
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									}
								}
							%>
							value="masculino">Masculino</input>
							<input type="radio" name="sexo" 
								<%
									if(request.getAttribute("user") != null) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										if(user.getSexo().equalsIgnoreCase("feminino")) {
											out.print(" ");
											out.print("checked=\"checked\"");
											out.print(" ");
										}
									}
								%>
							value="feminino">Feminino</input>
						</td>
					
						<td>Ativo:</td>
						<td><input type="checkbox" id="ativo" name="ativo"
							<%
								if(request.getAttribute("user") != null) {
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if(user.isAtivo()) {
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									} 
								}
							%>
						></td>
					</tr>
					
					<tr>
						<td>Foto:</td>
						<td>
							<input type="file" name="foto">
						</td>
						
						<td>Perfil:</td>
						<td>
							<select id="perfil" name="perfil" style="width: 185px;">
								<option value="nao_informado">[--SELECIONE--]</option>
								
								<option value="administrador"
									<%
										if(request.getAttribute("user") != null) {
											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if(user.getPerfil().equalsIgnoreCase("administrador")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}
									%>
								
								>Administrador</option>
								
								<option value="secretario"
									<%
										if(request.getAttribute("user") != null) {
											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if(user.getPerfil().equalsIgnoreCase("secretario")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}
									%>
									
								>Secret�rio(a)</option>
								
								<option value="gerente"
									<%
										if(request.getAttribute("user") != null) {
											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if(user.getPerfil().equalsIgnoreCase("gerente")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}
									%>
								>Gerente</option>
								
								<option value="funcionario"
									<%
										if(request.getAttribute("user") != null) {
											BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if(user.getPerfil().equalsIgnoreCase("funcionario")) {
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}
									%>
								>Funcion�rio</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<td>Curr�culo:</td>
						<td>
							<input type="file" name="curriculo" value="curriculo">
						</td>
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
	
	<form method="post" action="servletPesquisa">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Descri��o:</td>
						<td><input type="text" id="descricaoconsulta" name="descricaoconsulta"></td>
						<td><input type="submit" value="Pesquisar"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>
	
	<div class="container">
		<table class="responsive-table">
			<caption>Usu�rios cadastrados:</caption>
			<tr>
				<th>Id</th>
				<th>Foto</th>
				<th>Curr�culo</th>
				<th>Nome</th>
				<th>Telefones</th>
				<th>Excluir</th>
				<th>Editar</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}" /></td>
					
					<c:if test="${!user.fotoBase64Miniatura.isEmpty() && user.fotoBase64Miniatura != null}">
						<td><a href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img src="<c:out value='${user.fotoBase64Miniatura}' />" alt="Imagem User" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.fotoBase64Miniatura.isEmpty() || user.fotoBase64Miniatura == null}">
						<td><img src="resources/img/userPadrao.png" alt="Imagem User" width="32px" height="32px" onclick="alert('N�o possui imagem')"></td>
					</c:if>
					
					<c:if test="${!user.curriculoBase64.isEmpty() && user.curriculoBase64 != null}">
						<td><a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img alt="Curriculo" src="resources/img/pdfdown.png" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.curriculoBase64.isEmpty() || user.curriculoBase64 ==null}">
						<td><img alt="Curriculo" src="resources/img/pdfdown.png" width="32px" height="32px" onclick="alert('N�o possui curr�culo')"></td>
					</c:if>
					
					<td><c:out value="${user.nome}" /></td>
					<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img
							src="resources/img/fone.png" alt="Telefones" title="Telefones"
							width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclus�o?');"><img
							src="resources/img/excluir.png" alt="Excluir" title="Excluir"
							width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resources/img/editar.png" alt="Editar" title="Editar"
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
	</body>
</html>