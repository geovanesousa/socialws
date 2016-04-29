package br.com.social.controller;

import java.util.List;

import br.com.social.dao.RedeSocialDAO;
import br.com.social.model.RedeSocial;
import br.com.social.model.Usuario;

public class RedeSocialController {

	public String inserirRedeSocial(RedeSocial redeSocial){
		return new RedeSocialDAO().inserirRedeSocial(redeSocial);
	}

	/**
	 * retorna as redes sociais de um usuario JOIN com base no seu nome de usuario
	 * @param Usuario usuario
	 * @return List<RedeSocial>
	 */
	public List<RedeSocial> listarRedesSocial(Usuario usuario){
		return new RedeSocialDAO().listarTodas(usuario);
	}
	
}
