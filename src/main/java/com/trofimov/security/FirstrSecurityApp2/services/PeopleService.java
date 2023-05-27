package com.trofimov.security.FirstrSecurityApp2.services;

import com.trofimov.security.FirstrSecurityApp2.models.Person;
import com.trofimov.security.FirstrSecurityApp2.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUserByUsername(String username) {
        Optional<Person> personOptional = peopleRepository.findByUsername(username);
        return personOptional;
    }

}
