package br.com.social.resource;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.social.controller.UsuarioController;
import br.com.social.model.Usuario;

/**
 * 
 * Classe que contem os métodos de acesso ao webservice de usuario
 * 
 * @author equipe
 *
 * @since 20/04/2016 13:04:00
 */
@Path("/usuario")
public class UsuarioResource {

	/**
	 * insere um novo usuário recebendo as informações em formato JSON
	 * 
	 * @param u
	 * @return
	 */
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String inserirUsuario(Usuario u) {
		return new UsuarioController().inserirUsuario(u);
	}
	
	/**
	 * consulta um usuário por nome de usuário recebendo as informações em formato JSON
	 * 
	 * @param u
	 * @return Usuario usuario
	 */
	@POST
	@Path("/nome_de_usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario usuarioPorNomeDeUsuario(Usuario u) {
		return new UsuarioController().usuarioPorNomeDeUsuario(u);
	}

	/**
	 * Método que faz chamada ao controller
	 * 
	 * @return List<Usuario>
	 */
	@GET
	@Path("/listar_todos")
	@Produces("application/json")
	public List<Usuario> listarTodos() {
		List<Usuario> lista = new ArrayList<Usuario>();
		lista = new UsuarioController().listarTodos();
		return lista;
	}

}
