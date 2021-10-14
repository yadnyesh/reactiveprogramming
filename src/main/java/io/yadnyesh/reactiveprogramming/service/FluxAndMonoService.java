package io.yadnyesh.reactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

@Slf4j
public class FluxAndMonoService {
    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .log();
    }

    public Flux<String> fruitsFluxFilterAndMap(int number) {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }
    public Mono<List<String>> fruitsMonoflatMap() {
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        fluxAndMonoService.fruitsFlux()
                .subscribe(s -> log.info("s = " + s));
        fluxAndMonoService.fruitsMono()
                .subscribe(s -> log.info("Mono | s = " + s));
        fluxAndMonoService.fruitsFluxMap()
                .subscribe(s -> log.info("s = " + s));
        fluxAndMonoService.fruitsFluxFilter(3)
                .subscribe(s -> log.info("s = " + s));
        fluxAndMonoService.fruitsFluxFilterAndMap(3)
                .subscribe(s -> log.info("s = " + s));
        fluxAndMonoService.fruitsMonoflatMap()
                .subscribe(s -> log.info("s = " + s));
    }
}
