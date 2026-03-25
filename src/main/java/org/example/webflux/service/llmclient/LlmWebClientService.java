package org.example.webflux.service.llmclient;

import org.example.webflux.model.user.llmclient.LlmChatRequestDto;
import org.example.webflux.model.user.llmclient.LlmChatResponseDto;
import org.example.webflux.model.user.llmclient.LlmType;
import reactor.core.publisher.Mono;

public interface LlmWebClientService {
    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);

    LlmType getLlmType();
    //gptWebClientService, GeminiWebClientService
}
