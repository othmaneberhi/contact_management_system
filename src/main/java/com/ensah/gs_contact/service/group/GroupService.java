package com.ensah.gs_contact.service.group;

import com.ensah.gs_contact.bo.group.Group;
import com.ensah.gs_contact.repository.group.IGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService{

    private final IGroupRepository groupRepository;

    public GroupService(final IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public Iterable<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void updateGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public List<Group> getAllGroupsByOrderByName() {
        return groupRepository.findAllByOrderByName();
    }

    @Override
    public List<Group> getAllGroupsByName(String name) {
        return groupRepository.findGroupByNameContainingIgnoreCase(name);
    }
}
