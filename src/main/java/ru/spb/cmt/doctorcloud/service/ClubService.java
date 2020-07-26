package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.spb.cmt.doctorcloud.entity.Club;
import ru.spb.cmt.doctorcloud.entity.GeoPlace;
import ru.spb.cmt.doctorcloud.entity.Organization;
import ru.spb.cmt.doctorcloud.entity.Person;
import ru.spb.cmt.doctorcloud.repository.ClubRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Club> getAllClubs() {
        return StreamSupport.stream(clubRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public Club save(Club club) {
        if (club.getId() == 0) {
            return clubRepository.save(club);
        }
        Club clubToUpdate = clubRepository
                .findByIdWithOrganizationMainPersonGeoPlace(club.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        clubToUpdate.setContactEmail(club.getContactEmail());
        clubToUpdate.setContactPhone(club.getContactPhone());
        clubToUpdate.setFullAddress(club.getFullAddress());
        clubToUpdate.setNameFull(club.getNameFull());
        clubToUpdate.setNameShort(club.getNameShort());
        clubToUpdate.setSite(club.getSite());
        GeoPlace geoPlace = club.getGeoPlace();
        if (geoPlace != null) {
            clubToUpdate.setGeoPlace(geoPlace);
        }
        Person mainPerson = club.getMainPerson();
        if (mainPerson != null) {
            clubToUpdate.setMainPerson(mainPerson);
        }
        Organization organization = club.getOrganization();
        if (organization != null) {
            clubToUpdate.setOrganization(organization);
        }
        return clubToUpdate;
    }

    @Transactional
    public void remove(long id) {
        clubRepository.deleteById(id);
    }
}
