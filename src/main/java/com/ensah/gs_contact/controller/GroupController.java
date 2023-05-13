package com.ensah.gs_contact.controller;

import com.ensah.gs_contact.bo.group.Group;
import com.ensah.gs_contact.bo.message.Message;
import com.ensah.gs_contact.bo.message.MessageType;
import com.ensah.gs_contact.exception.NotFoundException;
import com.ensah.gs_contact.service.group.IGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {

    private final IGroupService groupService;

    public GroupController(final IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public String showGroups(Model model){
        List<Group> groups = groupService.getAllGroupsByOrderByName();
        model.addAttribute("groups",groups);
        return "group/groups";
    }

    @GetMapping("/groups/add")
    public String showAddGroupForm(Model model){
        model.addAttribute("group",new Group());
        return "group/addGroup";
    }

    @PostMapping("/groups/add")
    public String addGroup(@Valid @ModelAttribute("group") Group group, BindingResult result, Model model){
        if(result.hasErrors()){
            return "group/addGroup";
        }
        groupService.addGroup(group);
        model.addAttribute("message",new Message("Group added successfully", MessageType.SUCCESS));
        return "redirect:/groups";
    }

    @GetMapping("/groups/search")
    public String searchGroups(@RequestParam("name") String name,Model model){
        List<Group> groups = groupService.getAllGroupsByName(name);
        model.addAttribute("groups",groups);
        model.addAttribute("message",new Message("Showing group results for "+name,MessageType.INFO));
        return "group/groups";
    }

    @GetMapping("/groups/{id}")
    public  String showGroup(@PathVariable("id") Long id,Model model){
        Optional<Group> group = groupService.getGroupById(id);
        if (group.isEmpty()){
           throw new NotFoundException("Group not found");
        }
        model.addAttribute("group",group.get());
        return "group/group";
    }

    @PostMapping("/groups/{id}/delete")
    public String deleteGroup(@PathVariable("id") Long id,Model model){
       Optional<Group> groupToDelete = groupService.getGroupById(id);
       if (groupToDelete.isEmpty()){
           throw new NotFoundException("Group not found");
       }
       groupService.deleteGroup(groupToDelete.get());
       model.addAttribute("message", new Message(groupToDelete.get().getName()+" group deleted successfully",MessageType.SUCCESS));
       return "redirect:/groups";
    }

    @PostMapping("/groups/{id}/edit")
    public String updateGroup(@Valid @PathVariable("id") Long id,
                              @ModelAttribute("group") Group newGroup,
                              BindingResult result){
        if(result.hasErrors()){
            return "group/updateGroup";
        }
        Optional<Group> groupToEdit = groupService.getGroupById(id);
        if (groupToEdit.isEmpty()){
            throw new NotFoundException("Group not found");
        }
        groupToEdit.get().setName(newGroup.getName());
        groupToEdit.get().setDescription(newGroup.getDescription());

        groupService.updateGroup(groupToEdit.get());
        return "redirect:/groups";
    }

    @GetMapping("/groups/{id}/edit")
    public String updateGroupForm(@PathVariable("id") Long id,Model model){
        Optional<Group> groupToEdit = groupService.getGroupById(id);
        if (groupToEdit.isEmpty()){
            throw new NotFoundException("Group not found");
        }
        model.addAttribute("group",groupToEdit.get());
        return "group/updateGroup";
    }


}
