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
	public void salvar(BeanCursoJsp usuario) {
		try{
			String sql = "insert into usuario(login,senha) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
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
		
		String sql = "select * from usuario";
		
		PreparedStatement statment = connection.prepareStatement(sql);
		ResultSet resultSet = statment.executeQuery();
		while(resultSet.next()){
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setLogin("login");
			beanCursoJsp.setSenha("senha");
			
			listar.add(beanCursoJsp);
		}
		
		return listar;
		
	}
}
