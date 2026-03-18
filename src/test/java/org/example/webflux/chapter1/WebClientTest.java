package org.example.webflux.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootTest
public class WebClientTest {

    private WebClient webClient = WebClient.builder().build();

    @Test
    public void testWebClient() {
        // WebClient를 사용하면 스케쥴러 스레드의 지원 없이도 완벽하게 비동기로 블로킹 회피 가능
        // 스케쥴러 따로 할당해야하는 문제도 해결
        // 스케쥴러는 아래 상황에서 사용한다. (스레드 분리)
        // 1. 어쩔 수 업이 블로킹이 발생하는 요소
        // 2. 마땅한 라이브러리가 없는 I/O작업
        // 3. 병렬처리를 하고 싶을 때, 이벤트 루프 스레드가 할 일이 너무 많을때
        Flux<Integer> integerFlux = webClient.get()
                .uri("http://localhost:8080/reactive/onenine/flux")
                .retrieve() // 블로킹 대기를 os에 위임
                .bodyToFlux(Integer.class);

        integerFlux.subscribe(data -> {
            System.out.println("처리 되고 있는 스레드 이름 : " + Thread.currentThread().getName());
            System.out.println("WebFlux가 구독중!! : " + data);
        });
        System.out.println("Netty 이벤트 루프로 스레트 복귀 !!");

        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }

    }

}
