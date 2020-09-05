package co.edu.uniajc.apiRest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.apiRest.model.Usuario;
import co.edu.uniajc.apiRest.repositoy.UsuarioRepository;
import co.edu.uniajc.apiRest.exception.ResourceNotFoundException;

@Service
@Transactional
public class UsuarioService implements UsuarioServiceDelegator {

	@Autowired
	private UsuarioRepository usuario_Repository;

	/**
	 * getAll() Se lista el contenido de la tabla usuario
	 * 
	 * @return
	 */
	public List<Usuario> getAll() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		try {
			listaUsuario = usuario_Repository.findAll();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return listaUsuario;
	}

	/**
	 * getById Se obtiene por medio de Identificador de tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario getById(Integer idUsuario) {
		Usuario usuario = new Usuario();
		try {
			if (idUsuario > 0) {
				usuario = usuario_Repository.findById(idUsuario).orElseThrow(
						() -> new ResourceNotFoundException("Usuario no encontrado por Id :: " + idUsuario));
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return usuario;
	}

	/**
	 * create Se crea objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario create(Usuario usuario) {
		Usuario user = new Usuario();
		try {
			if (user != null) {
				user = usuario_Repository.save(usuario);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return user;
	}

	/**
	 * edit Se edita objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario edit(Integer idUsuario, Usuario usuarioDetalle) {
		Usuario user = new Usuario();
		try {
			if (idUsuario > 0) {
				Usuario usuario = getById(idUsuario);
				if (usuario != null) {
					usuario.setNombre(usuarioDetalle.getNombre());
					usuario.setContrasena(usuarioDetalle.getContrasena());
					usuario.setPrivilegio(usuarioDetalle.getPrivilegio());
					user = usuario_Repository.save(usuario);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return user;
	}

	/**
	 * delete() Se elimina objeto en tabla usuario
	 * 
	 * @param idUsuario
	 * @return
	 */
	public void delete(Integer idUsuario) {
		try {
			if (idUsuario > 0) {
				Usuario usuario = getById(idUsuario);
				if (usuario != null) {
					usuario_Repository.delete(usuario);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
