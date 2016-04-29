package br.com.social.controller;

import java.util.List;
import br.com.social.dao.UsuarioDAO;
import br.com.social.model.Usuario;

/**
 * 
 * Classe responsável por ser o controle entre o RESOURCE e a camada DAO
 * @author equipe
 *
 * @since 20/04/2016 13:01:27
 */
public class UsuarioController {

	public List<Usuario> listarTodos(){
		return new UsuarioDAO().listarTodos();
	}
	
	public String inserirUsuario(Usuario usuario){
		return new UsuarioDAO().inserirUsuario(usuario);
	}
	
	public Usuario usuarioPorNomeDeUsuario(Usuario u){
		Usuario usuario = new UsuarioDAO().usuarioPorNomeDeUsuario(u);
		usuario.setRedesSociais(new RedeSocialController().listarRedesSocial(usuario));
		return usuario;
	}
	
}
