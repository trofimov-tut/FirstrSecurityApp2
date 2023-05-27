package com.trofimov.security.FirstrSecurityApp2.util;

import com.trofimov.security.FirstrSecurityApp2.models.Person;
import com.trofimov.security.FirstrSecurityApp2.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override//говорит о том, что валидируем Person
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (!peopleService.loadUserByUsername(person.getUsername()).isEmpty()) {
            errors.rejectValue("username", "", "Пользователь с таким именем уже существует");
        }
    }
    
}
