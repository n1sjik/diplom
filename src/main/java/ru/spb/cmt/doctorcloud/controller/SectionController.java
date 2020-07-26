package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.cmt.doctorcloud.entity.BaseEntity;
import ru.spb.cmt.doctorcloud.entity.People;
import ru.spb.cmt.doctorcloud.entity.Section;
import ru.spb.cmt.doctorcloud.entity.SectionDTO;
import ru.spb.cmt.doctorcloud.service.SectionService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @RequestMapping("/list")
    public List<Section> getAll() {
        return sectionService.getAllSections();
    }

    @RequestMapping("/search")
    public List<SectionDTO> search(Principal principal) {
        if (principal == null) {
            return new ArrayList<>();
        }
        Object principalObject = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        if (!(principalObject instanceof People)) {
            return new ArrayList<>();
        }
        long peopleId = ((People) principalObject).getId();
        return sectionService.search(peopleId);
    }

    @RequestMapping("/save")
    public Section save(@RequestBody Section section) {
        return sectionService.save(section);
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        sectionService.remove(id);
    }
}
