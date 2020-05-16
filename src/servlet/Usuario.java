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
			String fone = request.getParameter("fone");
			
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String ibge = request.getParameter("ibge");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setFone(fone);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setIbge(ibge);
			usuario.setEstado(estado);

			try {
				
				String msg = null;
				boolean podeInserir = true;
				
				/*VERIFICAR PREENCHIMENTO DOS CAMPSO*/
				if(login==null || login.isEmpty()){
					msg = "Por favor, digite seu login.";
					podeInserir = false;
				}
				else if(senha==null || senha.isEmpty()){
					msg = "Por favor, digite uma senha";
					podeInserir = false;
				}
				else if(nome==null || nome.isEmpty()){
					msg = "Por favor, informe seu nome";
					podeInserir = false;
				}
				else if(email==null || email.isEmpty()){
					msg = "Por favor, informe seu email";
					podeInserir = false;
				}
				else if(fone==null || fone.isEmpty()){
					msg = "Por favor, informe o telefone";
					podeInserir = false;
				}
				else if(id==null || id.isEmpty() && !daoUsuario.validarLogin(login)){
					msg = "Já existe usuário cadastrado com esse login!";
					podeInserir = false;
				}
				
				if(msg != null){
					request.setAttribute("msg", msg);
				}
				else if(id==null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir){
					daoUsuario.salvar(usuario);
					request.setAttribute("msg", "Usuário salvo com sucesso!");
				}
				else if(id != null || !id.isEmpty() && podeInserir){
					if(!daoUsuario.validarLoginUpdate(login, id)){
						request.setAttribute("msg", "Já existe usuário cadastrado com esse login!");
					} else {
						daoUsuario.atualizar(usuario);
						request.setAttribute("msg", "Usuário atualizado com sucesso!");
					}
				}
				if(!podeInserir){
					request.setAttribute("user", usuario);
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
