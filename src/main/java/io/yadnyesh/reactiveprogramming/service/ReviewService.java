package io.yadnyesh.reactiveprogramming.service;

import io.yadnyesh.reactiveprogramming.model.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {
    public Flux<Review> getReviews(long bookId) {
        List<Review> reviewList = List.of(
                new Review(1, bookId, 9.1, "Good Book"),
                new Review(2, bookId, 7.1, "Worth Reading")
        );
        return Flux.fromIterable(reviewList);
    }
}
