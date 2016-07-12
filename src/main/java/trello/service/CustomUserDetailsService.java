package trello.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import trello.dao.UserRepository;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		LOGGER.debug("load user email : {}", email);
		trello.model.User user = userRepository.findByEmail(email); 
		LOGGER.debug("loaded User : {}", user);
		List<GrantedAuthority> authorities = buildUserAuthority();
		return buildUserForAuthentication(user, authorities);
	}
	
	private User buildUserForAuthentication(trello.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				true, true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
	
	/*
	 * 위에서 보면 어떻게 database 를 이용하여 spring security 를 하는 가를 보여 준다. 설명이 따로 필요 업을 듯. 
	 * 만약에 authentication principal 에 사용자 정의 값들이 들어 가야 한다면 UserDetails 를 따로 만들어 사용해야 한다. 
	 * 여기서 우리는 Spring Security 기본 org.springframework.security.core.userdetails.UserDetails (line 40~41) 를 사용하였다.
	 * */
}

