package org.example.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

@SpringBootTest
public class FunctionalProgrammingTest {

    @Test
    public void produceOneToNine() {
        List<Integer> sink = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            sink.add(i);
        }

        // 함수에서 모두 처리하는 대신에 데이터를 가공하고 싶은 함수를 매개변수로 보내면!! (받기는 Function<>으로)
        // 가독성도 좋고, 데이터 가공과 출력 등을 마음대로 가능
        // *2를 전부 해주고 싶다
        sink = map(sink, (data) -> data * 4);

        // 4의 배수들만 남겨두고 싶다
        sink = filter(sink, (data) -> data % 4 == 0);

        // 반환값이 없으면 Consumer를 사용한다
        forEach(sink, (data) -> System.out.println(data));

        // 하지만 여기서 더 나아가 대입문도 없애고, 중복되는 코드도 짜고싶지 않다
        // => stream 사용
    }

    @Test
    public void produceOneToNineStream() {
        IntStream.rangeClosed(1, 9).boxed()
                .map((data) -> data * 4)
                .filter((data) -> data % 4 == 0)
                .forEach((data) -> System.out.println(data));   // 최종연산: collect, foreach, min, max 등. 최종 연산 코드가 없으면 Test 코드가 결과 없이 지나가버림
    }

    @Test
    public void produceOneToNineFlux() {
        Flux<Integer> integerFlux = Flux.create(sink -> {
            for (int i = 1; i <= 9; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        // 구독을 해줘야지만 실행된다.
        integerFlux.subscribe(data -> System.out.println("WebFlux가 구독중!! : " + data));
        System.out.println("Netty 이벤트 루프로 스레트 복귀 !!");
    }

    /**
     * 최종: 가독성도 좋고, Flux를 사용한 코드
     * fromIterable 을 사용해 stream과 유사한 방식으로 코드 작성
     * 마지막엔 forEach 대신 subscribe 사용해야함
     */
    @Test
    public void produceOneToNineFluxOperator() {
        Flux.fromIterable(IntStream.rangeClosed(1, 9).boxed().toList())
                .map((data) -> data * 4) // operator 대부분이 stream과 유사하게 동작
                .filter((data) -> data % 4 == 0)
                .subscribe((data) -> System.out.println(data));
    }

    private void forEach(List<Integer> sink, Consumer<Integer> consumer) {
        for (int i = 0; i<= sink.size()-1; i++) {
            consumer.accept(sink.get(i));
        }
    }

    private List<Integer> filter(List<Integer> sink, Function<Integer, Boolean> predicate) {
        List<Integer> newSink2 = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            if (predicate.apply(sink.get(i))) {
                newSink2.add(sink.get(i));
            }
        }
        sink = newSink2;
        return sink;
    }

    private List<Integer> map(List<Integer> sink, Function<Integer, Integer> mapper) {
        List<Integer> newSink1 = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            newSink1.add(mapper.apply(sink.get(i)));
        }
        sink = newSink1;
        return sink;
    }

}
