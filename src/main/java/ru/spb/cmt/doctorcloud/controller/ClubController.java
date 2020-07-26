package ru.spb.cmt.doctorcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.cmt.doctorcloud.entity.Club;
import ru.spb.cmt.doctorcloud.entity.Organization;
import ru.spb.cmt.doctorcloud.service.ClubService;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @RequestMapping("/list")
    public List<Club> getAll() {
        return clubService.getAllClubs();
    }

    @RequestMapping("/save")
    public Club save(@RequestBody Club club) {
        return clubService.save(club);
    }

    @RequestMapping("/remove")
    public void remove(@RequestParam long id) {
        clubService.remove(id);
    }

}
