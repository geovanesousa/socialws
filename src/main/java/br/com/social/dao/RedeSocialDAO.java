package br.com.social.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.social.controller.UsuarioController;
import br.com.social.factory.ConnectionFactory;
import br.com.social.model.RedeSocial;
import br.com.social.model.Usuario;

/**
 * 
 * Classe que contém os métodos CRUD de Redes Sociais
 * @author equipe
 *
 * @since 29/04/2016 11:58:41
 */
public class RedeSocialDAO extends ConnectionFactory {

	/**
	 * insere uma nova rede social de um usuario JOIN
	 * @param RedeSocial redeSocial
	 * @return String
	 */
	public String inserirRedeSocial(RedeSocial redeSocial) {
		// cria um novo usuario inserindo nele o nome de usuario Join
		Usuario u = new Usuario();
		u.setNomeUsuario(redeSocial.getNomeUsuarioJoin());

		// consulta todas as informacoes do usuario
		Usuario usuario = new UsuarioController().usuarioPorNomeDeUsuario(u);

		String sql = "INSERT INTO rede_social(id,usuario_id,nm_rede,nm_usuario) "
				+ "VALUES (?,?,?,?)";

		PreparedStatement stmt = null;
		Connection conexao = criarConexao();
		ResultSet rs = null;
		Long redeSocialId = 0L;

		try {
			stmt = conexao.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, 0L);
			stmt.setLong(2, usuario.getId());
			stmt.setString(3, redeSocial.getNomeRedeSocial());
			stmt.setString(4, redeSocial.getNomeUsuario());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				redeSocialId = rs.getLong(1);
			}
			return "Rede social inserida com sucesso: "+redeSocialId; 
		} catch (Exception e1) {
			return "Erro ao inserir rede social: " + e1.getMessage();
		} finally {
			fecharConexao(conexao, stmt, rs);
		}
	}
	
	/**
	 * Lista as redes sociais recebendo como parametro o nome de usuario
	 * @param Usuario usuario
	 * @return List<RedeSocial>
	 */
	public List<RedeSocial> listarTodas(Usuario usuario) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RedeSocial> redesSociais = null;

		conexao = criarConexao();
		redesSociais = new ArrayList<RedeSocial>();
		Long idUsuario = new UsuarioDAO().idUsuarioPorNomeDeUsuario(usuario.getNomeUsuario());
		try {
			pstmt = conexao.prepareStatement("SELECT nm_rede, nm_usuario FROM rede_social "
					+ "WHERE usuario_id = (?)");
			pstmt.setLong(1, idUsuario);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				RedeSocial redeSocial = new RedeSocial();
				redeSocial.setNomeRedeSocial(rs.getString(1));
				redeSocial.setNomeUsuario(rs.getString(2));
				redesSociais.add(redeSocial);
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar as redes sociais do usuário JOIN" + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return redesSociais;
	}
}
