package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.spb.cmt.doctorcloud.entity.*;
import ru.spb.cmt.doctorcloud.repository.SectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Transactional(readOnly = true)
    public List<Section> getAllSections() {
        return StreamSupport.stream(sectionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public Section save(Section section) {
        if (section.getId() == 0) {
            return sectionRepository.save(section);
        }
        Section sectionToUpdate = sectionRepository.findByIdWithSectionTypeMainPersonClubOrganizationPeopleSections(section.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        sectionToUpdate.setFreePlace(section.getFreePlace());
        sectionToUpdate.setNameFull(section.getNameFull());
        sectionToUpdate.setNameShort(section.getNameShort());
        Club club = section.getClub();
        if (club != null) {
            sectionToUpdate.setClub(club);
        }
        Person mainPerson = section.getMainPerson();
        if (mainPerson != null) {
            sectionToUpdate.setMainPerson(mainPerson);
        }
        Organization organization = section.getOrganization();
        if (organization != null) {
            sectionToUpdate.setOrganization(organization);
        }
        SectionType sectionType = section.getSectionType();
        if (sectionType != null) {
            sectionToUpdate.setSectionType(sectionType);
        }
        List<PeopleSection> peopleSectionListToUpdate = sectionToUpdate.getPeopleSectionList();
        peopleSectionListToUpdate.clear();
        peopleSectionListToUpdate.addAll(section.getPeopleSectionList());
        return sectionToUpdate;
    }

    @Transactional
    public void remove(long id) {
        sectionRepository.deleteById(id);
    }

    public List<SectionDTO> search(long peopleId) {
        ArrayList<SectionDTO> sectionDTOS = new ArrayList<>();
        List<Object[]> search = sectionRepository.search(peopleId);
        for (Object[] objects : search) {
            Section section = (Section) objects[0];
            Long mySectionCount = (Long) objects[1];
            Club club = section.getClub();
            Organization organization = section.getOrganization();
            sectionDTOS.add(new SectionDTO(section.getId(),
                    section.getNameFull(),
                    section.getNameShort(),
                    section.getSectionType(),
                    section.getPaidPlace(),
                    section.getFreePlace(),
                    club == null ? null : club.getGeoPlace(),
                    organization == null ? null : organization.getGeoPlace(),
                    mySectionCount));
        }
        return sectionDTOS;

    }
}
