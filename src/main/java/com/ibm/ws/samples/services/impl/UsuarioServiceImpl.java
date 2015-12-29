/**
 * 
 */
package com.ibm.ws.samples.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ws.samples.entities.Usuario;
import com.ibm.ws.samples.repositories.UsuarioRepository;
import com.ibm.ws.samples.services.UsuarioService;

/**
 * @author lentiummmx
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService<Usuario, Long> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		LOGGER.debug("usuario :: " + usuario);
		return usuarioRepository.save(usuario);
	}

}
