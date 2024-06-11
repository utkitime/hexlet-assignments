package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactData) {
        var contact = toEntity(contactData); // Сначала в Entity
        contactRepository.save(contact);
        var contactDTO = toDTO(contact); // Потом в DTO
        return contactDTO;
    }

    private ContactDTO toDTO(Contact contact) {
        var dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setCreatedAt(contact.getCreatedAt());
        return dto;
    }

    private Contact toEntity(ContactCreateDTO contactDto) {
        var contact = new Contact();
        contact.setLastName(contactDto.getLastName());
        contact.setFirstName(contactDto.getFirstName());
        return contact;
    }
}





