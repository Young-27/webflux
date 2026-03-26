package org.example.webflux.service.llmclient;

import org.example.webflux.model.llmclient.LlmChatRequestDto;
import org.example.webflux.model.llmclient.LlmChatResponseDto;
import org.example.webflux.model.llmclient.LlmType;
import reactor.core.publisher.Mono;

public class GeminiWebClientService implements LlmWebClientService{


    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {
        return null;
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GEMINI;
    }
}
