package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private static Connection conexao;

    private ConexaoFactory() {}

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        
        return conexao;
    }
    
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}