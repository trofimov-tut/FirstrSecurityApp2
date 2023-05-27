package com.trofimov.security.FirstrSecurityApp2.services;

import com.trofimov.security.FirstrSecurityApp2.models.Person;
import com.trofimov.security.FirstrSecurityApp2.repositories.PeopleRepository;
import com.trofimov.security.FirstrSecurityApp2.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleDetailService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personOptional = peopleRepository.findByUsername(username);
        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден");
        }
        return new PersonDetails(personOptional.get());
    }

}
