package co.edu.uniajc.apiRest.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

public interface AuthServiceDelegator {
	
	public String login(String username, String password);
	public Optional<User> findByToken(String token);

}
