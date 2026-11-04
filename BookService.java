package com.example.library.service;

import com.example.library.exception.BookNotFoundException;
import com.example.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    // No database: books are stored in memory
    private final List<Book> books = new ArrayList<>();
    private long nextId = 1L;

    public synchronized Book addBook(Book request) {
        Book book = new Book(nextId++, request.getBookName().trim(),
                request.getAuthor().trim(), request.getPrice(), request.isAvailable());
        books.add(book);
        return book;
    }

    public synchronized List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public synchronized Book getBookById(Long id) {
        return books.stream()
                .filter(b -> b.getBookId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
    }

    public synchronized Book updateAvailability(Long id, boolean available) {
        Book book = getBookById(id);
        book.setAvailable(available);
        return book;
    }

    public synchronized void deleteBook(Long id) {
        Book book = getBookById(id);
        books.remove(book);
    }
}
