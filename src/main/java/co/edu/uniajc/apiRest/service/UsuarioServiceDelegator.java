package co.edu.uniajc.apiRest.service;

import java.util.List;

import co.edu.uniajc.apiRest.model.Usuario;

public interface UsuarioServiceDelegator {
	/**
	 * getAll() Se lista el contenido de la tabla usuario
	 * 
	 * @return
	 */
	public List<Usuario> getAll();

	/**
	 * getById Se obtiene por medio de Identificador de tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario getById(Integer idUsuario);

	/**
	 * create Se crea objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario create(Usuario usuario);

	/**
	 * edit Se edita objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario edit(Integer idUsuario, Usuario usuarioDetalle);

	/**
	 * delete() Se elimina objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public void delete(Integer idUsuario);
}
