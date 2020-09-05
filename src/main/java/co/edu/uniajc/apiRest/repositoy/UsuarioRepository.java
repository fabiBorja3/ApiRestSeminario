package co.edu.uniajc.apiRest.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.apiRest.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM usuario WHERE nombre = ?1 and contrasena = ?2 ", nativeQuery = true)
	Optional<Usuario> login(String username,String password);
	Optional<Usuario> findByToken(String token);
}

