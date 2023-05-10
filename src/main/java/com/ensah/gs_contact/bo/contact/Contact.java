package com.ensah.gs_contact.bo.contact;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="fname")
    @NotBlank(message = "Please enter the firstname")
    private String first_name;
    @Column(name="lname")
    @NotBlank(message = "Please enter the lastname")
    private String last_name;
    @Column(name="phone1")
    private String perso_phone;
    @Column(name="phone2")
    private String pro_phone;
    @Column(name="address")
    private String address;
    @Email(message = "Please enter a valid email")
    @Column(name="email1")
    private String perso_email;
    @Email(message = "Please enter a valid email")
    @Column(name="email2")
    private String pro_email;
    @NotNull(message = "This field is required,Please choose one of the two genders")
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    public Contact(Long id ,String first_name, String last_name, String perso_phone, String pro_phone, String address, String perso_email, String pro_email, Gender gender) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.perso_phone = perso_phone;
        this.pro_phone = pro_phone;
        this.address = address;
        this.perso_email = perso_email;
        this.pro_email = pro_email;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPerso_phone() {
        return perso_phone;
    }

    public void setPerso_phone(String perso_phone) {
        this.perso_phone = perso_phone;
    }

    public String getPro_phone() {
        return pro_phone;
    }

    public void setPro_phone(String pro_phone) {
        this.pro_phone = pro_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPerso_email() {
        return perso_email;
    }

    public void setPerso_email(String perso_email) {
        this.perso_email = perso_email;
    }

    public String getPro_email() {
        return pro_email;
    }

    public void setPro_email(String pro_email) {
        this.pro_email = pro_email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "contact{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", perso_phone='" + perso_phone + '\'' +
                ", pro_phone='" + pro_phone + '\'' +
                ", address='" + address + '\'' +
                ", perso_email='" + perso_email + '\'' +
                ", pro_email='" + pro_email + '\'' +
                ", gender=" + gender +
                '}';
    }
}

