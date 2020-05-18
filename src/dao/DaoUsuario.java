package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	/**
	 * Método salvar
	 * Responsável por salvar um novo usuário
	 * @param usuario
	 */
	public void salvar(BeanCursoJsp usuario){
		try{
			String sql = "insert into usuario(login,senha,nome,email,fone,cep,rua,bairro,cidade,estado,ibge,fotobase64,contenttype,curriculobase64,contenttypecurriculo,fotobase64miniatura,ativo,sexo,perfil)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
			insert.setString(5, usuario.getFone());
			insert.setString(6, usuario.getCep());
			insert.setString(7, usuario.getRua());
			insert.setString(8, usuario.getBairro());
			insert.setString(9, usuario.getCidade());
			insert.setString(10, usuario.getEstado());
			insert.setString(11, usuario.getIbge());
			insert.setString(12, usuario.getFotoBase64());
			insert.setString(13, usuario.getContentType());
			insert.setString(14, usuario.getCurriculoBase64());
			insert.setString(15, usuario.getContentTypeCurriculo());
			insert.setString(16, usuario.getFotoBase64Miniatura());
			insert.setBoolean(17, usuario.isAtivo());
			insert.setString(18, usuario.getSexo());
			insert.setString(19, usuario.getPerfil());
			insert.execute();
			connection.commit();
		}catch(Exception e){
			try {
				connection.rollback(); 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public List<BeanCursoJsp> listar (String descricaoconsulta) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE login <> 'admin' AND nome LIKE '%"+descricaoconsulta+"%' ORDER BY id DESC";
		return consultarUsuarios(sql);
	}
	
	public List<BeanCursoJsp> listar() throws Exception{
	
		String sql = "select * from usuario where login <> 'admin' order by id desc";
		return consultarUsuarios(sql);
		
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql)
			throws SQLException {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		//verifica se o objeto resultSet ainda contem linhas
		while(resultSet.next()){
			
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			
			//beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotoBase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			listar.add(beanCursoJsp);
		}
		return listar;
	}
	
	public void delete (String id){
		try {
			String sql = "delete from usuario where id='"+id+"' and login <> 'admin'";
			PreparedStatement preparedStatement;

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public BeanCursoJsp consultar(String id) throws Exception{
		String sql = "select * from usuario where id='"+id+"' and login <> 'admin'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setIbge(resultSet.getString("ibge"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));
			
			return beanCursoJsp;
		}
		return null;
	}
	/**
	 * Método validarLogin
	 * Responsável por validar Login, de modo que apenas um usuário possua o mesmo login
	 * @param login Atributo login do usuário
	 * @return retorna true se não existir login igual e false caso contrário
	 * @throws Exception
	 */
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) as qtd from usuario where login='"+login+"'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
					
			return resultSet.getInt("qtd")<=0; //nao existe login igual, return true
		}
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception{
		String sql = "select count(1) as qtde from usuario where login = '"+ login +"' and id <> " +id;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
					
			return resultSet.getInt("qtde")<=0; //nao existe login igual, return true
		}
		return false;
	}
		
	public void atualizar(BeanCursoJsp usuario) {
		try{
			
			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE usuario SET login = ?, senha = ?, nome = ?, email = ?, fone = ? ");
			sql.append(", cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ?, ativo = ?, sexo = ?, perfil = ? ");
			
			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64 = ?, contenttype = ? ");
			}
			if (usuario.isAtualizarPdf()) {
				sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
			}
			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64miniatura = ? ");
			}
			
			sql.append(" WHERE id = " + usuario.getId());
		
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
			preparedStatement.setString(5, usuario.getFone());
			preparedStatement.setString(6, usuario.getCep());
			preparedStatement.setString(7, usuario.getRua());
			preparedStatement.setString(8, usuario.getBairro());
			preparedStatement.setString(9, usuario.getCidade());
			preparedStatement.setString(10, usuario.getEstado());
			preparedStatement.setString(11, usuario.getIbge());
			preparedStatement.setBoolean(12, usuario.isAtivo());
			preparedStatement.setString(13, usuario.getSexo());
			preparedStatement.setString(14, usuario.getPerfil());
			
			if (usuario.isAtualizarImage()) {
				preparedStatement.setString(15, usuario.getFotoBase64());
				preparedStatement.setString(16, usuario.getContentType());
			}

			if (usuario.isAtualizarPdf()) {
				
				if (usuario.isAtualizarPdf() && !usuario.isAtualizarImage()){
					preparedStatement.setString(15, usuario.getCurriculoBase64());
					preparedStatement.setString(16,
							usuario.getContentTypeCurriculo());
				}else {
					preparedStatement.setString(17, usuario.getCurriculoBase64());
					preparedStatement.setString(18,
							usuario.getContentTypeCurriculo());
				}

			}else {
				if (usuario.isAtualizarImage()) {
					preparedStatement.setString(17,
							usuario.getFotoBase64Miniatura());
				}
			}

			if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				preparedStatement.setString(19,
						usuario.getFotoBase64Miniatura());
			}
						
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
