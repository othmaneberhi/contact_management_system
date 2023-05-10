package com.ensah.gs_contact.controller;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.bo.contact.Gender;
import com.ensah.gs_contact.service.contact.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ContactController {

    private final IContactService contactService;


    public ContactController(final IContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping("/contacts/add")
    public String addContact(@ModelAttribute("contact") Contact contact) {
        System.out.println(contact);
        contactService.addContact(contact);
        return "redirect:/contacts";
    }

    @RequestMapping("/contacts")
    public String showContacts(Model model){
        List<Contact> contactList = (List<Contact>) contactService.getAllContacts();
        model.addAttribute("contacts",contactList);
        return "contacts";
    }
}
