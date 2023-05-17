package com.ensah.gs_contact.service.group;

import com.ensah.gs_contact.bo.group.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupService {
    public Iterable<Group> getAllGroups();
    public Optional<Group> getGroupById(Long id);
    public void deleteGroup(Group group);
    public void addGroup(Group group);
    public void updateGroup(Group group);
    public List<Group> getAllGroupsByOrderByName();
    public Group getGroupsByName(String name);
    public  List<Group> getAllGroupsContainingName(String name);

}
