package org.example.webflux.chapter2;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BasicFluxMonoTest {

    @Test
    public void testBasicFluxMono() {
        Flux.<Integer>just(1, 2, 3, 4, 5) // 첫번째는 빈 함수로부터, 두번째는 데이터로부터 시작할 수 있다.
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(data -> System.out.println("Flux가 구독한 data = " + data));
        // 기본 구현 흐름
        // 1. just 데이터로부터 흐름을 시작
        // 2. map과 filter같은 연산자로 데이터 가공
        // 3. subscribe하면서 데이터 방출

        // Mono : 0개부터 1개의 데이터만 방출할 수 있는 객체 -> Optional 정도
        // Flux : 0개 이상의 데이터를 방출할 수 있는 객체 -> List, Stream 0개 이상의 데이터 방출

        // 1개의 데이터를 가공할때는 Mono가 가독성, 유지보수 측면에서 Flux보다 우수함
        Mono.<Integer>just(2) // 첫번째는 빈 함수로부터, 두번째는 데이터로부터 시작할 수 있다.
                .map(data -> data * 2)
                .filter(data -> data % 4 == 0)
                .subscribe(data -> System.out.println("Mono가 구독한 data = " + data));
    }

    @Test
    public void testFluxMonoBlock() {
        Mono<String> justString = Mono.just("String");
        String string = justString.block();
        System.out.println("string = " + string);
    }

}
