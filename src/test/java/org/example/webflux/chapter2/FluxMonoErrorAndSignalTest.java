package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class FluxMonoErrorAndSignalTest {

    @Test
    public void testBasicSignal() {
        Flux.just(1, 2, 3, 4)
                .doOnNext(publishedData -> System.out.println("publishedData = " + publishedData))
                .doOnComplete(() -> System.out.println("스트림이 끝났습니다."))
                .doOnError(ex -> {
                    System.out.println("ex 에러상황 발생! = " + ex);
                })
                .subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testFluxMonoError() {
        try {
            Flux.just(1, 2, 3, 4)
                    .map(data -> {
                        try {
                            if (data == 3) {
                                throw new RuntimeException();   // 에러가 던져지지 않음.. -> 어떻게 처리해야할까?
                                // 1. map 안에서 try catch 수동 처리
                                // 2. 에러 시그널을 잡는 함수들 사용
                            }
                            return data * 2;
                        } catch (Exception e) {
                            return data * 999;
                        }
                    })
                    .onErrorMap(ex -> new IllegalArgumentException())
                    .onErrorReturn(9999) // 에러가 났을 때 방출하고 싶은 데이터
                    .onErrorComplete()
                    // 에러 시그널을 찾아와서 처리할 수 있는 함수들
                    .subscribeOn(Schedulers.boundedElastic())
                    .subscribe(data -> System.out.println("data = " + data));
        } catch (Exception e) {
            System.out.println("에러가 발생했어요!");
        }
    }
    /*
    Flux, Mono.error()
     */

    @Test
    public void testFluxMonoDotError() {
        Flux.just(1, 2, 3, 4)
                .flatMap(data -> {
                    if (data != 3) {
                        return Mono.just(data);
                    } else {
//                        throw new RuntimeException();
                        return Mono.error(new RuntimeException()); // 우리가 직접 에러 시그널을 날림
                        // 둘 다 똑같은 결과긴 함
                    }
                }).subscribe(data -> System.out.println("data = " + data));
    }
    /*
    **시그널**
    Mono와 Flux에는 [방출(onNext) / 완료(onComplete) / 에러(onError)] 시그널이 있고
    doOnNext / doOnComplete / doOnError 로 포착이 가능하다.

    **에러 처리**
    리액티브 스트림 안에서 발생하는 예외는 스트림 밖으로 던져지지 않는다.
    때문에 스트림 안에서 적절히 try - catch를 이용해서 처리하거나
    onError~ 류 오퍼레이터를 사용하여 처리해야 한다.
     */

}
