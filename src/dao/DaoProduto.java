package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ProdutoBean;
import connection.SingleConnection;

public class DaoProduto {
	
	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(ProdutoBean produto) {
		try {
			String sql = "INSERT INTO produto(nome,quantidade,valor) VALUES (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setLong(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
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
	
	public List<ProdutoBean> listar() throws Exception {
		List<ProdutoBean> listar = new ArrayList<ProdutoBean>();
		
		String sql = "SELECT * FROM produto ORDER BY id desc";
		
		PreparedStatement statment = connection.prepareStatement(sql);
		ResultSet resultSet = statment.executeQuery();
		
		while(resultSet.next()) {
			ProdutoBean produtoBean = new ProdutoBean();
			produtoBean.setId(resultSet.getLong("id"));
			produtoBean.setNome(resultSet.getString("nome"));
			produtoBean.setQuantidade(resultSet.getLong("quantidade"));
			produtoBean.setValor(resultSet.getDouble("valor"));
			
			listar.add(produtoBean);
		}
		
		return listar;
	}
	
	public void delete(String id){
		try {
			String sql = "DELETE FROM produto WHERE id = '"+id+"'";
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
	
	public void atualizar(ProdutoBean produto) {
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? WHERE id = " + produto.getId();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setLong(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			
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
	
	public boolean validarNome(String nome) throws Exception {
		String sql = "SELECT count(1) as qtd FROM produto WHERE nome = '"+nome+"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			return resultSet.getInt("qtd") <= 0;
		}
		
		return false;
	}

}
