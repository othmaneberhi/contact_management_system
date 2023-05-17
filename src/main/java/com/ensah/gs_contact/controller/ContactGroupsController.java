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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ContactGroupsController {
    private final IContactService contactService;
    private final IGroupService groupService;
    public ContactGroupsController(final IContactService contactService,final IGroupService groupService) {
        this.contactService = contactService;
        this.groupService = groupService;
    }

    @PostMapping("/groups/contacts/add")
    public String addContact(@RequestParam("group_id") Long group_id,
                             @RequestParam("contact_id") Long  contact_id,
                             Model model){

        Optional<Contact> contact = contactService.getContactById(contact_id);
        Optional<Group> group = groupService.getGroupById(group_id);

        if(group.isEmpty()){
            throw new NotFoundException("Group not found");
        }
        if(contact.isEmpty()){
            throw new NotFoundException("Contact not found");
        }

        contact.get().getGroups().add(group.get());
        group.get().getContacts().add(contact.get());

        contactService.addContact(contact.get());

        model.addAttribute("message",new Message("Contact "+contact.get().getLastName()+" added successfully to "+group.get().getName(), MessageType.SUCCESS));
        return "redirect:/groups/"+group.get().getId();
    }
    
}
