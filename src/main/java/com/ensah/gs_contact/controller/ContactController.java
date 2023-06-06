package com.ensah.gs_contact.controller;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.bo.group.Group;
import com.ensah.gs_contact.bo.message.Message;
import com.ensah.gs_contact.bo.message.MessageType;
import com.ensah.gs_contact.exception.NotFoundException;
import com.ensah.gs_contact.service.contact.IContactService;
import com.ensah.gs_contact.service.group.IGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {

    private final IContactService contactService;
    private final IGroupService groupService;
    public ContactController(final IContactService contactService,final IGroupService groupService) {
        this.contactService = contactService;
        this.groupService = groupService;
    }
    @GetMapping("/contacts/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact/addContact";
    }

    @PostMapping("/contacts/add")
    public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult result, Model model) {
        if(result.hasErrors()){
            return "contact/addContact";
        }

        // automatically create a group of contacts with same lastname
        Group groupWithThisLastName = groupService.getGroupsByName(contact.getLastName());
        if(groupWithThisLastName==null){
            groupWithThisLastName = new Group(contact.getLastName(),"Group of contacts with lastname "+contact.getLastName());
            groupService.addGroup(groupWithThisLastName);
        }
        contact.setGroups(new HashSet<>());
        contact.getGroups().add(groupWithThisLastName);

        groupWithThisLastName.setContacts(new HashSet<>());
        groupWithThisLastName.getContacts().add(contact);
        contactService.addContact(contact);
        model.addAttribute("message",new Message(contact.getLastName()+ "'s contact added successfully ", MessageType.SUCCESS));


        return "redirect:/contacts/"+contact.getId();
    }

    @GetMapping("/contacts")
    public String showContacts(Model model){
        List<Contact> contactList = contactService.getAllContactsByOrderByLastName();
        model.addAttribute("contacts",contactList);
        return "contact/contacts";
    }
    @GetMapping("/contacts/{id}")
    public String getContactById(@PathVariable("id") Long id,Model model){
        Optional<Contact> contact = contactService.getContactById(id);
        if(contact.isEmpty()){
            throw new NotFoundException("contact not found");
        }
        model.addAttribute("contact",contact.get());
        List<Group> groups = groupService.getAllGroupsByOrderByName();
        model.addAttribute("groups",groups);
        return "contact/contact";
    }

    @PostMapping("/contacts/{id}/delete")
    public String deleteContact(@PathVariable("id") Long id,Model model){
        Optional<Contact> contactToDelete = contactService.getContactById(id);
        if(contactToDelete.isEmpty()){
            throw new NotFoundException("contact not found");
        }
        contactService.deleteContact(contactToDelete.get());
        model.addAttribute("message",new Message(contactToDelete.get().getLastName()+"'s contact deleted successfully ", MessageType.SUCCESS));

        return "redirect:/contacts";
    }

    @PostMapping("/contacts/{id}/edit")
    public String updateContact(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("contact") Contact newContact,
                                BindingResult result){
        if(result.hasErrors()){
            return "contact/updateContact";
        }

        Optional<Contact> contact = contactService.getContactById(id);
        if(contact.isEmpty()){
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
        return "redirect:/contacts/"+id;
    }

    @GetMapping("/contacts/{id}/edit")
    public String updateContactFrom(@PathVariable("id") Long id,Model model){
        Optional<Contact> contact = contactService.getContactById(id);
        if(contact.isEmpty()){
            throw new NotFoundException("contact not found");
        }
        model.addAttribute("contact",contact.get());
        return "contact/updateContact";
    }

    @PostMapping("/contacts/search")
    public String getContactByLastName(@RequestParam("searchOption") String searchOption,
                                       @RequestParam("query") String query,
                                       Model model){

        List<Contact> contacts = null;
        if(query.isEmpty()){
            contacts = contactService.getAllContacts();
        }
        else if (searchOption.equals("name")) {
            contacts = contactService.getContactBySimilarName(query);
            model.addAttribute("message",new Message("Showing contacts results for "+query,MessageType.INFO));
        } else if (searchOption.equals("phone")) {
            contacts = contactService.getContactByPersoPhone(query);
            contacts.addAll( contactService.getContactByProPhone(query));
            model.addAttribute("message",new Message("Showing contacts results for "+query,MessageType.INFO));

        }
        model.addAttribute("contacts",contacts);
        return "contact/contacts";
    }

    @GetMapping("/contacts/search")
        public String getSearch(){
        return "redirect:/contacts";
    }

}
