package com.example.library.controller;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service=service;
    }

    // Add book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return service.addBook(book);
    }

    // View all books
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    // Search book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return service.getBookById(id);
    }

    // Update availability
    @PutMapping("/{id}/availability")
    public Book updateAvailability(@PathVariable("id") Long id,
                                   @RequestParam("available") boolean available) {
        return service.updateAvailability(id, available);
    }

    // Delete book
    @DeleteMapping("/{id}")
    public Map<String, String> deleteBook(@PathVariable("id") Long id) {
        service.deleteBook(id);
        return Map.of("message", "Book deleted successfully");
    }
}
