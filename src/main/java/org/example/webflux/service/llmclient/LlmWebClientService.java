package org.example.webflux.service.llmclient;

import org.example.webflux.exception.CommonError;
import org.example.webflux.exception.ErrorTypeException;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmChatResponseDto;
import org.example.webflux.model.llmclient.LlmType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
느슨한 결합
이 인터페이스를 사용하는 서비스는 내부 구현체에 대해 전혀 몰라도 된다.

유연성 및 확장성
다양한 LlmType을 계속해서 자유롭게 추가 가능

다형성
원하는 webClient구현체를 자유롭게 선택해서 사용할 수 있음
 */
public interface LlmWebClientService {
    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);

    default Mono<LlmChatResponseDto> getChatCompletionWithCatchException(LlmChatRequestDto requestDto) {
        return getChatCompletion(requestDto)
                .onErrorResume(exception -> {
                    if (exception instanceof ErrorTypeException errorTypeException) {
                        CommonError commonError = new CommonError(errorTypeException.getErrorType().getCode(), errorTypeException.getMessage());
                        return Mono.just(new LlmChatResponseDto(commonError, errorTypeException));
                    } else {
                        CommonError commonError = new CommonError(500, exception.getMessage());
                        return Mono.just(new LlmChatResponseDto(commonError, exception));
                    }
                });
    }

    LlmType getLlmType();

    Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto llmChatRequestDto);
    //gptWebClientService, GeminiWebClientService
}
