/**
 * 
 */
package com.ibm.ws.samples.models.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ibm.ws.samples.entities.Usuario;
import com.ibm.ws.samples.models.UsuarioModel;

/**
 * @author lentiummmx
 *
 */
@Component
public class UsuarioValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz) || UsuarioModel.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreUsuario", "NotEmpty.user");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasena", "NotEmpty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correoElectronico", "NotEmpty.email");
	}

}
