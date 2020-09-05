package co.edu.uniajc.apiRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniajc.apiRest.model.Usuario;
import co.edu.uniajc.apiRest.repositoy.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Service
@Transactional
public class AuthService implements AuthServiceDelegator {
	
	@Autowired
	private UsuarioRepository usuario_Repository;
	
	public String login(String username, String password) {
		Optional<Usuario> customer = usuario_Repository.login(username,password);
        if(customer.isPresent()){
            String token = UUID.randomUUID().toString();
            Usuario custom= customer.get();
            custom.setToken(token);
            usuario_Repository.save(custom);
            return token;
        }

        return StringUtils.EMPTY;
    }
	
	public Optional<User> findByToken(String token) {
		Optional<Usuario> customer = usuario_Repository.findByToken(token);
        if(customer.isPresent()){
        	Usuario customer1 = customer.get();
            User user= new User(customer1.getNombre(), customer1.getContrasena(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

}
