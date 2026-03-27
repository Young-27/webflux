package org.example.webflux.service.llmclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmChatResponseDto;
import org.example.webflux.model.llmclient.LlmType;
import org.example.webflux.model.llmclient.gpt.request.GptChatRequestDto;
import org.example.webflux.model.llmclient.gpt.response.GptChatResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GptWebClientService implements LlmWebClientService {

    private final WebClient webClient;
    @Value("${llm.gpt.key}")
    private String gptApiKey;

    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {
        GptChatRequestDto gptChatRequestDto = new GptChatRequestDto(requestDto);
        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + gptApiKey)
                .bodyValue(gptChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(GptChatResponseDto.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new RuntimeException("API 요청 실패 : " + body));
                    });
                }))
                .bodyToMono(GptChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GPT;
    }

    @Override
    public Flux<LlmChatResponseDto> getChatCompletionStream(LlmChatRequestDto requestDto) {
        GptChatRequestDto gptChatRequestDto = new GptChatRequestDto(requestDto);
        gptChatRequestDto.setStream(true);  //stream 형식으로 답변이 조금씩 완료될때마다 우리에게 알려줌
        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + gptApiKey)
                .bodyValue(gptChatRequestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(GptChatResponseDto.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new RuntimeException("API 요청 실패 : " + body));
                    });
                }))
                .bodyToFlux(GptChatResponseDto.class)
                .takeWhile(response -> Optional.ofNullable(response.getSingleChoice().getFinish_reason()).isEmpty()) // takeWhile: 한번 방출 후 다음은 방출하지 않는다.
//                .map(response -> LlmChatResponseDto.getLlmChatResponseDtoFromStream(response));
                .map(LlmChatResponseDto::getLlmChatResponseDtoFromStream);
    }

}
