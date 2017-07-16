package twitter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.validation.Errors;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter.domain.Spitter;
import twitter.service.SpitterFeedServiceImpl;

@Controller
public class UserController {
	private SpitterFeedServiceImpl spitterFeedService;
	@Autowired
	public UserController( SpitterFeedServiceImpl spitterFeedService ){
		this.spitterFeedService = spitterFeedService;
	}
	
	@RequestMapping(value = "/register", method = GET)
	public String showRegistrationForm(Model model){
		model.addAttribute( "spitter", new Spitter() );
		return "registerForm";
	}
	/*
	@RequestMapping(value = "/register", method = POST)
	public void processRegistration(@Valid Spitter spitter, Errors errors){
		if( errors.hasErrors() ){
			;
		}
		System.out.println( "full name is: "+spitter.getFullName() );
		//spitterFeedService.save(spitter);
		//return "redirect:/" + spitter.getUsername();
	}
	*/
	@RequestMapping(value = "/register", method = POST)
	public String processRegistration(  @ModelAttribute("spitter")  Spitter spitter, Errors errors, HttpServletRequest request){
		System.out.println(request.getParameter("fullName")); 
		if( errors.hasErrors() ){
			return "registerForm";
		}
		System.out.println( "full name is: "+spitter.getFullName() );
		System.out.println( "updatedByEmail is: "+spitter.isUpdateByEmail() );
		System.out.println( "enabled is: "+spitter.isEnabled() );
		spitterFeedService.save(spitter);
		
		return "redirect:/" + spitter.getUsername();
	}
	
}
