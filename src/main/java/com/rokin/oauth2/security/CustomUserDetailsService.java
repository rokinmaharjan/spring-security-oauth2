package com.rokin.oauth2.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rokin.oauth2.user.model.User;
import com.rokin.oauth2.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User dbUser = this.userRepository.findByUsername(username);

		if (dbUser != null) {
			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			for (String role : dbUser.getRoles()) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantedAuthorities.add(authority);
			}
					
			org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					dbUser.getUsername(), dbUser.getPassword(), grantedAuthorities);
			return user;
		} else {
			throw new UsernameNotFoundException(String.format("User [%s] not found", username));
		}
	}

}
