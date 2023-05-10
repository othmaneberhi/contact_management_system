package com.ensah.gs_contact.bo.group;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @NotBlank(message = "Please enter a name for the group")
    private String name;

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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
