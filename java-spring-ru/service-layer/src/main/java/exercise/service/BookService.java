package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public ResponseEntity<?> create(BookCreateDTO bookData) {

        var author = authorRepository.findById(bookData.getAuthorId());
        if (author.isEmpty()) {
            return new ResponseEntity<>("Author not found", HttpStatus.BAD_REQUEST);
        }
        var book = bookMapper.map(bookData);
        bookRepository.save(book);

        return new ResponseEntity<>(bookMapper.map(book), HttpStatus.CREATED);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        authorRepository.findById(book.getA)
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        authorRepository.findById(bookData.getAuthorId().get()).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + bookData.getAuthorId() + " not found")
        );

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        var bookDTO = bookMapper.map(book);
        return bookDTO;
    }

}
