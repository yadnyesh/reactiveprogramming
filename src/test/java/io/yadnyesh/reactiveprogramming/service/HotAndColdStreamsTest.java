package io.yadnyesh.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamsTest {

    @Test
    public void coldStreamTest() {
//        var numbers = Flux.range(1, 10)
////                .delayElements(Duration.ofMillis(0)).log();
        var numbers = Flux.range(1, 10).log();
        numbers.subscribe(integer -> System.out.println("Subscriber 1 => " + integer));
        numbers.subscribe(integer -> System.out.println("Subscriber 2 => " + integer));
    }

    @Test
    public void hotStreamTest() throws InterruptedException {
        var numbers = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(1000)).log();
        ConnectableFlux<Integer> publisher = numbers.publish();
        publisher.connect();

        publisher.subscribe(integer -> System.out.println("Subscriber 1 => " + integer));
        Thread.sleep(4000);
        publisher.subscribe(integer -> System.out.println("Subscriber 2 => " + integer));
        Thread.sleep(10000);
    }

}
