package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.ProdutoBean;
import beans.Telefones;
import connection.SingleConnection;

public class DaoTelefones {
	
	private Connection connection;
	
	public DaoTelefones() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Telefones telefone) {
		try {
			String sql = "INSERT INTO telefone(numero,tipo,usuario) VALUES (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setDouble(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Telefones> listar(Long user) throws Exception {
		List<Telefones> listar = new ArrayList<Telefones>();
		
		String sql = "SELECT * FROM telefone WHERE usuario = " + user;
		
		PreparedStatement statment = connection.prepareStatement(sql);
		ResultSet resultSet = statment.executeQuery();
		
		while(resultSet.next()) {
			Telefones telefone = new Telefones();
			
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setUsuario(resultSet.getLong("usuario"));
			
			listar.add(telefone);
		}
		
		return listar;
	}
	
	public void delete(String id){
		try {
			String sql = "DELETE FROM telefone WHERE id = '"+id+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ProdutoBean consultar(String id) throws Exception{
		String sql = "SELECT * FROM produto WHERE id='"+id+"'";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setId(resultSet.getLong("id"));
			produtoBean.setNome(resultSet.getString("nome"));
			produtoBean.setQuantidade(resultSet.getLong("quantidade"));
			produtoBean.setValor(resultSet.getDouble("valor"));
			
			return produtoBean;
		}
		return null;
	}
	

}
