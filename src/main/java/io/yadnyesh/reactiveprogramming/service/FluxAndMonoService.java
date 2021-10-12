package io.yadnyesh.reactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
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
    }
}
