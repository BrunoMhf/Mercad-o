package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class estoqueDAO {
	
	private Connection connection;
	
	
	public estoqueDAO() {
		connection  = new ConnectionFactory().getConnection();
	}

	public void cadastrar(estoque produto) {
		
		String sql = "insert into estoque(nome, data_validade, marca, preco) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
		
			statement.setString(1, produto.getNome());
			statement.setDate(2, produto.getData_validade());
			statement.setString(3, produto.getMarca());
			statement.setBigDecimal(4, produto.getPreco());
			
			statement.execute();
			statement.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void atualizar(estoque produto) {
		

		String sql = "update estoque set nome=?, data_validade=?, marca=?, preco=? where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
		
			statement.setString(1, produto.getNome());
			statement.setDate(2, produto.getData_validade());
			statement.setString(3, produto.getMarca());
			statement.setBigDecimal(4, produto.getPreco());
			statement.setInt(5, produto.getId());
			
			statement.execute();
			statement.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void deletar(Integer idProduto) {
	

		String sql = "delete from estoque where id=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
		
			statement.setInt(1, idProduto);
			
			statement.execute();
			statement.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<estoque> consultar(String NomeConsultaProduto) {
		
		List<estoque> produtos = new ArrayList<>();
		
		String sql = "select * from produto where nome like '%" +NomeConsultaProduto + "%'";		
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
		
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				estoque produto = new estoque();
						
				produto.setId(resultSet.getInt("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setData_validade(resultSet.getDate("data_validade"));
				produto.setMarca(resultSet.getString("marca"));
				produto.setPreco(resultSet.getBigDecimal("preco"));
				
				produtos.add(produto);
				
			}
			
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return produtos;
	}

}





























