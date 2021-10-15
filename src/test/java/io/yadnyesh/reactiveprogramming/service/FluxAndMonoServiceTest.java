package io.yadnyesh.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServiceTest {

    FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoService.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("Apple", "Mango", "Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitsMono = fluxAndMonoService.fruitsMono();
        StepVerifier.create(fruitsMono)
                .expectNext("Mango")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxMap();
        StepVerifier.create(fruitsFlux)
                .expectNext("APPLE", "MANGO", "ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterAndMap() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxFilterAndMap(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("ORANGE", "BANANA")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsMonoflatMap() {
        var fruitsFlux = fluxAndMonoService.fruitsMonoflatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsMonoflatMapMany() {
        var fruitsFlux = fluxAndMonoService.fruitsMonoflatMapMany();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(5)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransform() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxTransform(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Banana")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIfEmpty() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxTransformDefaultIfEmpty(10);
        StepVerifier.create(fruitsFlux)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxSwitchIfEmpty() {
        var fruitsFlux = fluxAndMonoService.fruitsFluxSwitchIfEmpty(8);
        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Jack fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxConcat().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxConcatWith().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Orange", "Tomato", "Lemon")
                .verifyComplete();
    }


    @Test
    void fruitsFluxConcatWithForMono() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxConcatWithForMono().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxMerge().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Tomato", "Orange", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxMerge().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Tomato", "Orange", "Lemon")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitAndVeggieFlux = fluxAndMonoService.fruitsFluxMergeWithSequential().log();
        StepVerifier.create(fruitAndVeggieFlux)
                .expectNext("Mango", "Orange", "Tomato","Lemon")
                .verifyComplete();
    }
}