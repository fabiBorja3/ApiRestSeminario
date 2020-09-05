package co.edu.uniajc.apiRest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.apiRest.service.AuthServiceDelegator;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
	
	@Autowired
	private AuthServiceDelegator Auth_serv;
	
	@PostMapping("/token")
    public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
       String token= Auth_serv.login(username,password);
       if(StringUtils.isEmpty(token)){
           return "no token found";
       }
       return token;
    }

}
