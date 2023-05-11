package com.ensah.gs_contact.repository;

import com.ensah.gs_contact.bo.group.Group;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface IGroupRepository extends CrudRepository<Group,Long> {
    @OrderBy("name")
    public List<Group> findAllByOrderByName();
    public List<Group> findGroupByNameContainingIgnoreCase(String name);

}
