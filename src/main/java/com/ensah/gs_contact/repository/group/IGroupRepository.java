package com.ensah.gs_contact.repository.group;

import com.ensah.gs_contact.bo.group.Group;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface IGroupRepository extends CrudRepository<Group,Long> {
    @OrderBy("name")
    public List<Group> findAllByOrderByName();
    public Group findGroupByNameEqualsIgnoreCase(String name);
    public  List<Group> findGroupsByNameContainingIgnoreCase(String name);

}
