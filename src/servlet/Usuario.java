package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario") //recebe o action de um formulario
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	// recupera para fazer operacoes como delete, editar...
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");

		try {
			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				// deleta o usuario e carrega todos os registros
				// indica para qual tela vai redirecionar
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				// seta no atributo de usuarios (usado na pagina jsp), toda a
				// lista de usuarios
				view.forward(request, response); // terminar a resposta ao
													// navegador
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// geralmente usado para fazer o primeiro cadastro
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);

			try {
				
				/*
				if(id == null || id.isEmpty() && !daoUsuario.validarLogin(login)){
					request.setAttribute("msg", "Usuário já cadastrado com este login!");
				}
				//valida login
				if (id == null || id.isEmpty()
						&& daoUsuario.validarLogin(login)) {
					daoUsuario.salvar(usuario);
					
				} else if(id != null && !id.isEmpty()){
					daoUsuario.atualizar(usuario);
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				*/
				String msg = null;
				
				/*VERIFICAR PREENCHIMENTO DOS CAMPSO*/
				if(login==null || login.isEmpty()){
					msg = "Por favor, digite seu login.";
					request.setAttribute("msg", msg);
				}
				else if(senha==null || senha.isEmpty()){
					msg = "Por favor, digite uma senha";
					request.setAttribute("msg", msg);
				}
				else if(nome==null || nome.isEmpty()){
					msg = "Por favor, informe seu nome";
					request.setAttribute("msg", msg);
				}
				else if(email==null || email.isEmpty()){
					msg = "Por favor, informe seu email";
					request.setAttribute("msg", msg);
				}
				
				
				/*VERIFICAR SE EXISTE LOGIN JÁ CADASTRADO*/
				if(!daoUsuario.validarLogin(login) ){
					msg = "Usuário já cadastrado com esse login!";
					request.setAttribute("msg", msg);
				}
				
				/*se o campo id estiver vazio, ou seja, for um novo usuário*/
				if(id == null || id.isEmpty() && msg == null){
						daoUsuario.salvar(usuario);
						System.out.println("ENTROU NO SALVAR!!!");
				} 
				/*se for uma atualização*/
				else if(id != null || !id.isEmpty() && msg == null){
					daoUsuario.atualizar(usuario);
					System.out.println("Entrou no atualizar!!!");	

				}
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();			

			}
		}
		
	}

}
