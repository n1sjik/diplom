package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.spb.cmt.doctorcloud.entity.Project;
import ru.spb.cmt.doctorcloud.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/list")
    public List<Project> getAll() {
        return projectService.getAllSections();
    }

    @RequestMapping("/save")
    public Project save(@RequestBody Project project) {
        return projectService.save(project);
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        projectService.remove(id);
    }

    @RequestMapping("/{id}")
    public Project getProject(@PathVariable long id) {
        return projectService.getProject(id);
    }



}
