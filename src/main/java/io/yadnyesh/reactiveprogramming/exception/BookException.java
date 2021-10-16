package io.yadnyesh.reactiveprogramming.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookException extends RuntimeException{
    public BookException(String message) {
        super(message);
    }
}
