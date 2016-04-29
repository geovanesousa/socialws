package br.com.social.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.social.controller.RedeSocialController;
import br.com.social.model.RedeSocial;

/**
 * 
 * Classe que contem os metodos webservice de RedeSocial
 * @author equipe
 *
 * @since 29/04/2016 13:05:11
 */
@Path("/rede_social")
public class RedeSocialResource {

	/**
	 * insere uma nova rede social recebendo as informações em formato JSON
	 * @param RedeSocial redeSocial
	 * @return String
	 */
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String inserirRedeSocial(RedeSocial redeSocial){
		return new RedeSocialController().inserirRedeSocial(redeSocial);
	}
	
}
