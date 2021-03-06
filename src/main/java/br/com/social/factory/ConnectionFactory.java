package br.com.social.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * Classe responsável por criar e fechar conexão com o banco de dados
 * 
 * @author equipe
 *
 * @since 19/04/2016 13:40:46
 */
public class ConnectionFactory {

	private final static String URL = "jdbc:mysql://localhost:3306/social";
	private final static String USUARIO = "root";
	private final static String SENHA = "614398";
	private final static String DRIVER = "com.mysql.jdbc.Driver";

	public Connection criarConexao() {
		Connection conexao = null;
		try {
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			return conexao;
		} catch (Exception e) {
			System.out.println("Erro ao criar conexão com o banco de dados");
			e.printStackTrace();
			return null;
		}
	}

	public void fecharConexao(Connection conexao, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (conexao != null) {
				conexao.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com o banco de dados");
		}
	}

}
