package co.edu.uniajc.apiRest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.apiRest.exception.ResourceNotFoundException;
import co.edu.uniajc.apiRest.model.Usuario;
import co.edu.uniajc.apiRest.service.UsuarioServiceDelegator;
import co.edu.uniajc.apiRest.util.Util;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	@Autowired
	private UsuarioServiceDelegator UsuarioServ;

	@GetMapping("/usuario")
	public List<Usuario> getAll() throws NoSuchMethodException, ResourceNotFoundException {
		try {
			return UsuarioServ.getAll();
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Integer idUsuario) throws NoSuchMethodException {
		Usuario usuario = new Usuario();
		try {
			Util util = new Util();
			boolean estado = util.isNumeric(idUsuario.toString());
			if (estado == true) {
				usuario = UsuarioServ.getById(idUsuario);
				return ResponseEntity.ok().body(usuario);
			}

		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping("/usuario")
	public Usuario create(@Valid @RequestBody Usuario usuario) throws NoSuchMethodException {
		try {
			return UsuarioServ.create(usuario);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}

	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> edit(@PathVariable(value = "id") Integer idUsuario,
			@Valid @RequestBody Usuario usuarioDetalle) throws NoSuchMethodException {
		Usuario usuario = new Usuario();
		try {
			usuario = UsuarioServ.edit(idUsuario, usuarioDetalle);
			return ResponseEntity.ok(usuario);
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}

	@DeleteMapping("/usuario/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer idUsuario)
			throws ResourceNotFoundException, NoSuchMethodException {
		try {
			UsuarioServ.delete(idUsuario);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (IndexOutOfBoundsException exp) {
			throw new NoSuchMethodException();
		}
	}
}
