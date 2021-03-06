package com.mahesh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.mahesh.service.*;
import com.mahesh.model.Contact;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;

	@RequestMapping("/")
	public ModelAndView home() {
		List<Contact> listContact = contactService.listAll();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("listCustomer", listContact);
		return mav;
	}

	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		Contact contact = new Contact();
		model.put("contact", contact);
		return "new_contact";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveContact(@ModelAttribute("contact") Contact contact) {
		contactService.save(contact);
		return "redirect:/";
	}

	@RequestMapping("/edit")
	public ModelAndView editContactForm(@RequestParam Integer id) {
	    ModelAndView mav = new ModelAndView("edit_customer");
	    Contact contact = contactService.get(id);
	    mav.addObject("contact", contact);
	    return mav;    
	}
	@RequestMapping("/delete")
	public String deleteContactForm(@RequestParam Integer id) {
	contactService.delete(id);
	
	    return "redirect:/";       
	    }
	
}
