package exercise.controller;

import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }


//    GET /people — список всех персон
//    POST /people – создание новой персоны
//    DELETE /people/{id} – удаление персоны

    @GetMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getListPerson() {
        return personRepository.findAll();
    }

    @PostMapping("/people")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @DeleteMapping("/user/{id}")

    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

}
