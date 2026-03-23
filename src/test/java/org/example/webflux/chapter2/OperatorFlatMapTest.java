package org.example.webflux.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorFlatMapTest {
    /*
    Mono<Mono<T>> -> Mono<T>
    Mono<Flux<T>> -> Flux<T>
    Flux<Mono<T>> -> Flux<T>
     */

    @Test
    public void monoToFlux() {
        Mono<Integer> one = Mono.just(1);
        // 언제 만들어질지 모르는 객체 + 언제 만들어질지 모르는 객체 = 언제 만들어질지 모르는 객체
        // 비동기 + 비동기 = 비동기
        // 이렇게 비동기가 겹쳐진 구조를 비동기 1개로 평탄화 시켜주는 것이 FlatMap이다.
        Flux<Integer> integerFlux = one.flatMapMany(data -> {
            return Flux.just(data, data + 1, data + 2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testWebClientFlatMap() {
        Flux<String> flatMap = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                callWebClient("3단계 - 최종 응답", 500))
                .flatMap(monoData -> {
                    return monoData;
                });

        flatMap.subscribe(data -> System.out.println("FlatMapped data = " + data));
        // 출력결과
        // FlatMapped data = 3단계 - 최종 응답 -> 딜레이 : 500
        // FlatMapped data = 2단계 - 문제 단계별로 풀어가기 -> 딜레이 : 1000
        // FlatMapped data = 1단계 - 문제 이해하기 -> 딜레이 : 1500
        // FlatMap은 입력된 순서가 아니라 처리 순서가 빠른 순서대로 응답을 해준다.

        Flux<String> flatMapSequential = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500))
                .flatMapSequential(monoData -> {
                    return monoData;
                });

        flatMapSequential.subscribe(data -> System.out.println("flatMap Sequential data = " + data));
        // 출력결과
        // flatMap Sequential data = 1단계 - 문제 이해하기 -> 딜레이 : 1500
        // flatMap Sequential data = 2단계 - 문제 단계별로 풀어가기 -> 딜레이 : 1000
        // flatMap Sequential data = 3단계 - 최종 응답 -> 딜레이 : 500
        // flatMapSequential은 입력된 순서대로 응답을 해준다.

        Flux<String> merge = Flux.merge(callWebClient("1단계 - 문제 이해하기", 1500),
                callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                callWebClient("3단계 - 최종 응답", 500));
//             .map(~~~~~); 여기서 추가로 가공하면 flatMap이랑 비슷한 구조
        // 데이터 가공을 하지 않을거라면 flatMap 보다 merge를 쓰는 것이 가독성이 더 좋다.

        flatMap.subscribe(data -> System.out.println("merge data = " + data));

        Flux<String> mergeSequential = Flux.mergeSequential(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500));

        mergeSequential.subscribe(data -> System.out.println("mergeSequential data = " + data));

        Mono<String> monomonoString = Mono.just(Mono.just("안녕!")).flatMap(monoData -> monoData);

        try {
            Thread.sleep(10000);
        } catch(Exception e) {

        }
    }
    // concat, concatMap 이런건 쓰지말자
    /*
    Flux<Mono<T>>
    Mono<Mono<T>> --> 이 구조 안에 있는 Mono는 flatMap, merge로 벗겨낼 수 있다.
                  --> flatMap, merge 순서를 보장하지 않으니 순서 보장이 필요하면 sequential을 사용하자.
    평탄화
    Mono<Flux<T>> --> flatMapMany --> 얘는 Flux<T> 순서가 보장된다.
    Flux<Flux<T>> ? collectList --> Flux<Mono<List<T>> --> Flux<List<T>>
     */

    /*
    정리
    - Flux, Mono 안에서 외부 api 호출, DB 호출 등 비동기 작업 흐름을 시작하면 Flux, Mono 안에 Flux, Mono가 중첩된 구조가 형성된다.
    - 모든 중첩된 Flux, Mono 구조는 flatMap, merge, collectList 등으로 평탄화 할 수 있다.
     */

    public Mono<String> callWebClient(String request, long delay) {
        return Mono.defer(() -> {
            try {
                Thread.sleep(delay);
                return Mono.just(request + " -> 딜레이 : " + delay);
            } catch (Exception e) {
                return Mono.empty();
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
