package trello.controller.user;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableOAuth2Sso
@RestController
public class GithubUserController {
	
	private static final Logger log = LoggerFactory.getLogger(GithubUserController.class);

//	 @RequestMapping("/users/login/facebook")
//	  public Principal user(Principal principal) {
//		log.info("principal {}", principal);
//	    return principal;
//	  }
}
