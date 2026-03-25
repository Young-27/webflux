package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    /*
    subscribe
    publish
     */
    @Test
    public void testBasicFluxMono() {
        Mono.<Integer>just(2) // 첫번째는 빈 함수로부터, 두번째는 데이터로부터 시작할 수 있다.
                .map(data -> {
                    System.out.println("map Thread Name = " + Thread.currentThread().getName());
                    return data * 2;
                })
                .publishOn(Schedulers.parallel())
                .filter(data -> {
                    System.out.println("filter Thread Name = " + Thread.currentThread().getName());
                    return data % 4 == 0;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(data -> System.out.println("Mono가 구독한 data = " + data));
    }
}
