package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import ru.spb.cmt.doctorcloud.entity.People;
import ru.spb.cmt.doctorcloud.entity.PeopleSection;
import ru.spb.cmt.doctorcloud.entity.PeopleVisit;
import ru.spb.cmt.doctorcloud.entity.Section;
import ru.spb.cmt.doctorcloud.repository.PeopleRepository;
import ru.spb.cmt.doctorcloud.repository.PeopleSectionRepository;
import ru.spb.cmt.doctorcloud.repository.PeopleVisitRepository;
import ru.spb.cmt.doctorcloud.repository.SectionRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PeopleService implements UserDetailsService {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private PeopleVisitRepository peopleVisitRepository;
    @Autowired
    private PeopleSectionRepository peopleSectionRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return peopleRepository.findByCellular(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Transactional(readOnly = true)
    public List<People> getAllPeople() {
        return StreamSupport.stream(peopleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public People register(HttpSession session, People people) {
        if (people.getId() != 0) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        peopleRepository.save(people);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(people, null, new ArrayList<>());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(token);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        return people;
    }

    @Transactional
    public boolean visitSection(People people, long sectionId) {
        if (people.getPeopleSectionList().stream().noneMatch(peopleSection -> peopleSection.getSection().getId() == sectionId)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        PeopleVisit peopleVisit = new PeopleVisit(people, section);
        peopleVisitRepository.save(peopleVisit);
        return true;
    }

    @Transactional
    public void joinSection(People people, long sectionId, int status, int isFee) {
        if (people.getPeopleSectionList().stream().anyMatch(peopleSection -> peopleSection.getSection().getId() == sectionId)) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        PeopleSection peopleSection = new PeopleSection(people, section, status, LocalDate.now(), null, isFee);
        peopleSectionRepository.save(peopleSection);
    }

    @Transactional
    public People save(People people) {
        if (people.getId() == 0) {
            return peopleRepository.save(people);
        }
        People peopleToUpdate = peopleRepository.findByIdWithPeopleSectionList(people.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        peopleToUpdate.setBirthdate(people.getBirthdate());
        peopleToUpdate.setCellular(people.getCellular());
        peopleToUpdate.setName(people.getName());
        peopleToUpdate.setPatronymic(people.getPatronymic());
        peopleToUpdate.setPwd(people.getPwd());
        peopleToUpdate.setSex(people.getSex());
        peopleToUpdate.setNameFull(people.getNameFull());
        peopleToUpdate.setSurname(people.getSurname());
        peopleToUpdate.setStatus(people.getStatus());
        List<PeopleSection> peopleSectionListToUpdate = people.getPeopleSectionList();
        peopleSectionListToUpdate.clear();
        peopleSectionListToUpdate.addAll(people.getPeopleSectionList());
        return peopleToUpdate;
    }

    @Transactional
    public void remove(long id) {
        peopleRepository.deleteById(id);
    }
}
