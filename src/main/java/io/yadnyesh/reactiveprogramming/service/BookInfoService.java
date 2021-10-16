package io.yadnyesh.reactiveprogramming.service;

import io.yadnyesh.reactiveprogramming.model.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
    public Flux<BookInfo> getBooks() {
        List<BookInfo> bookInfos = List.of(
                new BookInfo(1, "Book One", "Author One", "11111111"),
                new BookInfo(2, "Book Two", "Author Two", "22222222"),
                new BookInfo(3, "Book Three", "Author Three", "33333333"),
                new BookInfo(4, "Book Four", "Author Four", "44444444")
        );
        return Flux.fromIterable(bookInfos);
    }

    public Mono<BookInfo> getBookInfoById(long bookId) {
        BookInfo bookInfo = new BookInfo(1, "Book One", "Author One", "11111111");
        return Mono.just(bookInfo);
    }
}
