package com.ensah.gs_contact.bo.group;

import com.ensah.gs_contact.bo.contact.Contact;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name="Group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "Please enter a name for the group")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<Contact> contacts;
    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
