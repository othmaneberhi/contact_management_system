package com.ensah.gs_contact.controller;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.bo.message.Message;
import com.ensah.gs_contact.bo.message.MessageType;
import com.ensah.gs_contact.exception.NotFoundException;
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
    @GetMapping("/contacts/{id}")
    public String showContact(@PathVariable("id") Long id,Model model){
        Optional<Contact> contact = contactService.getContactById(id);
        if(!contact.isPresent()){
            throw new NotFoundException("contact not found");
        }
        model.addAttribute("contact",contact);
        return "contact";
    }

    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable("id") Long id,Model model){
        Optional<Contact> contactToDelete = contactService.getContactById(id);
        if(!contactToDelete.isPresent()){
            throw new NotFoundException("contact not found");
        }
        contactService.deleteContact(contactToDelete.get());
        model.addAttribute("message",new Message("Contact deleted successfully ", MessageType.SUCCESS));

        return "redirect:/contacts";
    }

    @PostMapping("/contacts/{id}/edit")
    public String updateContact(@PathVariable("id") Long id,@ModelAttribute("contact") Contact newContact){
        Optional<Contact> contact = contactService.getContactById(id);
        if(!contact.isPresent()){
            throw new NotFoundException("contact not found");
        }
        contact.get().setAddress(newContact.getAddress());
        contact.get().setGender(newContact.getGender());
        contact.get().setFirstName(newContact.getFirstName());
        contact.get().setLastName(newContact.getLastName());
        contact.get().setPersoEmail(newContact.getPersoEmail());
        contact.get().setProEmail(newContact.getProEmail());
        contact.get().setPersoPhone(newContact.getPersoPhone());
        contact.get().setProPhone(newContact.getProPhone());

        contactService.updateContact(contact.get());
        return "redirect:contacts/"+id;
    }
}
