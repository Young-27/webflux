package org.example.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import static reactor.core.publisher.Flux.create;

public class SubscriberPublisherAsyncTest {

    @Test
    public void produceOneToNineFlux() {
        Flux<Integer> integerFlux = Flux.<Integer>create(sink -> {
            for (int i = 1; i <= 9; i++) {
                try {
                    Thread.sleep(500);
                } catch(Exception e) {

                }
                sink.next(i);
            }
            sink.complete();
        }).publish() // 바로 구독도 가능
                .subscribeOn(Schedulers.boundedElastic());
        // Flux로 어떻게 블로킹을 회피할 수 있을까?
        // => 스레드 1개만 사용해서는 절대로 블로킹을 회피할 수 없다.
        // => Reactor의 스케쥴러를 사용해서 스레드를 추가 할당해야지만 블로킹 회피가 가능하다.

        // 구독을 해줘야지만 실행된다.
        // 블로킹 코드가 들어있는 스레드는 메인 스레드가 무시한다.
        integerFlux.subscribe(data -> {
            System.out.println("처리 되고 있는 스레드 이름 : " + Thread.currentThread().getName());
            System.out.println("WebFlux가 구독중!! : " + data);
        });
        System.out.println("Netty 이벤트 루프로 스레트 복귀 !!");

        // 테스트 환경에서의 메인 스레드는 자기 할 일을 다하고 죽어버린다.
        // 메인 스레드가 살아있어야 다른 스레드들이 일을 할 수 있기 때문에 메인스레드를 잠시 잡아둔다.
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }

        // **스케쥴러가 제공하는 스레드가 중요한 스레드 (이벤트 루프 스레드) 대신 대기 하는것이 블로킹 회피의 기본 전략이다.**

        // Flux에는 데이터가 들어 있는게 아니다. 함수 코드 덩어리가 들어있다.
        // 그래서 구독을 해서 함수를 실행시키는 순간부터 데이터가 발행된다.

        // --------------------------------------------------------
        // Netty와 이벤트 루프 동작 원리
        // OS는 이벤트가 완료될 때 마다 쌓아둔다.
        // 이벤트 루프 스레드는 1~2를 계속 반복한다.
        // 1. 이벤트 루프에서 selector로 OS가 쌓아둔 이벤트가 있는지 조회한다.
        // 2. 이벤트가 있다면 이벤트에 등록 되어있는 함수를 실행한다.

    }

}
