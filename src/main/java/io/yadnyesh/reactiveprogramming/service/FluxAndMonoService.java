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

    public Mono<String> fruitsMono() {
        return Mono.just("Mango").log();
    }

    public static void main(String[] args) {
        FluxAndMonoService fluxAndMonoService = new FluxAndMonoService();
        fluxAndMonoService.fruitsFlux()
                .subscribe(s -> {
                    log.info("s = " + s);
                });
        fluxAndMonoService.fruitsMono()
                .subscribe(s -> {
                    log.info("Mono | s = " + s);
                });
    }
}
