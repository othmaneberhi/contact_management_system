package com.ensah.gs_contact.service.contact;

import com.ensah.gs_contact.bo.contact.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    public Optional<Contact> getContactById(Long id);
    public void addContact(Contact contact);
    public void updateContact(Contact contact);

    public void deleteContact(Contact contact);

    public List<Contact> getAllContacts();

    public List<Contact> getAllContactsByOrderByLastName();

    public List<Contact> getContactByLastName(String lastname);
    public List<Contact> getContactByFirstName(String firstname);
    public List<Contact> getContactByPersoPhone(String persoPhone);
    public List<Contact> getContactByProPhone(String proPhone);

    public List<Contact> getContactBySimilarName(String name);
    public List<Contact> getContacstByLastName(String lastName);

}
