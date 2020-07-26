package ru.spb.cmt.doctorcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import ru.spb.cmt.doctorcloud.entity.Person;
import ru.spb.cmt.doctorcloud.entity.PersonRole;
import ru.spb.cmt.doctorcloud.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return personRepository.findByCellular(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional
    public Person save(Person person) {
        if (person.getId() == 0) {
            return personRepository.save(person);
        }
        Person personToUpdate = personRepository.findByIdWithRoles(person.getId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        personToUpdate.setCellular(person.getCellular());
        personToUpdate.setName(person.getName());
        personToUpdate.setPatronymic(person.getPatronymic());
        personToUpdate.setPwd(person.getPwd());
        personToUpdate.setSurname(person.getSurname());
        personToUpdate.setNameFull(person.getNameFull());
        List<PersonRole> rolesToUpdate = personToUpdate.getRoles();
        rolesToUpdate.clear();
        rolesToUpdate.addAll(person.getRoles());
        return personToUpdate;
    }

    @Transactional
    public void remove(long id) {
        personRepository.deleteById(id);
    }
}
