package br.com.social.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.social.controller.RedeSocialController;
import br.com.social.factory.ConnectionFactory;
import br.com.social.model.RedeSocial;
import br.com.social.model.Usuario;

/**
 * 
 * Classe responsável por conter os métodos CRUD de Usuário
 * 
 * @author equipe
 *
 * @since 19/04/2016 13:44:17
 */
public class UsuarioDAO extends ConnectionFactory {

	/**
	 * Método que cadastra usuários
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Usuario usuario
	 */
	public String inserirUsuario(Usuario usuario) {

		String sql = "INSERT INTO usuario(id,nm_usuario,nm_completo,biografia,senha,foto,email) "
				+ "VALUES (?,?,?,?,?,?,?)";

		PreparedStatement stmt = null;
		Connection conexao = criarConexao();
		ResultSet rs = null;
		Long usuarioId = 0L;

		try {
			stmt = conexao.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, 0L);
			stmt.setString(2, usuario.getNomeUsuario());
			stmt.setString(3, usuario.getNomeCompleto());
			stmt.setString(4, usuario.getBiografia());
			stmt.setString(5, usuario.getSenha());
			stmt.setBytes(6, usuario.getFoto());
			stmt.setString(7, usuario.getEmail());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				usuarioId = rs.getLong(1);
			}

			return "Usuário inserido com sucesso: " + usuarioId + "!";

		} catch (Exception e1) {
			return "Já existe um usuário com esse nome: "
					+ usuario.getNomeUsuario();
		} finally {
			fecharConexao(conexao, stmt, rs);
		}
	}

	/**
	 * Método responsavel por listar todos os usuários JOIN
	 * 
	 * @return List<Usuario> usuarios
	 */
	public List<Usuario> listarTodos() {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Usuario> usuarios = null;

		conexao = criarConexao();
		usuarios = new ArrayList<Usuario>();
		try {
			pstmt = conexao.prepareStatement("select * from usuario");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getLong(1));
				usuario.setNomeUsuario(rs.getString(2));
				usuario.setNomeCompleto(rs.getString(3));
				usuario.setBiografia(rs.getString(4));
				usuario.setSenha(rs.getString(5));
				usuario.setFoto(rs.getBytes(6));
				usuario.setEmail(rs.getString(7));
				
				usuario.setRedesSociais(new RedeSocialController().listarRedesSocial(usuario));

				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os usuários JOIN" + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return usuarios;
	}

	/**
	 * Método responsavel por consultar um usuário JOIN
	 * 
	 * @return Usuario usuario
	 */
	public Usuario usuarioPorNomeDeUsuario(Usuario u) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Usuario usuario = null;

		conexao = criarConexao();
		usuario = new Usuario();
		try {
			String sql = "SELECT * FROM usuario WHERE nm_usuario=?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, u.getNomeUsuario());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getLong(1));
				usuario.setNomeUsuario(rs.getString(2));
				usuario.setNomeCompleto(rs.getString(3));
				usuario.setBiografia(rs.getString(4));
				usuario.setSenha(rs.getString(5));
				usuario.setFoto(rs.getBytes(6));
				usuario.setEmail(rs.getString(7));
			}
			return usuario;
		} catch (Exception e) {
			return null;
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}

	}

	/**
	 * Método responsavel por consultar o ID de um usuário JOIN
	 * 
	 * @param String
	 *            nomeUsuario
	 * @return Long id
	 */
	public Long idUsuarioPorNomeDeUsuario(String nomeUsuario) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long id = 0L;

		conexao = criarConexao();
		try {
			String sql = "SELECT id FROM usuario WHERE nm_usuario=?";
			pstmt = conexao.prepareStatement(sql);
			pstmt.setString(1, nomeUsuario);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			return id;
		} catch (Exception e) {
			return id;
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}

	}

	/*
	 * public String atualizarUsuario(Usuario usuario) {
	 * 
	 * String sql = "UPDATE usuario SET usuario.nm_usuario = ?, " +
	 * "usuario.nm_completo = ?, " +
	 * "usuario.biografia = ?, usuario.senha = ?, usuario.foto = ?, usuario.email = ? "
	 * + "WHERE usuario.nm_usuario = ?;"; PreparedStatement stmt = null;
	 * Connection conexao = criarConexao(); ResultSet rs = null;
	 * 
	 * try { stmt = conexao.prepareStatement(sql,
	 * Statement.RETURN_GENERATED_KEYS); stmt.setInt(1, 0); stmt.setString(2,
	 * usuario.getNomeUsuario()); stmt.setString(3, usuario.getNomeCompleto());
	 * stmt.setString(4, usuario.getBiografia()); stmt.setString(5,
	 * usuario.getSenha()); stmt.setBytes(6, usuario.getFoto());
	 * stmt.setString(7, usuario.getEmail()); stmt.execute(); return
	 * "Usuário atualizado com sucesso!";
	 * 
	 * } catch (Exception e1) { return
	 * "Já existe um usuário com esse nome: "+usuario.getNomeUsuario(); }
	 * finally { fecharConexao(conexao, stmt, rs); } }
	 */

}
