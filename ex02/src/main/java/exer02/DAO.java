package exer02;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "biblioteca"; 
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao != null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	// Inserir livro
	public boolean inserirLivro(Livro livro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO livros (id, titulo, autor, status) "
					       + "VALUES ("+livro.getId()+ ", '" + livro.getTitulo() + "', '"  
					       + livro.getAutor() + "', '" + livro.getStatus() + "');");
			st.close();
			status = true;
		} catch (SQLException e) {  
			throw new RuntimeException(e);
		}
		return status;
	}
	
	// Atualizar livro
	public boolean atualizarLivro(Livro livro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE livros SET titulo = '" + livro.getTitulo() + 
			             "', autor = '" + livro.getAutor() + 
			             "', status = '" + livro.getStatus() + "'" +
			             " WHERE id = " + livro.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException e) {  
			throw new RuntimeException(e);
		}
		return status;
	}
	
	// Excluir livro
	public boolean excluirLivro(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM livros WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException e) {  
			throw new RuntimeException(e);
		}
		return status;
	}
	
	// Buscar todos os livros
	public Livro[] getLivros() {
		Livro[] livros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM livros");		
	         if(rs.next()){
	             rs.last();
	             livros = new Livro[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                livros[i] = new Livro(rs.getInt("id"), rs.getString("titulo"), 
	                		                  rs.getString("autor"), rs.getString("status").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return livros;
	}

	// Buscar apenas livros disponíveis (status = 'D')
	public Livro[] getLivrosDisponiveis() {
		Livro[] livros = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM livros WHERE status = 'D'");		
	         if(rs.next()){
	             rs.last();
	             livros = new Livro[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                livros[i] = new Livro(rs.getInt("id"), rs.getString("titulo"), 
                         		                  rs.getString("autor"), rs.getString("status").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return livros;
	}
}
