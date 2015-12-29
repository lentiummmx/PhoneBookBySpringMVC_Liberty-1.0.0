/**
 * 
 */
package com.ibm.ws.samples.config.security.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.ws.samples.entities.Rol;
import com.ibm.ws.samples.entities.Usuario;
import com.ibm.ws.samples.repositories.UsuarioRepository;

/**
 * @author lentiummmx
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		for (Rol role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(Rol.ROLE_PREFIX + role.getNombre()));
		}
		return authorities;
	}
	
	private SecurityUser createSecurityUser(Usuario user) {
		return new SecurityUser(user.getNombreUsuario(), user.getContrasena(), getGrantedAuthorities(user));
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByNombreUsuario(username);
		if (user == null) {
			String message = "Username not found " + username;
			LOGGER.info(message);
			throw new UsernameNotFoundException(message);
		}
		LOGGER.info("Found user in database: " + user);
		return createSecurityUser(user);
	}
	
	private Authentication authenticate(Usuario user) {
		return new UsernamePasswordAuthenticationToken(createSecurityUser(user), null, getGrantedAuthorities(user));
	}
	
	public void signin(Usuario user) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(user));
	}

}
