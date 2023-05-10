package com.ensah.gs_contact.service.contact;

import com.ensah.gs_contact.bo.contact.Contact;
import com.ensah.gs_contact.repository.IContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService{


    private final IContactRepository contactRepository;
    public ContactService(final IContactRepository contactRepository){
        this.contactRepository=contactRepository;
    }
    @Override
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }


    @Override
    public List<Contact> getAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public Iterable<Contact> getAllContactsByOrderByLastName() {
        return (List<Contact>) contactRepository.findAllByOrderByLastName();
    }

}
