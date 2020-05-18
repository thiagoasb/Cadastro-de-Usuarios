package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario") //recebe o action de um formulario
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	// recupera para fazer operacoes como delete, editar...
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
		String user = request.getParameter("user");

		try {
			if (acao != null && acao.equalsIgnoreCase("delete")) {
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
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("download")) {
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				if(usuario != null) {
					
					String contentType = "";
					byte[] fileBytes = null;
					String tipo = request.getParameter("tipo");
					
					if(tipo.equalsIgnoreCase("imagem")) {
						contentType = usuario.getContentType();
						
						/*Converte a base64 da imagem do banco para byte[]*/
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						
						/*Converte a base64 da imagem do banco para byte[]*/
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);
					
					
					/*Coloca os bytes em um objeto de entrada para processar*/
					InputStream is = new ByteArrayInputStream(fileBytes);
					
					/*Início da resposta para o navegador*/
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
				} else {
					RequestDispatcher view = request
							.getRequestDispatcher("/cadastroUsuario.jsp");
					request.setAttribute("user", daoUsuario.listar());
					view.forward(request, response);
				}
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
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");

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
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if(request.getParameter("ativo") != null 
					&& request.getParameter("ativo").equalsIgnoreCase("on")){
				usuario.setAtivo(true);
			} else {
				usuario.setAtivo(false);
			}

			try {
				
				/*Inicio File upload de imagens e PDF*/
				
				if(ServletFileUpload.isMultipartContent(request)) {
					
					Part imagemFoto = request.getPart("foto");
					
					if(imagemFoto != null && imagemFoto.getInputStream().available() > 0){
						
						byte[] bytesImagem = converteStremParabyte(imagemFoto.getInputStream());
						
						String fotoBase64 = new Base64()
							.encodeBase64String(bytesImagem);
						
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());
						
						/*Inicio miniatura imagem*/
						
						/*Transforma em um bufferedImage*/
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						/*Pega o tipo da imagem*/
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB: bufferedImage.getType();
						
						/*Cria imagem em miniatura*/
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						
						/*Escrever imagem novamente*/
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
						
						usuario.setFotoBase64Miniatura(miniaturaBase64);
						/*Fim miniatura imagem*/
					
					} else {
						usuario.setAtualizarImage(false);
					}
					
					/*Processa PDF*/
					Part curriculoPdf = request.getPart("curriculo");
					if(curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
						String curriculoBase64 = new Base64()
						.encodeBase64String(converteStremParabyte(curriculoPdf.getInputStream()));
						
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
					} else {
						usuario.setAtualizarPdf(false);
					}
				}
				
				/*Fim File upload de imagens e PDF*/
				
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
	
	/*Converte a entrada de fluxo de dados da imagem para array de bytes(bytes[])*/
	private byte[] converteStremParabyte(InputStream imagem) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		
		while(reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		
		return baos.toByteArray();
	}

}
