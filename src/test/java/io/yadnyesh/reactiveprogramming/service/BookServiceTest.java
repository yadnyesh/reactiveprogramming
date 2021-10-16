package io.yadnyesh.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookInfoService bookInfoService = new BookInfoService();
    private ReviewService reviewService = new ReviewService();
    private BookService bookService = new BookService(bookInfoService,reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
                .assertNext(book -> {
                    assertEquals("Book One", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewList().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Two", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewList().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Three", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewList().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Four", book.getBookInfo().getTitle());
                    assertEquals(2, book.getReviewList().size());
                })
                .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1).log();
        StepVerifier.create(book)
                .assertNext(bk -> {
                    assertEquals("Book One", bk.getBookInfo().getTitle());
                    assertEquals(2, bk.getReviewList().size());
                }).verifyComplete();
    }
}