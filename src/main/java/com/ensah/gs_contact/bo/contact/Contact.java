package com.ensah.gs_contact.bo.contact;

import com.ensah.gs_contact.bo.group.Group;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Contact")
@DynamicUpdate
//tells Spring Data JPA to generate an SQL update statement that
//only includes the modified attributes, rather than updating all attributes of the entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="fname")
    @NotBlank(message = "Please enter the firstname")
    private String firstName;

    @Column(name="lname")
    @NotBlank(message = "Please enter the lastname")
    private String lastName;
    @Column(name="phone1")
    private String persoPhone;
    @Column(name="phone2")
    private String proPhone;
    @Column(name="address")
    private String address;
    @Email(message = "Please enter a valid email")
    @Column(name="email1")
    private String persoEmail;
    @Email(message = "Please enter a valid email")
    @Column(name="email2")
    private String proEmail;
    @NotNull(message = "This field is required,Please choose one of the two genders")
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name="contact_groups",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();


    public Contact(Long id , String firstName, String lastName, String persoPhone, String proPhone, String address, String persoEmail, String proEmail, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.persoPhone = persoPhone;
        this.proPhone = proPhone;
        this.address = address;
        this.persoEmail = persoEmail;
        this.proEmail = proEmail;
        this.gender = gender;
    }

    public Contact() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getPersoPhone() {
        return persoPhone;
    }

    public void setPersoPhone(String perso_phone) {
        this.persoPhone = perso_phone;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String pro_phone) {
        this.proPhone = pro_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersoEmail() {
        return persoEmail;
    }

    public void setPersoEmail(String perso_email) {
        this.persoEmail = perso_email;
    }

    public String getProEmail() {
        return proEmail;
    }

    public void setProEmail(String pro_email) {
        this.proEmail = pro_email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "contact{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", perso_phone='" + persoPhone + '\'' +
                ", pro_phone='" + proPhone + '\'' +
                ", address='" + address + '\'' +
                ", perso_email='" + persoEmail + '\'' +
                ", pro_email='" + proEmail + '\'' +
                ", gender=" + gender +
                '}';
    }
}

