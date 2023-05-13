package com.ensah.gs_contact.repository.contact;

import com.ensah.gs_contact.bo.contact.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;
import java.util.List;

@Repository
public interface IContactRepository extends CrudRepository<Contact,Long> {

    @OrderBy("lastName")
    List<Contact> findAllByOrderByLastName();
    List<Contact> findContactByLastNameContainingIgnoreCase(String lastname);
    List<Contact> findContactByFirstNameContainingIgnoreCase(String firstname);
    List<Contact> findContactByPersoPhoneContaining(String persoPhone);
    List<Contact> findContactByProPhoneContaining(String proPhone);
    @Query("SELECT c FROM Contact c WHERE SOUNDEX(c.firstName) = SOUNDEX(:name) OR SOUNDEX(c.lastName) = SOUNDEX(:name)")
    List<Contact> findBySimilarName(@Param("name") String name);
}
