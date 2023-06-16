package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroStorage {
        
		public static void inserir(Cadastro cadastro) {
		final String query = "INSERT INTO cadastro (nome, idade, email, endereco, cep, telefone, usuario, senha, curso, observacao, ativo)"
		+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
 
		
		 Connection conn = null;
		 PreparedStatement prepStmt = null;
		 ResultSet rs = null;
	
		

		try {
			conn = ConexaoFactory.getConexao();

			prepStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prepStmt.setString(1, cadastro.getNome());
			prepStmt.setString(2, cadastro.getIdade());
			prepStmt.setString(3, cadastro.getEmail());
			prepStmt.setString(4, cadastro.getEndereco());
			prepStmt.setString(5, cadastro.getCep());
			prepStmt.setString(6, cadastro.getTelefone());
			prepStmt.setString(7, cadastro.getUsuario());
			prepStmt.setString(8, cadastro.getSenha());
			prepStmt.setString(9, cadastro.getCurso());
			prepStmt.setString(10, cadastro.getObservacao());
			prepStmt.setString(11, cadastro.getAtivo());
			prepStmt.execute();

			rs = prepStmt.getGeneratedKeys();
			if (rs.next()) {
				cadastro.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepStmt != null)
					prepStmt.close();

				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // fim do método inserir

	public static void atualizar(Cadastro cadastro) {
		final String query = "UPDATE cadastro SET nome = ?, idade = ?, email = ?, endereco = ?, cep = ?, telefone = ?, "
		+"usuario = ?, senha = ?, curso = ?, observacao = ?, ativo = ?, WHERE id = ?";

		Connection conn = null;
		PreparedStatement prepStmt = null;

		try {
			conn = ConexaoFactory.getConexao();

			prepStmt = conn.prepareStatement(query);
			prepStmt.setString(1, cadastro.getNome());
			prepStmt.setString(2, cadastro.getIdade());
			prepStmt.setString(3, cadastro.getEmail());
			prepStmt.setString(4, cadastro.getEndereco());
			prepStmt.setString(5, cadastro.getCep());
			prepStmt.setString(6, cadastro.getTelefone());
			prepStmt.setString(7, cadastro.getUsuario());
			prepStmt.setString(8, cadastro.getSenha());
			prepStmt.setString(9, cadastro.getCurso());
			prepStmt.setString(10, cadastro.getObservacao());
			prepStmt.setString(11, cadastro.getAtivo());
			prepStmt.setInt(12, cadastro.getId());
			
			prepStmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepStmt != null)
					prepStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // fim do método atualizar

	public static void remover(Cadastro cadastro) {
		final String query = "DELETE FROM cadastro WHERE id = ?";

		Connection conn = null;
		PreparedStatement prepStmt = null;

		try {
			conn = ConexaoFactory.getConexao();

			prepStmt = conn.prepareStatement(query);
			prepStmt.setInt(1, cadastro.getId());
			
			prepStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepStmt != null)
					prepStmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} // fim do método remover

	public static List<Cadastro> listar() {
		List<Cadastro> cadastro = new ArrayList<>();

		final String query = "SELECT * FROM cadastro ORDER BY Id";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConexaoFactory.getConexao();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				Cadastro cadastros = new Cadastro();
				cadastros.setId(rs.getInt("id"));
				cadastros.setNome(rs.getString("nome"));
				cadastros.setIdade(rs.getString("idade"));
				cadastros.setEmail(rs.getString("email"));
				cadastros.setEndereco(rs.getString("endereco"));
				cadastros.setCep(rs.getString("cep"));
				cadastros.setTelefone(rs.getString("telefone"));
				cadastros.setUsuario(rs.getString("usuario"));
				cadastros.setSenha(rs.getString("senha"));
				cadastros.setCurso(rs.getString("curso"));
				cadastros.setObservacao(rs.getString("observacao"));
				cadastros.setAtivo(rs.getString("ativo"));
				
				cadastro.add(cadastros);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();

				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cadastro;
	} // fim do método listar()

} 