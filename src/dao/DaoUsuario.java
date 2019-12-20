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
	public void salvar(BeanCursoJsp usuario) {
		try{
			String sql = "insert into usuario(login,senha,nome,email) values (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getEmail());
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

			
			return beanCursoJsp;
		}
		return null;
	}
	
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
		String sql = "select count(1) as qtde from usuario where login='"+login+"' + and id <> '"+id+"'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
					
			return resultSet.getInt("qtde")<=0; //nao existe login igual, return true
		}
		return false;
	}
	
	public void atualizar(BeanCursoJsp usuario) {
		String sql = "update usuario set login = ?, senha = ?, nome = ?, email = ? where id = " + usuario.getId();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getEmail());
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
