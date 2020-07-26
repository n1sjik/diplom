package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import ru.spb.cmt.doctorcloud.entity.GeoPlace;
import ru.spb.cmt.doctorcloud.entity.Organization;
import ru.spb.cmt.doctorcloud.entity.Person;
import ru.spb.cmt.doctorcloud.repository.OrganizationRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<Organization> getAllOrganizations() {
        Stream<Organization> organizationStream = StreamSupport.stream(organizationRepository.findAll().spliterator(), false);
        return organizationStream.collect(Collectors.toList());
    }

    @Transactional
    public Organization save(Organization organization) {
        if (organization.getId() == 0) {
            return organizationRepository.save(organization);
        }
        Organization organizationToUpdate = organizationRepository
                .findByIdWithGeoPlacePerson(organization.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        organizationToUpdate.setCity(organization.getCity());
        organizationToUpdate.setCityArea(organization.getCityArea());
        organizationToUpdate.setContactEmail(organization.getContactEmail());
        organizationToUpdate.setContactPhone(organization.getContactPhone());
        organizationToUpdate.setFullAddress(organization.getFullAddress());
        organizationToUpdate.setNameFull(organization.getNameFull());
        organizationToUpdate.setNameShort(organization.getNameShort());
        organizationToUpdate.setSite(organization.getSite());
        GeoPlace geoPlace = organization.getGeoPlace();
        if (geoPlace != null) {
            organizationToUpdate.setGeoPlace(geoPlace);
        }
        Person mainPerson = organization.getMainPerson();
        if (mainPerson != null) {
            organizationToUpdate.setMainPerson(mainPerson);
        }
        return organizationToUpdate;
    }

    @Transactional
    public void remove(long id) {
        organizationRepository.deleteById(id);
    }
}
