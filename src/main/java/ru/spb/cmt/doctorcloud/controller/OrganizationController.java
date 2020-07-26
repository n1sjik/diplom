package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.cmt.doctorcloud.entity.Organization;
import ru.spb.cmt.doctorcloud.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/list")
    public List<Organization> getAll() {
        return organizationService.getAllOrganizations();
    }

    @RequestMapping("/save")
    public Organization save(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        organizationService.remove(id);
    }
}

