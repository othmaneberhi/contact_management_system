package com.ensah.gs_contact.repository;

import com.ensah.gs_contact.bo.contact.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface IContactRepository extends CrudRepository<Contact,Long> {

    @OrderBy("lastName")
    List<Contact> findAllByOrderByLastName();
}