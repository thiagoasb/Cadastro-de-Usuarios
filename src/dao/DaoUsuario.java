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
	 * M�todo salvar
	 * Respons�vel por salvar um novo usu�rio
	 * @param usuario
	 */
	public void salvar(BeanCursoJsp usuario){
		try{
			String sql = "insert into usuario(login,senha,nome,email,fone,cep,rua,bairro,cidade,estado,ibge)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?)";
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
	
	public List<BeanCursoJsp> listar() throws Exception{
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		
		String sql = "select * from usuario order by id desc";
		
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

			listar.add(beanCursoJsp);
		}
		
		return listar;
		
	}
	
	public void delete (String id){
		try {
			String sql = "delete from usuario where id='"+id+"'";
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
		String sql = "select * from usuario where id='"+id+"'";
		
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

			
			return beanCursoJsp;
		}
		return null;
	}
	/**
	 * M�todo validarLogin
	 * Respons�vel por validar Login, de modo que apenas um usu�rio possua o mesmo login
	 * @param login Atributo login do usu�rio
	 * @return retorna true se n�o existir login igual e false caso contr�rio
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
		String sql = "update usuario set login = ?, senha = ?, nome = ?, email = ?, fone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ? where id = " + usuario.getId();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
