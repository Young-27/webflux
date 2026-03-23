package org.example.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reactive")
public class ReactiveProgrammingExampleController {

    // 1~9까지 출력하는 api
    @RequestMapping(value = "/onenine/list")
    public List<Integer> produceOneToNine() {
        List<Integer> sink = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            try {
                Thread.sleep(500); // 0.5초 동안 스레드를 멈춤

                // 니즈 발생: 0.5초 동안 처리되는 데이터를 4.5초 이후에 한번에 보여주지 말고, 데이터가 처리 될 때 마다 바로바로 보여주세요
                // 개발자: 동기적인 자료형(List)로는 불가... => flux 사용
            } catch (Exception e) {
            }
            sink.add(i);
        } // 4.5초 소요
        return sink;
    }

    /**
     * List 방식을 Flux로 변경 (리액티브한 프로그래밍)
     * 데이터가 처리될 때마다 바로바로 보여줌
     * http header 설정에서 accept: text/event-stream 으로 변경해야함
     * @return
     */
    @GetMapping("/onenine/flux")
    public Flux<Integer> produceOneToNineFlux() {
        return Flux.create(sink -> {
            for (int i = 1; i <= 9; i++) {
                try {
                    Thread.sleep(500);

                } catch (Exception e) {
                }
                sink.next(i);
            }
            // 사용 후에는 반드시 닫기
            sink.complete();
        });
    }
    // 리액티브 스트링 구현체 Flux, Mono를 사용하여 발생하는 데이터를 바로바로 리액티브하게 처리
    // 비동기로 동작 - 논 블로킹하게 동작 해야한다.

    // 리액티브 프로그래밍 필수 요소
    // 1. 데이터가 준비될 때마다 바로바로 리액티브하게 처리 > **리액티브 스트림 구현체 Flux, Mono**를 사용하여 발생하는 데이터를 바로바로 처리
    // 2. 로직을 짤 때는 반드시 논 블로킹하게 짜야함 > 이를 위해 **비동기 프로그래밍**이 필요
}

