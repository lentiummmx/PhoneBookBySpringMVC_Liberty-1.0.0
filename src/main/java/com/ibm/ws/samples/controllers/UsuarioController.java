/**
 * 
 */
package com.ibm.ws.samples.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.ws.samples.entities.Usuario;
import com.ibm.ws.samples.models.UsuarioModel;
import com.ibm.ws.samples.models.validators.UsuarioValidator;
import com.ibm.ws.samples.services.UsuarioService;

/**
 * @author lentiummmx
 *
 */
@Controller
public class UsuarioController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	private UsuarioService<Usuario, Long> usuarioService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(usuarioValidator);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("user", new UsuarioModel());
		return "login";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model) {
		request.getSession().invalidate();
		model.addAttribute("user", new UsuarioModel());
		LOGGER.debug("after invalidateSession");
		return "redirect:login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registrationForm(Model model) {
		model.addAttribute("user", new UsuarioModel());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String handleRegistration(@Valid @ModelAttribute("user") UsuarioModel userModel, BindingResult errors, Model model) {
		if (errors.hasErrors()) {
			return "register";
		}
		try {
			Usuario user = new Usuario();
			BeanUtils.copyProperties(userModel, user);
			usuarioService.save(user);
			return "redirect:login";
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error al registrar el usuario.", e);
			model.addAttribute("ERROR", e.getMessage());
			return "register";
		}
	}

}
