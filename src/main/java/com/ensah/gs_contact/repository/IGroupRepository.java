package com.ensah.gs_contact.repository;

import com.ensah.gs_contact.bo.group.Group;
import org.springframework.data.repository.CrudRepository;

public interface IGroupRepository extends CrudRepository<Group,Long> {
}
