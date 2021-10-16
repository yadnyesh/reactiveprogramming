package io.yadnyesh.reactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

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
    public Flux<String> fruitsMonoflatMapMany() {
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>,Flux<String>> filterData
                 = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIfEmpty(int number) {
        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> fruitsFluxSwitchIfEmpty(int number) {
        Function<Flux<String>,Flux<String>> filterData
                = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Apple", "Mango", "Orange", "Banana"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple","Jack fruit"))
                .transform(filterData)
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return Flux.concat(fruits, veggies);
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");

        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxConcatWithForMono() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");

        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon").delayElements(Duration.ofMillis(75));
        return Flux.merge(fruits, veggies);
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon").delayElements(Duration.ofMillis(75));
        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.just("Mango", "Orange").delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Lemon").delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruits, veggies);
    }

    public Flux<String > fruitsFluxZip() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        return Flux.zip(fruits, veggies, (first,second) -> first + second).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        return fruits.zipWith(veggies, (first,second) -> first + second).log();
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Mango");
        var veggies = Mono.just("Tomato");
        return fruits.zipWith(veggies, (first,second) -> first + second).log();
    }

    public Flux<String > fruitsFluxZipTuple() {
        var fruits = Flux.just("Mango", "Orange");
        var veggies = Flux.just("Tomato", "Lemon");
        var moreVeggies = Flux.just("Potato", "Beans");
        return Flux.zip(fruits, veggies, moreVeggies)
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3())
                .log();
    }

    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("Mango", "Orange", "Banana"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> log.info("s = " + s))
                .doOnSubscribe(subscription -> log.info("Subscription: " + subscription.toString()))
                .doOnComplete(() -> log.info("Completed!"))
                .log();
    }

    public Flux<String> fruitFluxOnErrorReturn() {
        return Flux.just("Apple", "Mango")
                .concatWith(Flux.error(new RuntimeException("my Exception Occurred")))
                .onErrorReturn("Orange");
    }

    public Flux<String> fruitFluxOnErrorContinue() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("my Exception with: " + s);
                    return s.toUpperCase();
                })
                .onErrorContinue((e,f) -> {
                    log.info("e = " + e);
                    log.info("f = " + f);
                });
    }

    public Flux<String> fruitFluxOnErrorMap() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("my Exception with: " + s);
                    return s.toUpperCase();
                })
                .onErrorMap(throwable -> {
                    log.info("throwable = " + throwable);
                    return new IllegalStateException("From onErrorMap");
                });
    }

    public Flux<String> fruitFluxDoOnError() {
        return Flux.just("Apple", "Mango", "Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango"))
                        throw new RuntimeException("my Exception with: " + s);
                    return s.toUpperCase();
                })
                .doOnError(throwable ->
                    log.info("throwable = " + throwable)
                );
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
