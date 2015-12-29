/**
 * 
 */
package com.ibm.ws.samples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ws.samples.entities.Usuario;

/**
 * @author lentiummmx
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom<Usuario, Long> {

	Usuario findByNombreUsuario(String nombreUsuario);

}
