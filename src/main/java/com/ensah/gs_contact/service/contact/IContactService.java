package com.ensah.gs_contact.service.contact;

import com.ensah.gs_contact.bo.contact.Contact;

import java.util.Optional;

public interface IContactService {
    public Optional<Contact> getContactById(Long id);
    public void addContact(Contact contact);
    public void updateContact(Contact contact);

    void deleteContact(Contact contact);

    public Iterable<Contact> getAllContacts();

}
