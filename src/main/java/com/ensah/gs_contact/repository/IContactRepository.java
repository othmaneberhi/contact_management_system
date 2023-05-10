package com.ensah.gs_contact.repository;

import com.ensah.gs_contact.bo.contact.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends CrudRepository<Contact,Long> {
}
