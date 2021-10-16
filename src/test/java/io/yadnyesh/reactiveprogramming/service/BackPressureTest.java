package io.yadnyesh.reactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure() {
        Flux<Integer> integers = Flux.range(1,100).log();
        //integers.subscribe(System.out::println);
        integers.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                //super.hookOnSubscribe(subscription);
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                //super.hookOnNext(value);
                System.out.println("Value: " + value);
                if (value == 3)
                    cancel();
            }

            @Override
            protected void hookOnComplete() {
                //super.hookOnComplete();
                System.out.println("Execution complete...");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureDrop() {
        Flux<Integer> integers = Flux.range(1,100).log();
        //integers.subscribe(System.out::println);
        integers
                .onBackpressureDrop(integer -> System.out.println("Dropped Values -> " + integer))
                .subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                //super.hookOnSubscribe(subscription);
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                //super.hookOnNext(value);
                System.out.println("Value: " + value);
                if (value == 3)
                    hookOnCancel();
            }

            @Override
            protected void hookOnComplete() {
                //super.hookOnComplete();
                System.out.println("Execution complete...");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureBuffer() {
        Flux<Integer> integers = Flux.range(1,100).log();
        //integers.subscribe(System.out::println);
        integers
                .onBackpressureBuffer(10,
                        i -> System.out.println("Buffered Value -> " + i))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        //super.hookOnSubscribe(subscription);
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        //super.hookOnNext(value);
                        System.out.println("Value: " + value);
                        if (value == 3)
                            hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        //super.hookOnComplete();
                        System.out.println("Execution complete...");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureError() {
        Flux<Integer> integers = Flux.range(1,100).log();
        //integers.subscribe(System.out::println);
        integers
                .onBackpressureError()
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        //super.hookOnSubscribe(subscription);
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        //super.hookOnNext(value);
                        System.out.println("Value: " + value);
                        if (value == 3)
                            hookOnCancel();
                    }

                    @Override
                    protected void hookOnComplete() {
                        //super.hookOnComplete();
                        System.out.println("Execution complete...");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        //super.hookOnError(throwable);
                        System.out.println("throwable = " + throwable);

                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }
}
