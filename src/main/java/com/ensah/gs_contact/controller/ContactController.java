package com.ensah.gs_contact.controller;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.bo.message.Message;
import com.ensah.gs_contact.bo.message.MessageType;
import com.ensah.gs_contact.service.contact.IContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String addContact(@ModelAttribute("contact") Contact contact,Model model) {
        contactService.addContact(contact);
        model.addAttribute("message",new Message("Contact added successfully ", MessageType.SUCCESS));
        return "redirect:/contacts";
    }

    @RequestMapping("/contacts")
    public String showContacts(Model model){
        List<Contact> contactList = (List<Contact>) contactService.getAllContactsByOrderByLastName();
        model.addAttribute("contacts",contactList);
        return "contacts";
    }

    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id,Model model){
        Optional<Contact> contactToDelete = contactService.getContactById(id);
        if(!contactToDelete.isPresent()){
           model.addAttribute("message",new Message("Contact not foung", MessageType.ERROR));
        }
        contactService.deleteContact(contactToDelete.get());
        model.addAttribute("message",new Message("Contact deleted successfully ", MessageType.SUCCESS));
        return "redirect:/contacts";
    }
}
